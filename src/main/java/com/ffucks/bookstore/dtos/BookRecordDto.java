package com.ffucks.bookstore.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRecordDto(

        @NotBlank
        @Size(max = 150)
        String title,

        @NotBlank
        @Size(max = 100)
        String author,

        @NotBlank
        @Size(max = 100)
        String publisher,

        @NotNull
        @Min(1450)
        @Max(2100)
        Integer publicationYear) {
}
