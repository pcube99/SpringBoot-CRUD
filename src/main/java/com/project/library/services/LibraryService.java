package com.project.library.services;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.project.library.constants.Constants;
import com.project.library.models.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Slf4j
@Service
public class LibraryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Book addBook(Book book) throws Exception{
        try {
            book = mongoTemplate.save(book);
            log.info("[addBook DB] - {} ", book);
        } catch (Exception e) {
            log.error("[addBook] err - {}", e.getMessage());
            throw e;
        }
        return book;
    }

    public List<Book> getBooks() {
        List<Book> bookList;
        try {
            bookList = mongoTemplate.findAll(Book.class);
            log.info("[addBook DB] - {} ", bookList);
        } catch (Exception e) {
            log.error("[addBook] err - {}", e.getMessage());
            throw e;
        }
        return bookList;
    }

    public Book getBooksWithId(String bookId) {
        Book book;
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(bookId));
        try {
            book = mongoTemplate.findOne(query, Book.class);
            log.info("[getBooksWithId DB] - {} ", book);
        } catch (Exception e) {
            log.error("[getBooksWithId] err - {}", e.getMessage());
            throw e;
        }
        return book;
    }

    public DeleteResult deleteBooksWithId(String bookId) {
        DeleteResult book;
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(bookId));
        try {
            book = mongoTemplate.remove(query, Book.class);
            log.info("[deleteBooksWithId DB] - {} ", book);
        } catch (Exception e) {
            log.error("[deleteBooksWithId] err - {}", e.getMessage());
            throw e;
        }
        return book;
    }

    public void updateBooksWithId(Book updatedBook) {
        UpdateResult book;
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(updatedBook.getBookId()));
        Update update = new Update();
        update.set("bookId", updatedBook.getBookId());
        update.set("bookName", updatedBook.getBookName());
        update.set("bookAuthor", updatedBook.getBookAuthor());
        update.set("bookPrice", updatedBook.getBookPrice());

        try {
            book = mongoTemplate.updateFirst(query ,update,Book.class);
            log.info("[updateBooksWithId DB] - {} ", book);
        } catch (Exception e) {
            log.error("[updateBooksWithId] err - {}", e.getMessage());
            throw e;
        }
    }
}
