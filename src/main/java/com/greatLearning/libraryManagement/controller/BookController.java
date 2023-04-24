package com.greatLearning.libraryManagement.controller;

import com.greatLearning.libraryManagement.entity.Book;
import com.greatLearning.libraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/list")
    public String listBooks(Model model) {

        List<Book> bookList = bookService.findAll();
        model.addAttribute("Books", bookList);

        return "list-Books";
    }

    @PostMapping("/save")
    public String save(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("author") String author, @RequestParam("category") String category) {
        Book book;
        if (id == 0) {
            book = new Book();
            book.setAuthor(author);
            book.setCategory(category);
            book.setName(name);
        } else {
            book = new Book(name,author,category);
            book.setId(id);
        }

        bookService.save(book);

        return "redirect:/books/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {

        // delete the Book
        bookService.deleteById(theId);

        // redirect to /Books/list
        return "redirect:/books/list";

    }


    @RequestMapping("/search")
    public String search(@RequestParam("name") String name,
                         @RequestParam("author") String author,
                         Model theModel) {

        if(name.trim().isEmpty() && author.trim().isEmpty()){
            return "redirect:/books/list";
        }else{
            List<Book> books=bookService.searchBy(name,author);
            theModel.addAttribute("Books",books);
        }

        return "list-Books";
    }

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Book theBook = new Book();

        theModel.addAttribute("Book", theBook);

        return "Book-form";
    }


    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId,
                                    Model theModel) {

        // get the Book from the service
        Book theBook = bookService.findById(theId);


        // set Book as a model attribute to pre-populate the form
        theModel.addAttribute("Book", theBook);

        // send over to our form
        return "Book-form";
    }

    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }

}
