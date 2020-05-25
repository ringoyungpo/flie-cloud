package io.github.ringoyungpo.filecloud;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SocketController {

    private static ConcurrentHashMap<Long, FileStatus> fileStatusMatrix = new ConcurrentHashMap<>();

    @MessageMapping("/edit_action")
    @SendTo("/topic/editing_box")
    public EditReplay send(EditAction editAction, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sessionId = headerAccessor.getSessionId();
        if (editAction.getType() == EditActionEnum.APPLY_FOR_EDITING){
            FileStatus fileStatus = fileStatusMatrix.get(editAction.getFileId());
            if (fileStatus == null){
                fileStatusMatrix.put(
                        editAction.getFileId(),
                        FileStatus.builder()
                                .editingUser(sessionId).waittingUsers(new LinkedList<String>())
                                .build());
                return EditReplay.builder().uuid(sessionId).type(EditReplayEnum.AVAILABLE).build();
            } else {
                fileStatus.getWaittingUsers().offer(sessionId);
                return EditReplay.builder().uuid(sessionId).type(EditReplayEnum.EDITING).build();
            }
        }
        return EditReplay.builder().uuid(sessionId).build();
    }
}
