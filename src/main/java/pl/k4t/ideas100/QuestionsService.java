package pl.k4t.ideas100;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionsService {

    private final Ideas100Configuration ideas100Configuration;

    public QuestionsService(Ideas100Configuration ideas100Configuration) {
        this.ideas100Configuration = ideas100Configuration;
    }

    public List<Questions> getQuestions() {
        return Arrays.asList(
                new Questions("Question 1"),
                new Questions("Question 2"));
    }

    public String test(String value) {
        return String.format("Hello in questions service %s %s", ideas100Configuration.getName(), value);
    }
}
