package julien.firstwebsocket.controller;

import julien.firstwebsocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


  @MessageMapping("/chat.sendMessage") // The @MessageMapping annotation ensures that,
  // if a message is sent to the /chat.sendMessage destination, the sendMessage() method is called.
  @SendTo("/topic/public")
  // The return value is broadcast to all subscribers of /topic/publicChatRoom
  public ChatMessage sendMessage(@Payload final ChatMessage chatMessage) {
    return chatMessage;
  }

  @MessageMapping("/chat.addUser")
  @SendTo("/topic/public")
  public ChatMessage addUser(@Payload final ChatMessage chatMessage,
      final SimpMessageHeaderAccessor headerAccessor) {
    // Add username in web socket session
    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
    return chatMessage;
  }

}
