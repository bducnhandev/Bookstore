package Tuan4.BuiDucNhanBai4.controllers;

import Tuan4.BuiDucNhanBai4.services.CategoryService;
import Tuan4.BuiDucNhanBai4.viewmodels.CategoryGetVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryGetVm>> getAllCategories() {
        List<CategoryGetVm> categoryGetVms = categoryService.getAllCategories().stream()
                .map(CategoryGetVm::from)
                .toList();
        return ResponseEntity.ok(categoryGetVms);
    }
}
