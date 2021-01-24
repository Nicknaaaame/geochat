package org.example.backend.service.impl;

import org.example.backend.model.entity.Message;
import org.example.backend.model.request.SaveMessageRequest;
import org.example.backend.repository.MessageRepository;
import org.example.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;

    @Override
    public Message saveMessage(SaveMessageRequest request) {
        throw new UnsupportedOperationException();
//        return repository.save(new Message(null, request.getText(), MessageType.TEXT, ));
    }

    @Override
    public Message saveMessage(Message message) {
        return repository.save(message);
    }
}
