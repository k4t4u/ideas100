package pl.k4t.ideas100.domain.model;

import java.util.UUID;

public class Category {

    private UUID id;

    private String name;

    public Category() {
    }

    public Category(String name) {

        this.name = name;
        this.id = UUID.randomUUID();
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
