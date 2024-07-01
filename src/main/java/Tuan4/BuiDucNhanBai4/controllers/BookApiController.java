package Tuan4.BuiDucNhanBai4.controllers;

import Tuan4.BuiDucNhanBai4.entities.Book;
import Tuan4.BuiDucNhanBai4.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllProducts();
    }

    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
        return ResponseEntity.ok().body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Book bookData) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
        book.setTitle(bookData.getTitle());
        book.setPrice(bookData.getPrice());
        book.setAuthor(bookData.getAuthor());
        bookService.addBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Product not found on :: "+ id));
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
