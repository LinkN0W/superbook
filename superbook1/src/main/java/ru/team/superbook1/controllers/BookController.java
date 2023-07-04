package ru.team.superbook1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.services.BookService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping
    public Iterable<Book> showAll(){
        return bookService.findAll();
    }

    @PostMapping("/new")
    public Optional<Book> save(@RequestBody Book book){
        bookService.save(book);
        return Optional.ofNullable(book);
    }

    @GetMapping("/show/{bookId}")
    public Optional<Book> show(@PathVariable UUID bookId){
        return bookService.findBookById(bookId);
    }

    @PostMapping("/edit/{bookId}")
    public Optional<Book> update(@PathVariable UUID bookId, @RequestBody Book book){
        return bookService.update(bookId,book);
    }

    @DeleteMapping("/delete/{bookId}")
    public String delete(@PathVariable UUID bookId){
        bookService.delete(bookId);
        return "OK";
    }



}
