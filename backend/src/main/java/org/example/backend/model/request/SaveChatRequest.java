package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveChatRequest {
    @NotBlank(message = "Provide chat name")
    @Size(min = 3, max = 52, message = "Minimal size of chat name is 3 symbols maximum is 52")
    private String name;
    @Size(max = 2048, message = "Maximum size of description is 2048 symbols")
    private String description;
    private MultipartFile picture;
}
