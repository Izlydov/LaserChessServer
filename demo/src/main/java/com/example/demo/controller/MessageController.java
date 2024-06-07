package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestParam String roomCode, @RequestParam String sender, @RequestParam String content) {
        return messageService.sendMessage(roomCode, sender, content);
    }

    @GetMapping("/{roomCode}")
    public List<Message> getMessages(@PathVariable String roomCode) {
        return messageService.getMessagesByRoomCode(roomCode);
    }
}
