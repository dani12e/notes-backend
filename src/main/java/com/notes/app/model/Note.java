package com.notes.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(unique = true, nullable = false)
    private String publicId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Note() {
        // generate publicId and timestamp by default
        this.publicId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    // // getters & setters

    // public Long getId() { return id; }
    // public void setId(Long id) { this.id = id; }

    // public String getTitle() { return title; }
    // public void setTitle(String title) { this.title = title; }

    // public String getContent() { return content; }
    // public void setContent(String content) { this.content = content; }

    // public String getPublicId() { return publicId; }
    // public void setPublicId(String publicId) { this.publicId = publicId; }

    // public LocalDateTime getCreatedAt() { return createdAt; }
    // public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
