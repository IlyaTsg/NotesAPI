package com.ilyatsg.notesapi.controllers;

import com.ilyatsg.notesapi.dtos.CreateNoteRequest;
import com.ilyatsg.notesapi.dtos.NoteDto;
import com.ilyatsg.notesapi.exceptions.ErrorDto;
import com.ilyatsg.notesapi.services.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@Tag(name = "note-controller")
public class NoteController {
    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    @Operation(summary = "Get all Notes", description = "Get all Notes in JSON array")
    @ApiResponse(responseCode = "200", content = @Content(
            array = @ArraySchema(schema = @Schema(implementation = NoteDto.class))))
    public ResponseEntity<?> getAllNotes(){
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Note by id", description = "Get Note by id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = NoteDto.class)))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getNoteById(@PathVariable("id") Integer id){
        return noteService.getNoteById(id);
    }

    @PostMapping
    @Operation(summary = "Create Note", description = "Create Note")
    @ApiResponse(responseCode = "201", content = @Content(
            schema = @Schema(implementation = NoteDto.class)))
    @ApiResponse(responseCode = "400", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest createNoteRequest){
        return noteService.createNote(createNoteRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Note", description = "Delete Note")
    @ApiResponse(responseCode = "204")
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleteNote(@PathVariable("id") Integer id){
        return noteService.deleteNote(id);
    }
}

