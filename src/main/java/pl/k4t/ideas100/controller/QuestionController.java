package pl.k4t.ideas100.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.domain.model.Question;
import pl.k4t.ideas100.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionsService;

    public QuestionController(QuestionService questionsService) {

        this.questionsService = questionsService;
    }

    @GetMapping
    List<Question> getQuestions(){

        return questionsService.getQuestions();
    }

    @GetMapping("{id}")
    Question getQuestion(@PathVariable UUID id){
        return  questionsService.getQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@RequestBody Question question){
        return questionsService.createQuestion(question);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Question updateQuestion(@PathVariable UUID id, @RequestBody Question question){
        return questionsService.updateQuestion(id, question);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable UUID id){
        questionsService.deleteQuestion(id);
    }
}
