package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @GetMapping("/last")
    public Message getLastMessage() {
        List<Message> messages = messageService.getAllMessages();
        if (!messages.isEmpty()) {
            return messages.get(messages.size() - 1);
        }
        return null;
    }
    @GetMapping("/room/{roomCode}/last")
    public Message getLastMessageByRoomCode(@PathVariable String roomCode) {
        return messageService.getLastMessageByRoomCode(roomCode);
    }
    @DeleteMapping
    public void deleteAllMessages() {
        messageService.deleteAllMessages();
    }
    @GetMapping("/room/{roomCode}")
    public List<Message> getMessagesByRoomCode(@PathVariable String roomCode) {
        return messageService.getMessagesByRoomCode(roomCode);
    }
}
