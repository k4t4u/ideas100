package pl.k4t.ideas100.service;

import org.springframework.stereotype.Service;
import pl.k4t.ideas100.Ideas100Configuration;
import pl.k4t.ideas100.domain.model.Categorie;
import pl.k4t.ideas100.domain.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CategorieService {

    public List<Categorie> getCategories() {
        return Arrays.asList(
                new Categorie("Categorie 1"),
                new Categorie("Categorie 2"));
    }

    public Categorie getCategorie(UUID id) {
        return new Categorie("Categorie "+id);
    }

    public Categorie createCategorie(Categorie categorie) {
        categorie.setId(UUID.randomUUID());
        return categorie;
    }

    public Categorie updateCategorie(UUID id, Categorie categorie) {
        return categorie;
    }

    public void deleteCategorie(UUID id) {
    }
}
