package io.github.ringoyungpo.filecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(fixedRateString = "10000", initialDelayString = "0")
    public void schedulingTask() {
        SocketController.editQueueMatrix.forEach((k, v) -> {
            v.stream().forEach((q)->{
                simpMessagingTemplate.convertAndSendToUser(
                        q,
                        "/topic/file_status",
                        "Hallo " + k + " at " + new Date().toString()
                );
            });
        });
    }
}
