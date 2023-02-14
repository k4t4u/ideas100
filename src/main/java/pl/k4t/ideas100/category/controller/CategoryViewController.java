package pl.k4t.ideas100.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.k4t.ideas100.common.controller.Ideas100CommonViewController;
import pl.k4t.ideas100.category.domain.model.Category;
import pl.k4t.ideas100.question.domain.model.Question;
import pl.k4t.ideas100.category.service.CategoryService;
import pl.k4t.ideas100.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
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
}
