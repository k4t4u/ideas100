package pl.k4t.ideas100.category.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private UUID id;

    @NotBlank(message = "{ideas100.validation.name.NotBlank.message}")
    @Size(min = 3, max = 35)
    private String name;

    public Category() {
        this.id = UUID.randomUUID();
    }

    public Category(String name) {

        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
