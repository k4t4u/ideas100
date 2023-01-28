package pl.k4t.ideas100.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.k4t.ideas100.domain.model.Answer;
import pl.k4t.ideas100.service.AnswerService;

import java.util.UUID;

@Controller
@RequestMapping("/admin/answers")
public class AnswerAdminViewController {

	private final AnswerService answerService;

	public AnswerAdminViewController(AnswerService answerService) {

		this.answerService = answerService;
	}

	@GetMapping
	public String indexView(Model model){
		model.addAttribute("answers", answerService.getAnswers(UUID.randomUUID()));

		return "admin/answer/index";
	}

	@GetMapping("{id}")
	public String editView(Model model, @PathVariable UUID id){
		model.addAttribute("answer", answerService.getAnswer(id));

		return "admin/answer/edit";
	}

	@PostMapping("{id}")
	public String edit(@ModelAttribute("answer") Answer answer, @PathVariable UUID id){
		answerService.updateAnswer(id, answer);

		return "redirect:/admin/answers";
	}

}
