package com.learning.DTO;

import com.learning.entity.Genre;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookResponseDTO {
    private int id;
    private String title;
    private Genre genre;
    private int pages;
    private LocalDate publicationDate;
    private String language;
    private BigDecimal price;

    private int authorId;
    private String authorName;
}
