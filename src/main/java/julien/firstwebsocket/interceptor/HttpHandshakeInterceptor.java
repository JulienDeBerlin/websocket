package julien.firstwebsocket.interceptor;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
@Slf4j
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

  @Override
  public boolean beforeHandshake(final ServerHttpRequest request, final ServerHttpResponse response,
      final WebSocketHandler wsHandler,
      final Map<String, Object> attributes) throws Exception {

    log.info("Call beforeHandshake");

    if (request instanceof ServletServerHttpRequest) {
      final ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
      final HttpSession session = servletRequest.getServletRequest().getSession();
      attributes.put("sessionId", session.getId());
    }
    return true;
  }

  @Override
  public void afterHandshake(final ServerHttpRequest request, final ServerHttpResponse response,
      final WebSocketHandler wsHandler,
      final Exception ex) {
    log.info("Call afterHandshake");
  }

}
