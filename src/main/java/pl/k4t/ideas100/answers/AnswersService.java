package pl.k4t.ideas100.answers;

import org.springframework.stereotype.Service;
import pl.k4t.ideas100.Ideas100Configuration;

import java.util.Arrays;
import java.util.List;

@Service
public class AnswersService {

    private final Ideas100Configuration ideas100Configuration;

    public AnswersService(Ideas100Configuration ideas100Configuration) {
        this.ideas100Configuration = ideas100Configuration;
    }

    public List<Answers> getAnswers() {
        return Arrays.asList(
                new Answers("Answer 1"),
                new Answers("Answer 2"));
    }

    public String test(String value) {
        return String.format("Hello in answer service %s %s", ideas100Configuration.getName(), value);
    }
}
