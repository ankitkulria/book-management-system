package com.learning.service;

import com.learning.DTO.AuthorRequestDTO;
import com.learning.DTO.AuthorResponseDTO;
import com.learning.entity.Author;
import com.learning.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private  final ModelMapper modelMapper;
    private final AuthorRepo authorRepo;

//    CREATE AUTHOR
    public  AuthorResponseDTO createAuthor(AuthorRequestDTO dto)
    {
        Author author=modelMapper.map(dto,Author.class);
        Author savedAuthor=authorRepo.save(author);
        return modelMapper.map(savedAuthor, AuthorResponseDTO.class);
    }

//    GET ALL AUTHOR
    public List<AuthorResponseDTO> getAllAuthors()
    {
        List<Author> authors=authorRepo.findAll();
        return authors.stream().map(author -> modelMapper.map(author, AuthorResponseDTO.class)).toList();
    }

//    GET AUTHOR BY ID
    public AuthorResponseDTO getAuthorById(Integer authorId)
    {
        Author author=authorRepo.findById(authorId).orElseThrow(()->new RuntimeException("Author not found"));
        return modelMapper.map(author, AuthorResponseDTO.class);
    }

//    UPDATE AUTHOR
    public AuthorResponseDTO updateAuthor(Integer id, AuthorRequestDTO updatedAuthor)
    {
        Author author=authorRepo.findById(id).orElseThrow(()->new RuntimeException("Author not found"));
        author.setName(updatedAuthor.getName());
        author.setEmail(updatedAuthor.getEmail());
        author.setProfession(updatedAuthor.getProfession());
        author.setNationality(updatedAuthor.getNationality());

        Author author2=authorRepo.save(author);

        return modelMapper.map(author2,AuthorResponseDTO.class);
    }
//    DELETE AUTHOR
    public void deleteAuthorByID(Integer authorId)
    {
        authorRepo.deleteById(authorId);
    }

//    SEARCH AUTHOR BY NAME
    public List<AuthorResponseDTO> searchAuthorsByName(String name)
    {
        List<Author> authors=authorRepo.findByNameContainingIgnoreCase(name);
        return authors.stream().map(author -> modelMapper.map(author,AuthorResponseDTO.class)).toList();
    }
}
