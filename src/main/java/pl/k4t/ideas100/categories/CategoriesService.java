package pl.k4t.ideas100.categories;

import org.springframework.stereotype.Service;
import pl.k4t.ideas100.Ideas100Configuration;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoriesService {

    private final Ideas100Configuration ideas100Configuration;

    public CategoriesService(Ideas100Configuration ideas100Configuration) {
        this.ideas100Configuration = ideas100Configuration;
    }

    public List<Categories> getCategories() {
        return Arrays.asList(
                new Categories("Category 1"),
                new Categories("Category 2"));
    }

    public String test(String value) {
        return String.format("Hello in categories service %s %s", ideas100Configuration.getName(), value);
    }
}
