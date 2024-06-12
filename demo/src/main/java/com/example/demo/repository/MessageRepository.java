package com.example.demo.repository;

import com.example.demo.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoomId(Long roomId);
    @Query("SELECT m FROM Message m WHERE m.room.roomCode = :roomCode ORDER BY m.timestamp DESC")
    List<Message> findLastMessageByRoomCode(String roomCode, Pageable pageable);
}
