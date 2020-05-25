package io.github.ringoyungpo.filecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class SocketController {

    static ConcurrentHashMap<Long, Queue<String>> editQueueMatrix = new ConcurrentHashMap<>();

    @MessageMapping("/edit_action")
    @SendToUser("/topic/editing_box")
    public EditReplay send(EditAction editAction, StompPrincipal stompPrincipal) throws Exception {
        Queue<String> editQueue = editQueueMatrix.get(editAction.getFileId());
        String uuid = stompPrincipal.getName();
        if (editAction.getType() == EditActionEnum.APPLY_FOR_EDITING) {
            if (editQueue == null) {
                editQueue = new LinkedList<String>();
                editQueue.offer(uuid);
                editQueueMatrix.put(editAction.getFileId(),editQueue);
                return EditReplay.builder().uuid(uuid).available(true).build();
            } else {
                editQueue.offer(uuid);
            }
        }
        return EditReplay.builder().uuid(uuid).available(false).build();
    }
}
