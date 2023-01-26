package pl.k4t.ideas100.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.domain.model.Answer;
import pl.k4t.ideas100.service.AnswerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("questions/{question-id}/answers")
public class AnswerController {

    private final AnswerService answersService;

    public AnswerController(AnswerService answersService) {
        this.answersService = answersService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("question-id") UUID questionId){
        return answersService.getAnswers(questionId);
    }

    @GetMapping("{answer-id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId,
                     @PathVariable("answer-id") UUID answerId){
        return  answersService.getAnswer(answerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Answer createAnswer(@PathVariable("question-id") UUID questionId ,@RequestBody Answer answer){
        return answersService.createAnswer(questionId, answer);
    }


    @PutMapping("{answer-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Answer updateAnswer(@PathVariable("question-id") UUID questionId,
                        @PathVariable("answer-id") UUID answerId,
                        @RequestBody Answer answer){
        return answersService.updateAnswer(answerId, answer);
    }

    @DeleteMapping("{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAnswer(@PathVariable("answer-id") UUID answerId){
        answersService.deleteAnswer(answerId);
    }
}
