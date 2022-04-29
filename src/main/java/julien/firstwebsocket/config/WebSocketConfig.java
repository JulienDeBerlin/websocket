package julien.firstwebsocket.config;

import julien.firstwebsocket.interceptor.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //@Configuration annotation indicates that a class declares one or more @Bean methods
@EnableWebSocketMessageBroker // enables WebSocket message handling, backed by a message broker.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final HttpHandshakeInterceptor handshakeInterceptor;

  @Autowired // constructor injection better than field injection
  public WebSocketConfig(final HttpHandshakeInterceptor handshakeInterceptor) {
    this.handshakeInterceptor = handshakeInterceptor;
  }

  @Override
  // defining the URL used by the client for setting up the WebSocket connection with the server.
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);
  }

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes(
        "/app"); // This prefix will be used to define all the message mappings
    registry.enableSimpleBroker("/topic"); // enable a simple memory-based message
    // broker to carry the greeting messages back to the client on destinations prefixed with /topic
  }

}
