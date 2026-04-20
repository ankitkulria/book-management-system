package com.learning.repository;

import com.learning.entity.Book;
import com.learning.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByPublicationDateAfter(LocalDate date, Pageable pageable);

    Page<Book> findByAuthorId (Integer authorId, Pageable pageable);

    Page<Book> findByGenre(Genre genre, Pageable pageable);
}
