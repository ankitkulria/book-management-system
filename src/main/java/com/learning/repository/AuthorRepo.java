package com.learning.repository;

import com.learning.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface AuthorRepo extends JpaRepository<Author,Integer> {

    List<Author> findByNameContainingIgnoreCase(String name);
}
