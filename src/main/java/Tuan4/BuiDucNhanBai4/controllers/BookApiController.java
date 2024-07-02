package Tuan4.BuiDucNhanBai4.controllers;

import Tuan4.BuiDucNhanBai4.entities.Book;
import Tuan4.BuiDucNhanBai4.services.BookService;
import Tuan4.BuiDucNhanBai4.viewmodels.BookGetVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo,
                                                       Integer pageSize, String sortBy) {
        return ResponseEntity.ok(bookService.getAllBooks(
                pageNo == null ? 0 : pageNo,pageSize == null ? 20 : pageSize,
                        sortBy == null ? "id" : sortBy)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    @PostMapping
    public void createBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGetVm> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getBookById(id)
                .map(BookGetVm::from)
                .orElse(null));
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
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }
}
