package com.learning.controller;

import com.learning.DTO.AuthorRequestDTO;
import com.learning.DTO.AuthorResponseDTO;
import com.learning.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private  final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto)
    {
        AuthorResponseDTO response= authorService.createAuthor(dto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors()
    {
        List<AuthorResponseDTO> response=authorService.getAllAuthors();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable int id)
    {
        AuthorResponseDTO response=authorService.getAuthorById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable int id,@RequestBody AuthorRequestDTO dto)
    {
        AuthorResponseDTO response= authorService.updateAuthor(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id)
    {
        authorService.deleteAuthorByID(id);
        return ResponseEntity.ok("Author Deleted Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorByName(@RequestParam String name)
    {
        List<AuthorResponseDTO> response=authorService.searchAuthorsByName(name);
        return ResponseEntity.ok(response);
    }
}
