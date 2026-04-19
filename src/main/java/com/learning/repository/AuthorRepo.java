package com.learning.repository;

import com.learning.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author,Integer> {

    List<Author> findByNameContainingIgnoreCase(String name);
}
