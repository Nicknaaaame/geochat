package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveMessageRequest {
    private Long chatId;
    @NotBlank
    @Size(max = 4096, message = "Maximum size of message text is 4096 symbols")
    private String text;
}
