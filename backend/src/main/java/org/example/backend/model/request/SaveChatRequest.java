package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SaveChatRequest {
    private String name;
    private String description;
    private MultipartFile picture;
}