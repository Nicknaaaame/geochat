package org.example.backend.controller;

import org.example.backend.model.request.ImageRequest;
import org.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/image")
public class ImageController {
    @Autowired
    public ImageService imageService;

    @PostMapping(value = "/chat/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> uploadChatImage(@ModelAttribute ImageRequest request) {
        this.imageService.uploadChatImage(request.getFile());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> uploadUserImage(@ModelAttribute ImageRequest request) {
        this.imageService.uploadUserImage(request.getFile(), request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/chat/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getChatImage(@PathVariable String id) {
        return this.imageService.getChatImage(id);
    }

    @GetMapping(value = "/user/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] getUserImage(@PathVariable Long id) {
        return this.imageService.getUserImage(id);
    }
}
