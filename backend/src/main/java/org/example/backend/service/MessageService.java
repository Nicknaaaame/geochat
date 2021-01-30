package org.example.backend.service;

import org.example.backend.model.entity.Message;
import org.example.backend.model.request.SaveLocalMessageRequest;
import org.example.backend.model.request.SavePrivateMessageRequest;

public interface MessageService {
    Message saveMessage(SavePrivateMessageRequest request);
    Message saveMessage(SaveLocalMessageRequest request);
    Message saveMessage(Message message);
}
