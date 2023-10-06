package com.ilyatsg.notesapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Create Note request")
public class CreateNoteRequest {
    @Schema(type = "int", example = "1")
    private Integer user_id;

    @Schema(type = "string", example = "Test title")
    private String title;

    @Schema(type = "string", example = "Test content")
    private String content;

    public CreateNoteRequest(Integer user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }
}
