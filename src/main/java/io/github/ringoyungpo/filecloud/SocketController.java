package io.github.ringoyungpo.filecloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Controller
public class SocketController {

    static ConcurrentMap<Long, Queue<String>> editQueueMatrix = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/edit_action/{fileId}")
    @SendToUser("/topic/editing_box")
    public EditReplay send(EditAction editAction, StompPrincipal stompPrincipal,@DestinationVariable Long fileId) throws Exception {
        Queue<String> editQueue = editQueueMatrix.get(editAction.getFileId());
        String uuid = stompPrincipal.getName();
        if (editAction.getType() == EditActionEnum.APPLY_FOR_EDITING) {
            if (editQueue == null) {
                editQueue = new LinkedList<String>();
                editQueue.offer(uuid);
                editQueueMatrix.put(fileId,editQueue);
                return EditReplay.builder().uuid(uuid).available(true).build();
            } else {
                editQueue.offer(uuid);
            }
        }
        return EditReplay.builder().uuid(uuid).available(false).build();
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        if (event.getUser() == null){
            return;
        }
        String uuid = event.getUser().getName();

        List<Long> keys = editQueueMatrix.entrySet().stream()
                .filter(entry -> entry.getValue().contains(uuid))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        keys.forEach(key -> {
            Queue<String> value = editQueueMatrix.get(key);
            if (value.contains(uuid)){
                if (value.peek().equals(uuid)){
                    value.poll();
                    if (value.isEmpty()){
                        editQueueMatrix.remove(key);
                    } else {
                        simpMessagingTemplate.convertAndSendToUser(
                                value.peek(),
                                "/topic/editing_box",
                                EditReplay.builder().uuid(value.peek()).available(true).build()
                        );
                    }
                } else {
                    value.remove(uuid);
                }
            }
        });
    }
}
