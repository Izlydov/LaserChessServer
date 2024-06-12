package com.example.demo.controller;

import com.example.demo.model.Message;
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
    public Room createRoom(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @GetMapping("/{roomCode}")
    public Room getRoomByCode(@PathVariable String roomCode) {
        return roomService.findByRoomCode(roomCode);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }
    @DeleteMapping("/{roomCode}")
    public void deleteRoomByRoomCode(@PathVariable String roomCode) {
        roomService.deleteRoomByRoomCode(roomCode);
    }
    @DeleteMapping
    public void deleteAllRooms(){
        roomService.deleteAllRooms();
    }
}