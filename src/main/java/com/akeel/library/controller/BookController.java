//package com.example.demo.controller;
//
//import model.com.akeel.library.Book;
//import service.com.akeel.library.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookService.findAllBooks();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Book> getBookById(@PathVariable Long id) {
//        return bookService.findBookById(id);
//    }
//
//    @PostMapping
//    public Book createBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
//
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
//        Optional<Book> bookOptional = bookService.findBookById(id);
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            book.setTitle(bookDetails.getTitle());
//            book.setAuthor(bookDetails.getAuthor());
//            book.setPublicationYear(bookDetails.getPublicationYear());
//            book.setIsbn(bookDetails.getIsbn());
//            // Update other fields as needed
//            return bookService.saveBook(book);
//        } else {
//            throw new ResourceNotFoundException("Book not found with id " + id);
//            System.out.println("Book not found with id " + id);
//        }
////        throw new Exception("Book not found with id " + id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id) {
//        bookService.deleteBookById(id);
//    }
//}
package com.akeel.library.controller;

import com.akeel.library.dto.BookDto;
import com.akeel.library.service.BookService;
import com.akeel.library.controller.api.BookApi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
//import springfox.documentation.annotations.ApiIgnore;


import java.util.List;
import java.util.Optional;
//
//@RestController( value = "/Books")
//@RequestMapping("/api/books")
//@Tag(name = "Book", description = "the Book Api")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookService.findAllBooks();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Book> getBookById(@PathVariable Long id) {
//        return bookService.findBookById(id);
//    }
//
//    @PostMapping
//    public Book createBook(@RequestBody Book book) {
//        return bookService.saveBook(book);
//    }
//
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
//        Optional<Book> bookOptional = bookService.findBookById(id);
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            book.setTitle(bookDetails.getTitle());
//            book.setAuthor(bookDetails.getAuthor());
//            book.setPublicationYear(bookDetails.getPublicationYear());
//            book.setIsbn(bookDetails.getIsbn());
//            // Update other fields as needed
//            return bookService.saveBook(book);
//        } else {
////            throw new ResourceNotFoundException("Book not found with id " + id);
//        }
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id) {
//        bookService.deleteBookById(id);
//    }
//}


//import model.com.akeel.library.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController implements BookApi {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooksWithFilter(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size) {

        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        Page<BookDto> books = bookService.findByTitleContainingOrAuthorContaining(
                title.orElse(""),
                author.orElse(""),
                pageable);
        return ResponseEntity.ok(books.toList());
    }

    @GetMapping("/all")
    //@Cacheable(value = "books", key = "", sync = true)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAllAvailableBooks() {
        return ResponseEntity.ok(bookService.getAllAvailableBooks());
    }

    @GetMapping("/{id}")
    @Cacheable(value = "books", key = "#id", sync = true)
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Optional<BookDto> book = bookService.findById(id);

        return book.map((r) -> {

                    return ResponseEntity.ok(book.get());
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
//        Book book = BookMapper.INSTANCE.BookDtoToBook(bookDto);
        BookDto savedBook = bookService.save(bookDto);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDetails) {
        return bookService.update(id,bookDetails)
                .map(
                        book -> ResponseEntity.ok(book)
                )
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return (bookService.deleteById(id)) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}