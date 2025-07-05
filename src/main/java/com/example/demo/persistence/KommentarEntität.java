package com.example.demo.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "kommentare")
public class KommentarEntität {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "tastatur_id")
    private TastaturEntität tastatur;

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public TastaturEntität getTastatur() { return tastatur; }
    public void setTastatur(TastaturEntität tastatur) { this.tastatur = tastatur; }
}
