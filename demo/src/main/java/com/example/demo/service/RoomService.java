package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room saveRoom(Room room) {
        Optional<Room> existingRoom = roomRepository.findByRoomCode(room.getRoomCode());
        if (existingRoom.isPresent()) {
            throw new RuntimeException("Room with code " + room.getRoomCode() + " already exists.");
        }
        return roomRepository.save(room);
    }

    public Room findByRoomCode(String roomCode) {
        return roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found with roomCode: " + roomCode));
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        existingRoom.setPlayer1(room.getPlayer1());
        existingRoom.setPlayer2(room.getPlayer2());
        existingRoom.setRoomCode(room.getRoomCode());
        return roomRepository.save(existingRoom);
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }
    public void deleteRoomByRoomCode(String roomCode){
        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Room not found with roomCode: " + roomCode));
        roomRepository.delete(room);
    }
}
