package org.example.backend.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequest {
    private MultipartFile file;
    private Long id;
}
