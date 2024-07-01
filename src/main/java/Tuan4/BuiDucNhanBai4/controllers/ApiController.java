package Tuan4.BuiDucNhanBai4.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Tuan4.BuiDucNhanBai4.entities.Book;
import Tuan4.BuiDucNhanBai4.services.BookService;
import Tuan4.BuiDucNhanBai4.services.CartService;
import Tuan4.BuiDucNhanBai4.services.CategoryService;
import Tuan4.BuiDucNhanBai4.viewmodels.BookGetVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApiController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping("/roles")
    public List<String> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
        return ResponseEntity.ok(bookService.getAllBooks(
                pageNo == null ? 0 : pageNo,
                pageSize == null ? 20 : pageSize,
                sortBy == null ? "id" : sortBy)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }

    @GetMapping("/books/id/{id}")
    public ResponseEntity<BookGetVm> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getBookById(id)
                .map(BookGetVm::from)
                .orElse(null));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        System.out.println("Deleting book with id: " + id);
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);  // Ensure the book ID is set correctly
        bookService.updateBook(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<BookGetVm>> searchBooks(String keyword)
    {
        return ResponseEntity.ok(bookService.searchBook(keyword)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }
}
