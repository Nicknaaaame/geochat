package org.example.backend.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    public final String storageDirectoryPath = "C:\\Users\\Mi\\Desktop\\geochat-s3";

    private static final String USER_IMG_URI = "\\user-img", CHAT_IMG_URI = "\\chat-img";

    public String  uploadUserImage(MultipartFile file, Long id) {
        uploadToLocalFileSystem(file, String.valueOf(id), USER_IMG_URI);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/image/user/")
                .path(String.valueOf(id))
                .toUriString();
    }

    public String uploadChatImage(MultipartFile file) {
        String id = UUID.randomUUID().toString();
        uploadToLocalFileSystem(file, id, CHAT_IMG_URI);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/image/chat/")
                .path(String.valueOf(id))
                .toUriString();
    }

    private void uploadToLocalFileSystem(MultipartFile file, String id, String uri) {
        String fileName = id + "." + "png";//FilenameUtils.getExtension(file.getOriginalFilename());
        Path storageDirectory = Paths.get(storageDirectoryPath + uri);
        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);// we are Copying all bytes from an input stream to a file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getUserImage(Long id) {
        Path destination = Paths.get(storageDirectoryPath + USER_IMG_URI + id);
        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getChatImage(String id) {
        Path destination = Paths.get(storageDirectoryPath + CHAT_IMG_URI + "\\" + id + ".png");
        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
