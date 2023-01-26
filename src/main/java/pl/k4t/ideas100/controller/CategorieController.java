package pl.k4t.ideas100.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.domain.model.Categorie;
import pl.k4t.ideas100.service.CategorieService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categories")
public class CategorieController {

    private final CategorieService categoriesService;

    public CategorieController(CategorieService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    List<Categorie> getCategories(){
        return categoriesService.getCategories();
    }

    @GetMapping("{id}")
    Categorie getCategorie(@PathVariable UUID id){
        return  categoriesService.getCategorie(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Categorie createCategorie(@RequestBody Categorie categorie){
        return categoriesService.createCategorie(categorie);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Categorie updateCategorie(@PathVariable UUID id, @RequestBody Categorie categorie){
        return categoriesService.updateCategorie(id, categorie);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategorie(@PathVariable UUID id){
        categoriesService.deleteCategorie(id);
    }
}
