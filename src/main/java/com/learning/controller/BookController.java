package com.learning.controller;

import com.learning.DTO.BookRequestDTO;
import com.learning.DTO.BookResponseDTO;
import com.learning.entity.Book;
import com.learning.entity.Genre;
import com.learning.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

//    CREATE
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO dto)
    {
        BookResponseDTO response=bookService.createBook(dto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

//    GET ALL(PAGINATION+SORTING)
    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortedby,
            @RequestParam String direction
    )
    {
        Page<BookResponseDTO> books=bookService.getALlBooks(page,size,sortedby,direction);
        return ResponseEntity.ok(books);
    }

//    GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable int id)
    {
        BookResponseDTO book=bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

//    UPDATE BOOK
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBookById(@PathVariable int id,
                                               @RequestBody BookRequestDTO book){
        BookResponseDTO updatedBook=bookService.updateBookById(id,book);
        return ResponseEntity.ok(updatedBook);
    }

//    DELETE BOOK
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id)
    {
        bookService.deleteBookById(id);
        return  new ResponseEntity<>("Book Deleted Successfully",HttpStatus.OK);
    }

//    SEARCH BY TITLE
    @GetMapping("/search/title")
    public ResponseEntity<Page<BookResponseDTO>> searchByTitle(
            @RequestParam String title,
            @RequestParam int page,
            @RequestParam int size
    ){
        Page<BookResponseDTO> books=bookService.searchByTitle(title,page,size);
        return ResponseEntity.ok(books);
    }

//    SEARCH BY AFTER DATE
    @GetMapping("/search/date")
    public ResponseEntity<Page<BookResponseDTO>> getBookByAfterDate(
            @RequestParam LocalDate date,
            @RequestParam int page,
            @RequestParam int size
            )
    {
        Page<BookResponseDTO> books=bookService.getBooksByAfterDate(date,page,size);
        return ResponseEntity.ok(books);
    }

//    SEARCH BY AUTHOR ID
    @GetMapping("/search/author")
    public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthorId(
            @RequestParam int authorId,
            @RequestParam int page,
            @RequestParam int size
            )
    {
        Page<BookResponseDTO> books=bookService.getBooksByAuthorId(authorId,page,size);
        return ResponseEntity.ok(books);
    }

//    SEARCH BY GENRE
    @GetMapping("/search/genre")
    public ResponseEntity<Page<BookResponseDTO>> getBooksByGenre(
            @RequestParam Genre genre,
            @RequestParam int page,
            @RequestParam int size
            )
    {
        Page<BookResponseDTO> books=bookService.getBooksByGenre(genre,page,size);
        return ResponseEntity.ok(books);
    }
}
