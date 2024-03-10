package com.example.demoNotesApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Component
@Table(name = "Notes_table")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @Column(name = "Data_create")
    private LocalDateTime localDateTime = LocalDateTime.now();
}
