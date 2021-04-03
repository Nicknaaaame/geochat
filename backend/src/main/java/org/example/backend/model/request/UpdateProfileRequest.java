package org.example.backend.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateProfileRequest {
    @NotBlank(message = "Provide name")
    @Size(min = 6, max = 52, message = "Minimal size of name is 6 symbols maximum is 52")
    private String name;
    private MultipartFile picture;
}
