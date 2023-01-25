package pl.k4t.ideas100;

public class Categories {

    private String name;

    public Categories() {
    }

    public Categories(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                '}';
    }
}
