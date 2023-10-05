package com.ilyatsg.notesapi.dtos;

import com.ilyatsg.notesapi.entities.Note;
import lombok.Data;

@Data
public class NoteDto {
    private Integer id;

    private Integer user_id;

    private String title;

    private String content;

    public NoteDto(Integer id, Integer user_id, String title, String content) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }
    public NoteDto(Note note) {
        this.id = note.getId();
        this.user_id = note.getUser_id();
        this.title = note.getTitle();
        this.content = note.getContent();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
