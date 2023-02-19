package pl.k4t.ideas100.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.k4t.ideas100.common.dto.Message;
import pl.k4t.ideas100.question.domain.model.Answer;
import pl.k4t.ideas100.question.domain.model.Question;
import pl.k4t.ideas100.question.domain.repository.QuestionRepository;
import pl.k4t.ideas100.service.QuestionService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static pl.k4t.ideas100.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/admin/questions")
@Slf4j
public class QuestionAdminViewController {
	private final QuestionRepository questionRepository;

	private final QuestionService questionService;

	public QuestionAdminViewController(QuestionService questionService,
									   QuestionRepository questionRepository) {

		this.questionService = questionService;
		this.questionRepository = questionRepository;
	}

	@GetMapping
	public String indexView(
			@RequestParam(name = "s", required = false) String search,
			@RequestParam(name = "field", required = false, defaultValue = "id") String field,
			@RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			Model model
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), field);
		List<Question> questionsPage = questionService.getQuestions();
		String reverseSort = null;
		if ("asc".equals(direction)) {
			reverseSort = "desc";
		} else {
			reverseSort = "asc";
		}

		model.addAttribute("questions", questionService.getQuestions());
		model.addAttribute("answersPage", questionsPage);
		model.addAttribute("search", search);
		model.addAttribute("reverseSort", reverseSort);

		paging(model, questionRepository.findAll(pageable));

		return "admin/question/index";
	}

	@GetMapping("{id}")
	public String editView(Model model, @PathVariable UUID id) {
		model.addAttribute("answer", questionService.getQuestion(id));

		return "admin/question/edit";
	}

	@PostMapping("{id}")
	public String edit(
			@PathVariable UUID id,
			@Valid @ModelAttribute("question") Question question,
			BindingResult bindingResult,
			RedirectAttributes ra,
			Model model
	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			model.addAttribute("message", Message.error("Save error"));
			return "admin/question/edit";
		}
		try {
			questionService.updateQuestion(id, question);
			ra.addFlashAttribute("message", Message.info("Question saved"));
		} catch (Exception e) {
			model.addAttribute("question", question);
			model.addAttribute("message", Message.error("Unknown save error"));
			return "admin/question/edit";
		}

		return "redirect:/admin/questions";
	}

	@GetMapping("{id}/delete")
	public String deleteView(@PathVariable UUID id, RedirectAttributes ra) {
		try {
			questionService.deleteQuestion(id);
			ra.addFlashAttribute("message", Message.info("Question deleted"));
		} catch (Exception e) {
			log.error("Error on Question.delete", e);
			ra.addFlashAttribute("message", Message.error("Unknown delete error"));
			return "redirect:/admin/questions";
		}
		return "redirect:/admin/questions";
	}
}