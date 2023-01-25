package pl.k4t.ideas100.categories;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    List<Categories> getCategories(){
        return categoriesService.getCategories();
    }

    @GetMapping("/test")
    String test(@RequestParam String value){
        return categoriesService.test(value);
    }
}
