package pl.k4t.ideas100;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    List<Questions> getQuestions(){
        return questionsService.getQuestions();
    }

    @GetMapping("/test")
    String test(@RequestParam String value){
        return questionsService.test(value);
    }
}
