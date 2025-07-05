package com.example.demo.web.api;

import java.time.LocalDateTime;

public class Kommentar {
    private Long id;
    private String name;
    private String text;
    private LocalDateTime createdAt;

    // Konstruktor
    public Kommentar(Long id, String name, String text, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
    }

    // Getter/Setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
