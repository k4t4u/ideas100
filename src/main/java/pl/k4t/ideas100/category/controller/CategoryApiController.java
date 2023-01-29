package pl.k4t.ideas100.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.domain.model.Category;
import pl.k4t.ideas100.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("{id}")
    Category getCategory(@PathVariable UUID id){
        return  categoryService.getCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Category updateCategory(@PathVariable UUID id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategorie(@PathVariable UUID id){
        categoryService.deleteCategory(id);
    }
}
