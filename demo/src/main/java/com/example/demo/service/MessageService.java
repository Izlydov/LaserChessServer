package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.model.Room;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Message saveMessage(Message message) {
        if (message.getRoom() != null && message.getRoom().getId() == null) {
            roomRepository.save(message.getRoom());
        }
        if (message.getTimestamp() == null) {
            message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        return messageRepository.save(message);
    }
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Message sendMessage(String roomCode, String sender, String content) {
        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Message message = new Message();
        message.setRoom(room);
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return messageRepository.save(message);
    }
    @Transactional(readOnly = true)
    public List<Message> getMessagesByRoomCode(String roomCode) {
        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        return messageRepository.findByRoomId(room.getId());
    }
    @Transactional(readOnly = true)
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }
    @Transactional
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
    @Transactional(readOnly = true)
    public Message getLastMessageByRoomCode(String roomCode) {
        Pageable pageable = PageRequest.of(0, 1);
        List<Message> messages = messageRepository.findLastMessageByRoomCode(roomCode, pageable);
        return messages.isEmpty() ? null : messages.get(0);
    }
}
