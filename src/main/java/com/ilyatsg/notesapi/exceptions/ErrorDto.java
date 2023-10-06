package com.ilyatsg.notesapi.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Error Dto")
public class ErrorDto {
    @Schema(type = "int", example = "404")
    private Integer status;
    @Schema(type = "string", example = "Note not found")
    private String message;
    @Schema(type = "string", example = "2023-10-06T11:41:49.259+00:00")
    private Date timestamp;

    public ErrorDto(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
