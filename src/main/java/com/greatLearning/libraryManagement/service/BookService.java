package com.greatLearning.libraryManagement.service;

import com.greatLearning.libraryManagement.entity.Book;

import java.util.List;

public interface BookService {

    public void save(Book book);

    public Book findById(Integer bookId);

    public List<Book> findAll();

    public void deleteById(int id);

    public List<Book> searchBy(String name, String author);

}
