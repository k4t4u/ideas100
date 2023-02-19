package pl.k4t.ideas100.question.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.question.domain.model.Question;
import pl.k4t.ideas100.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionApiController {

    private final QuestionService questionsService;

    public QuestionApiController(QuestionService questionsService) {

        this.questionsService = questionsService;
    }

    @GetMapping
    List<Question> getQuestions(Pageable pageable){

        return questionsService.getQuestions();
    }

    @GetMapping("{question-id}")
    Question getAnswer(@PathVariable("question-id") UUID questionId,
                     @PathVariable("answer-id") UUID answerId){
        return  questionsService.getQuestion(questionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@PathVariable("category-id") UUID categoryId, @RequestBody Question question){

        return questionsService.createQuestion(categoryId, question);
    }

    @PutMapping("{question-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Question updateQuestion(
            @PathVariable("category-id") UUID categoryId,
            @PathVariable("question-id") UUID questionId,
            @RequestBody Question question){
        return questionsService.updateQuestion(questionId, question);
    }

    @DeleteMapping("{question-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable("question-id") UUID questionId){

        questionsService.deleteQuestion(questionId);
    }
}
