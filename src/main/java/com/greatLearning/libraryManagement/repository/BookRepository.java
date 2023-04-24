package com.greatLearning.libraryManagement.repository;

import com.greatLearning.libraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name, String author);
    List<Book> findByNameContainsAllIgnoreCase(String name);
    List<Book> findByAuthorContainsAllIgnoreCase(String author);

}
