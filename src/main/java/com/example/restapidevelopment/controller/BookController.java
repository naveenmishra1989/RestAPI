package com.example.restapidevelopment.controller;

import com.example.restapidevelopment.dto.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/book")
public class BookController {
    private static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(Book.builder().id(12345L).author("Rao").name("Black book").build());
        bookList.add(Book.builder().id(12346L).author("Gosling").name("Java").build());

    }

    //localhost:8384/book/list
    @GetMapping("/list")
    public ResponseEntity<List<Book>> getBookList() {
        ResponseEntity<List<Book>> responseEntity = new ResponseEntity<>(bookList, HttpStatus.OK);
        log.info(responseEntity);
        return responseEntity;
    }

    //localhost:8384/book/id/12346  //valid input
    //localhost:8384/book/id/12349   //invalid input
    @GetMapping("/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> optionalBook = bookList.stream().filter(b -> b.getId() == id).findAny();
        if (optionalBook.isPresent()) {
            ResponseEntity<Book> responseEntity = new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
            log.info(responseEntity);
            return responseEntity;
        }
        ResponseEntity<Book> responseEntity1 = new ResponseEntity<>(Book.builder().build(), HttpStatus.NOT_FOUND);
        log.info(responseEntity1);
        return responseEntity1;
    }
 /*

   {
    "id": 12348,
    "name":"C++",
    "author": "naveen"
    }

    */

    // localhost:8384/book/new
    @PostMapping("/new")
    public ResponseEntity<Book> createBook(@Valid  @RequestBody Book book) {
        bookList.add(book);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }

   // localhost:8384/book/update
    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
           Book updatedUser = bookList.stream().filter(u-> u.getId().equals(book.getId())).peek(up->{
            up.setAuthor(book.getAuthor());
            up.setName(book.getName());
           }).collect(Collectors.toList()).get(0);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }


}
