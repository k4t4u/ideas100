package pl.k4t.ideas100.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.k4t.ideas100.common.controller.Ideas100CommonViewController;
import pl.k4t.ideas100.category.domain.model.Category;
import pl.k4t.ideas100.common.dto.Message;
import pl.k4t.ideas100.question.domain.model.Question;
import pl.k4t.ideas100.category.service.CategoryService;
import pl.k4t.ideas100.service.QuestionService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryViewController extends Ideas100CommonViewController {

	private final CategoryService categoryService;
	private final QuestionService questionService;

	@GetMapping("{id}")
	public String singleView(@PathVariable UUID id, Model model){
		Category category = categoryService.getCategory(id);
		List<Question> questions = questionService.findAllByCategoryId(id);

		model.addAttribute("category", category);
		model.addAttribute("questions", questions);
		addGlobalAttributes(model);

		return "category/single";
	}

	@GetMapping("{id}/add")
	public String addQuestionView(@PathVariable UUID id, Model model) {
		model.addAttribute("category", categoryService.getCategory(id));
		Question question = new Question();
		question.setCategory(categoryService.getCategory(id));
		model.addAttribute("question", question);
		return "question/add";
	}

	@PostMapping("{id}/add")
	public String addQuestion(@PathVariable UUID id,
							  @Valid @ModelAttribute("question") Question question,
							  BindingResult bindingResult,
							  RedirectAttributes ra,
							  Model model) {
		if(bindingResult.hasErrors()){
			model.addAttribute("category", categoryService.getCategory(id));
			model.addAttribute("question", question);
		}
		try {
			model.addAttribute("question", questionService.createQuestion(question));
			ra.addFlashAttribute("message", Message.info("Question has been added"));
		}catch(Exception e) {
			log.error("Error on question add", e);
			model.addAttribute("question", question);
			ra.addFlashAttribute("message", Message.info("Unknown error occurred on question add."));
			return "question/add";
		}
		return "redirect:/categories/{id}";
	}

}
