package com.example.demoNotesApp.service;

import com.example.demoNotesApp.model.Note;
import com.example.demoNotesApp.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;

    public Note addNote(Note note){
        noteRepository.save(note);
        return note;
    }

    public List<Note> findAll(int limit){
        return noteRepository.findAll(PageRequest.of(0, limit, Sort.by("localDateTime").descending())).getContent();
    }

    public Optional<Note> findById(Long id){
        return noteRepository.findById(id);
    }

    public void deleteById(Long id){
        noteRepository.deleteById(id);
    }
    public boolean updateById(Long id, Note updatedNote) {
        if (noteRepository.existsById(id)) {
            updatedNote.setId(id);
            Note savedNote = noteRepository.save(updatedNote);
            return true;
        } else {
            return false;
        }
    }
}


