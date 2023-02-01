package pl.k4t.ideas100.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.k4t.ideas100.common.dto.Message;
import pl.k4t.ideas100.domain.model.Category;
import pl.k4t.ideas100.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

	private final CategoryService categoryService;

	public CategoryAdminViewController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public String indexView(
			@RequestParam(name = "s", required = false) String search,
			Pageable pageable,
			Model model
	){
		Page<Category> categoriesPage = categoryService.getCategories(pageable);
		model.addAttribute("categoriesPage", categoriesPage);
		model.addAttribute("search", search);
		paging(model, categoriesPage);

		return "admin/category/index";
	}

	@GetMapping("{id}")
	public String editView(Model model, @PathVariable UUID id){
		model.addAttribute("category", categoryService.getCategory(id));

		return "admin/category/edit";
	}

	@PostMapping("{id}")
	public String edit(
			@PathVariable UUID id,
			@Valid @ModelAttribute("category") Category category,
			BindingResult bindingResult,
			RedirectAttributes ra,
			Model model
						){
		if (bindingResult.hasErrors()){
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Save error"));
			return "admin/category/edit";
		}
		try {
			categoryService.updateCategory(id, category);
			ra.addFlashAttribute("message", Message.info("Category saved"));
		}catch (Exception e){
			model.addAttribute("category", category);
			model.addAttribute("message", Message.error("Unknown save error"));
			return "admin/category/edit";
		}

		return "redirect:/admin/categories";
	}

	@GetMapping("{id}/delete")
	public String deleteView(@PathVariable UUID id, RedirectAttributes ra) {
		categoryService.deleteCategory(id);
		ra.addFlashAttribute("message", Message.info("Category deleted"));
		return "redirect:/admin/categories";
	}

	private void paging(Model model, Page page){
		int totalPages = page.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
	}
}
