package com.example.demoNotesApp.controller;

import com.example.demoNotesApp.model.Note;
import com.example.demoNotesApp.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class Controller {
    private NoteService noteService;

    @GetMapping("/")
    public ResponseEntity<List> findAll(Note note){
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note saveNote = noteService.addNote(note);
        return ResponseEntity.ok(saveNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable("id") Long id){
        Optional<Note> noteOptional = noteService.findById(id);
        return noteOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id){
        Optional<Note> noteOptional = noteService.findById(id);
        if (noteOptional.isPresent()){
            noteService.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<Note>  updateNote(@PathVariable("id") Long id, @RequestBody Note note){
        if(noteService.updateById(id, note)){
            return ResponseEntity.ok(note);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
