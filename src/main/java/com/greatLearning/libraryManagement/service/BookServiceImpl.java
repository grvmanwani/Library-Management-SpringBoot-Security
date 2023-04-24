package com.greatLearning.libraryManagement.service;

import com.greatLearning.libraryManagement.entity.Book;
import com.greatLearning.libraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(Integer bookId) {
        Optional<Book> bookDetails= bookRepository.findById(bookId);
        if(bookDetails.isPresent()){
            return bookDetails.get();
        }

        throw new RuntimeException("Book Details not found");
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBy(String name, String author) {
        if((!StringUtils.isEmpty(name)) && (!StringUtils.isEmpty(author))) {
            return bookRepository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);

        }else if((StringUtils.isEmpty(name))){
            return bookRepository.findByAuthorContainsAllIgnoreCase(author);

        }else if((StringUtils.isEmpty(author))){
            return bookRepository.findByNameContainsAllIgnoreCase(name);

        }

        return null;
    }
}
