package com.ilyatsg.notesapi.services;

import com.ilyatsg.notesapi.dtos.NoteDto;
import com.ilyatsg.notesapi.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ResponseEntity<?> getAllNotes(){
        List<NoteDto> notes = noteRepository.findAll().stream().map(NoteDto::new).toList();
        return ResponseEntity.ok(notes);
    }
}
