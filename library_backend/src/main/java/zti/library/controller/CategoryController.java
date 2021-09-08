package zti.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zti.library.dto.AuthorDto;
import zti.library.dto.CategoryDto;
import zti.library.dto.BookDto;
import zti.library.model.Author;
import zti.library.model.Category;
import zti.library.service.AuthorService;
import zti.library.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody final CategoryDto categoryDto){
        Category category = categoryService.addCategory(Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable final Long id){
        Category category = categoryService.getCategory(id);
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable final Long id){
        Category category  = categoryService.deleteCategory(id);
        return new ResponseEntity<>(CategoryDto.from(category), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CategoryDto> editCategory(@PathVariable final Long id, @RequestBody final CategoryDto categoryDto){
        Category categoryToEdit = categoryService.editCategory(id, Category.from(categoryDto));
        return new ResponseEntity<>(CategoryDto.from(categoryToEdit), HttpStatus.OK);
    }

}
