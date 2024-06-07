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

    public Room createRoom(String roomCode, String player1, String player2) {
        Room room = new Room();
        room.setRoomCode(roomCode);
        room.setPlayer1(player1);
        room.setPlayer2(player2);
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomByCode(String roomCode) {
        return roomRepository.findByRoomCode(roomCode);
    }
}
