package pl.k4t.ideas100;

public class Questions {

    private String name;

    public Questions() {
    }

    public Questions(String name) {
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
        return "Questions{" +
                "name='" + name + '\'' +
                '}';
    }
}
