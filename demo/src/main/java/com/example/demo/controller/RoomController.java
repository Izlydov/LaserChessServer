package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public Room createRoom(@RequestParam String roomCode, @RequestParam String player1, @RequestParam String player2) {
        return roomService.createRoom(roomCode, player1, player2);
    }

    @GetMapping("/{roomCode}")
    public Optional<Room> getRoom(@PathVariable String roomCode) {
        return roomService.getRoomByCode(roomCode);
    }
}
