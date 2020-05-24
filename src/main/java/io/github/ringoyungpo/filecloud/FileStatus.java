package io.github.ringoyungpo.filecloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Queue;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileStatus {

    private String editingUser;

    private Queue<String> waittingUser;
}
