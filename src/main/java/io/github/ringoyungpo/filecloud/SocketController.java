package io.github.ringoyungpo.filecloud;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SocketController {

    private static ConcurrentHashMap<Long, FileStatus> fileStatusMatrix;

    @MessageMapping("/editing")
    @SendTo("/topic/editing_status")
    public OutputMessage send(Message msg, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionId();
        return OutputMessage.builder().uuid(sessionId).build();
    }
}
