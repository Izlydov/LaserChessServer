package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private Room room;
    @SerializedName("sender")
    private String sender;
    @SerializedName("content")
    private String content;
    private Timestamp timestamp;
    @Lob
    @Column(columnDefinition = "TEXT")
    @SerializedName("positions")
    private String positions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}
