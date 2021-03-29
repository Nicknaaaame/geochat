package org.example.backend.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileRequest {
    private String name;
    private MultipartFile picture;
}
