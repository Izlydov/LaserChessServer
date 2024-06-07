package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.model.Room;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Message sendMessage(String roomCode, String sender, String content) {
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow(() -> new RuntimeException("Room not found"));
        Message message = new Message();
        message.setRoom(room);
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRoomCode(String roomCode) {
        Room room = roomRepository.findByRoomCode(roomCode).orElseThrow(() -> new RuntimeException("Room not found"));
        return messageRepository.findByRoomId(room.getId());
    }
}
