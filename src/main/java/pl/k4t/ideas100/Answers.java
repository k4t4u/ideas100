package pl.k4t.ideas100;

public class Answers {

    private String name;

    public Answers() {
    }

    public Answers(String name) {
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
        return "Answers{" +
                "name='" + name + '\'' +
                '}';
    }
}
