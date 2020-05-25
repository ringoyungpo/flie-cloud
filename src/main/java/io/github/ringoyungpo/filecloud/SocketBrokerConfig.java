package io.github.ringoyungpo.filecloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/ui");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/editing_center")
                .setAllowedOrigins("http://localhost:3000")
                .setHandshakeHandler(new HandshakeHandler());

        registry.addEndpoint("/editing_center")
                .setAllowedOrigins("http://localhost:3000")
                .setHandshakeHandler(new HandshakeHandler()).withSockJS();
    }
}
