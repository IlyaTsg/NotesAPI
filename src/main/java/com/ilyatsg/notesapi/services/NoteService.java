package com.ilyatsg.notesapi.services;

import com.ilyatsg.notesapi.dtos.CreateNoteRequest;
import com.ilyatsg.notesapi.dtos.NoteDto;
import com.ilyatsg.notesapi.entities.Note;
import com.ilyatsg.notesapi.exceptions.ErrorDto;
import com.ilyatsg.notesapi.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<?> getNoteById(Integer id){
        Note note = noteRepository.findById(id).orElse(null);
        if(note != null){
            return ResponseEntity.ok(new NoteDto(note));
        } else{
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Note not found"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createNote(CreateNoteRequest createNoteRequest){
        Note note = new Note(null, createNoteRequest.getUser_id(), createNoteRequest.getTitle(), createNoteRequest.getContent());
        try {
            noteRepository.save(note);
            return new ResponseEntity<>(new NoteDto(note), HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Note cannot be created"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteNote(Integer id){
        try{
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException e){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Note not found"), HttpStatus.NOT_FOUND);
        }
    }
}
