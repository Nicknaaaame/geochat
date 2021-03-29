package org.example.backend.controller;

import org.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/image")
public class ImageController {
    @Autowired
    public ImageService imageService;

    @GetMapping(value = "/chat/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getChatImage(@PathVariable String id) {
        return this.imageService.getChatImage(id);
    }

    @GetMapping(value = "/user/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getUserImage(@PathVariable String id) {
        return this.imageService.getUserImage(id);
    }
}
