package pl.k4t.ideas100;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswersController {

    private final AnswersService answersService;

    public AnswersController(AnswersService answersService) {
        this.answersService = answersService;
    }

    @GetMapping
    List<Answers> getAnswers(){
        return answersService.getAnswers();
    }

    @GetMapping("/test")
    String test(@RequestParam String value){
        return answersService.test(value);
    }
}
