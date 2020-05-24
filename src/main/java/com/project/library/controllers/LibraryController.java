package com.project.library.controllers;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.project.library.models.Book;
import com.project.library.services.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @CrossOrigin
    @PostMapping("/books")
    public String addBook(@RequestBody Book book) throws Exception {
        try {
            libraryService.addBook(book);
        } catch (Exception e) {
            log.error("[addBook] error {} ", e.getMessage());
            throw e;
        }
        return "success";
    }

    @CrossOrigin
    @GetMapping("/books")
    public List<Book> getBooks() throws Exception {
        List<Book> bookList;
        try {
            bookList = libraryService.getBooks();
        } catch (Exception e) {
            log.error("[addBook] error {} ", e.getMessage());
            throw e;
        }
        return bookList;
    }

    @CrossOrigin
    @GetMapping("/books/{id}")
    public Book getBookWithId(@PathVariable("id") String bookId) throws Exception {
        Book book;
        try {
            book = libraryService.getBooksWithId(bookId);
        } catch (Exception e) {
            log.error("[addBook] error {} ", e.getMessage());
            throw e;
        }
        return book;
    }

    @CrossOrigin
    @DeleteMapping("/books/{id}")
    public void deleteBookWithId(@PathVariable("id") String bookId) throws Exception {
        DeleteResult book;
        try {
            book = libraryService.deleteBooksWithId(bookId);
        } catch (Exception e) {
            log.error("[addBook] error {} ", e.getMessage());
            throw e;
        }
    }

    @CrossOrigin
    @PutMapping("/books")
    public void deleteBookWithId(@RequestBody Book updatedBook) throws Exception {
        UpdateResult book;
        try {
            libraryService.updateBooksWithId(updatedBook);
        } catch (Exception e) {
            log.error("[addBook] error {} ", e.getMessage());
            throw e;
        }
    }



}
