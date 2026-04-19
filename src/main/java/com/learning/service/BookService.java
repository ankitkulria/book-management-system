package com.learning.service;

import com.learning.DTO.BookRequestDTO;
import com.learning.DTO.BookResponseDTO;
import com.learning.entity.Author;
import com.learning.entity.Book;
import com.learning.entity.Genre;
import com.learning.exception.ResourceNotFoundException;
import com.learning.repository.AuthorRepo;
import com.learning.repository.BookRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final ModelMapper modelMapper;

//    CREATE BOOK
    public BookResponseDTO createBook(BookRequestDTO dto)
    {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setGenre(dto.getGenre());
        book.setPages(dto.getPages());
        book.setPublicationDate(dto.getPublicationDate());
        book.setLanguage(dto.getLanguage());
        book.setPrice(dto.getPrice());

        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setAuthor(author);
        Book saved = bookRepo.save(book);

        return modelMapper.map(saved, BookResponseDTO.class);
    }

//    GET ALL BOOKS(PAGINATION+SORTING)
    public Page<BookResponseDTO> getALlBooks(int page,int size,String sortedby,String direction)
    {
        Sort sort = direction.equalsIgnoreCase("asc")?
                Sort.by(sortedby).ascending():
                Sort.by(sortedby).descending();
        Pageable pageable= PageRequest.of(page,size,sort);

        Page<Book> books=bookRepo.findAll(pageable);
        return books.map(book -> modelMapper.map(book,BookResponseDTO.class));
    }

//    GET BOOK BY ID
    public BookResponseDTO getBookById(Integer id)
    {
        Book book=bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(("Book Not Found")));
        return modelMapper.map(book,BookResponseDTO.class);
    }


//  UPDATE BOOK
    public BookResponseDTO updateBookById(Integer id, @NonNull BookRequestDTO dto)
    {
        Book book=bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found"));

        modelMapper.map(dto,book);
        if(dto.getAuthorId()!=0)
        {
            Author author=authorRepo.findById(dto.getAuthorId()).orElseThrow(()->new ResourceNotFoundException("Author not found"));
            book.setAuthor(author);
        }
        Book updatedBook=bookRepo.save(book);
        return modelMapper.map(updatedBook,BookResponseDTO.class);
    }

//    DELETE BOOK
    public void deleteBookById(Integer id)
    {
        Book book=bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Can't Delete Because book not found with the given ID."));
        bookRepo.deleteById(id);
    }

//    SEARCH BY TITLE
    public Page<BookResponseDTO> searchByTitle(String title,int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size);
        Page<Book> books=bookRepo.findByTitleContainingIgnoreCase(title,pageable);
        return books.map(book->modelMapper.map(book,BookResponseDTO.class));
    }
//    SEARCH BY AFTER DATE
    public Page<BookResponseDTO> getBooksByAfterDate(LocalDate date,int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size);
        Page<Book> books=bookRepo.findByPublicationDateAfter(date,pageable);
        return books.map(book->modelMapper.map(book,BookResponseDTO.class));
    }

//    SEARCH BY AUTHOR ID
    public Page<BookResponseDTO> getBooksByAuthorId(Integer authorId,int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size);
        Page<Book> books=bookRepo.findByAuthorId(authorId,pageable);
        return books.map(book->modelMapper.map(book,BookResponseDTO.class));
    }

//    SEARCH BY GENRE
    public Page<BookResponseDTO> getBooksByGenre(Genre genre,int page,int size)
    {
        Pageable pageable=PageRequest.of(page,size);
        Page<Book> books=bookRepo.findByGenre(genre,pageable);
        return books.map(book->modelMapper.map(book,BookResponseDTO.class));
    }
}
