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

    public final String storageDirPath = "C:\\Users\\Mi\\Desktop\\geochat-s3";

    private static final String USER_IMG_FOLDER = "\\user-img", CHAT_IMG_FOLDER = "\\chat-img";

    public String uploadUserImage(MultipartFile file, String oldPath) {
        String id;
        if (oldPath == null)
            id = UUID.randomUUID().toString();
        else {
            String[] strings = oldPath.split("/");
            id = strings[strings.length - 1];
            try {
                UUID uuid = UUID.fromString(id);
                id = uuid.toString();
            } catch (IllegalArgumentException exception) {
                id = UUID.randomUUID().toString();
            }
        }
        uploadToLocalFileSystem(file, id, USER_IMG_FOLDER);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/image/user/")
                .path(String.valueOf(id))
                .toUriString();
    }

    public String uploadChatImage(MultipartFile file, String oldPath) {
        String id;
        if (oldPath == null)
            id = UUID.randomUUID().toString();
        else {
            String[] strings = oldPath.split("/");
            id = strings[strings.length - 1];
        }
        uploadToLocalFileSystem(file, id, CHAT_IMG_FOLDER);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/image/chat/")
                .path(String.valueOf(id))
                .toUriString();
    }

    private void uploadToLocalFileSystem(MultipartFile file, String id, String uri) {
        String fileName = id + "." + "png";//FilenameUtils.getExtension(file.getOriginalFilename());
        Path storageDirectory = Paths.get(storageDirPath + uri);
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

    public byte[] getUserImage(String id) {
        Path destination = Paths.get(storageDirPath + USER_IMG_FOLDER + "\\" + id + ".png");
        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getChatImage(String id) {
        Path destination = Paths.get(storageDirPath + CHAT_IMG_FOLDER + "\\" + id + ".png");
        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
