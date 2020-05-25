package io.github.ringoyungpo.filecloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAction {

    private long fileId;

    private EditActionEnum type;
}
