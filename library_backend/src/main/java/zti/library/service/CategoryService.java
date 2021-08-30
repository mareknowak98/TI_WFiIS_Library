package zti.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import zti.library.exception.CategoryNotFoundException;
import zti.library.model.Category;
import zti.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getCategories(){
        return StreamSupport.stream(categoryRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category deleteCategory(Long id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }

    @Transactional
    public Category editCategory(Long id, Category author){
        Category categoryToEdit = getCategory(id);
        categoryToEdit.setCategory(author.getCategory());
        return categoryToEdit;
    }

}
