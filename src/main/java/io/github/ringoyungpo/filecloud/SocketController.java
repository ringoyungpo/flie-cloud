package io.github.ringoyungpo.filecloud;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message msg) throws Exception {
        return new OutputMessage(
                msg.getFrom(),
                msg.getText(),
                new SimpleDateFormat("HH:mm").format(new Date()));
    }
}
