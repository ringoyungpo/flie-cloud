package io.github.ringoyungpo.filecloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditReplay {

    private String uuid;

    private Boolean available;

    private Long timeLeft;
}
