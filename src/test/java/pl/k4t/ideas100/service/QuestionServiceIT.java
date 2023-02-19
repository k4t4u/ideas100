//package pl.k4t.ideas100.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//import pl.k4t.ideas100.category.domain.model.Category;
//import pl.k4t.ideas100.category.domain.repository.CategoryRepository;
//import pl.k4t.ideas100.question.domain.model.Answer;
//import pl.k4t.ideas100.question.domain.model.Question;
//import pl.k4t.ideas100.question.domain.repository.AnswerRepository;
//import pl.k4t.ideas100.question.domain.repository.QuestionRepository;
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//@SpringBootTest
//@Transactional
//@Rollback
//class QuestionServiceIT {
//
//	@Autowired
//	private QuestionService questionService;
//
//	@Autowired
//	private QuestionRepository questionRepository;
//
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private AnswerRepository answerRepository;
//
//	@Test
//	void shouldGetAllQuestions() {
//
//		questionRepository.deleteAll();
//
//		questionRepository.saveAll(List.of(
//				new Question("Question1"),
//				new Question("Question2"),
//				new Question("Question3")
//		));
//
//		List<Question> questions = questionService.getQuestions();
//
//		assertThat(questions)
//				.hasSize(3)
//				.extracting(Question::getName)
//				.containsExactlyInAnyOrder("Question1", "Question2", "Question3");
//	}
//
//	@Test
//	void shouldSingleGetQuestion() {
//
//		Question question = new Question("Question2");
//
//		questionRepository.saveAll(List.of(
//				new Question("Question1"),
//				question,
//				new Question("Question3")
//		));
//
//		Question result = questionService.getQuestion(question.getId());
//
//		assertThat(result.getId()).isEqualTo(question.getId());
//	}
//
//	@Test
//	void shouldCreateQuestion() {
//
//		Question question = new Question("Question");
//
//		Question result = questionService.createQuestion(question);
//
//		assertThat(result.getName()).isEqualTo(question.getName());
//		assertThat(result.getName()).isEqualTo(questionRepository.getById(result.getId()).getName());
//	}
//
//	@Test
//	void shouldUpdateQuestion() {
//
//		Question question = new Question("Question");
//		question = questionService.createQuestion(question);
//
//		question.setName("updated");
//
//		Question result = questionService.updateQuestion(question.getId(), question);
//
//		assertThat(result.getId()).isEqualTo(question.getId());
//		assertThat(result.getId()).isEqualTo(questionRepository.getById(question.getId()).getId());
//	}
//
//	@Test
//	void shouldDeleteQuestion() {
//
//		Question question = new Question("Question");
//		question = questionService.createQuestion(question);
//		UUID id = question.getId();
//
//		Throwable throwable = catchThrowable(() -> questionService.deleteQuestion(id));
//
//		assertThat(questionRepository.findById(question.getId())).isEmpty();
//	}
//
//	@Test
//	void shouldFindAllByCategoryId() {
//
//		Category category = new Category("Category1");
//		categoryRepository.save(category);
//
//		Question question1 = new Question("Question1");
//		question1.setCategory(category);
//
//		Question question2 = new Question("Question2");
//		question2.setCategory(category);
//
//		Question question3 = new Question("Question3");
//		questionRepository.saveAll(List.of(question1, question2, question3));
//
//		List<Question> questions = questionService.findAllByCategoryId(category.getId());
//
//		assertThat(questions)
//				.hasSize(2)
//				.extracting(Question::getName)
//				.containsExactlyInAnyOrder("Question1", "Question2");
//	}
//
//	@Test
//	void shouldFindHot() {
//
//		questionRepository.deleteAll();
//
//		Question question1 = new Question("Question1");
//		Question question2 = new Question("Question2");
//		Question question3 = new Question("Question3");
//
//		questionRepository.saveAll(List.of(question1, question2, question3));
//
//		Answer answer = new Answer("Answer");
//		question2.addAnswer(answer);
//		answerRepository.save(answer);
//
//		Page<Question> result = questionService.findHot(Pageable.unpaged());
//
//		assertThat(result)
//				.hasSize(3)
//				.extracting(Question::getName)
//				.containsExactlyInAnyOrder("Question2", "Question1", "Question3");
//	}
//
//	@Test
//	void shouldFindUnanswered() {
//
//		questionRepository.deleteAll();
//
//		Question question1 = new Question("Question1");
//		Question question2 = new Question("Question2");
//		Question question3 = new Question("Question3");
//
//		questionRepository.saveAll(List.of(question1, question2, question3));
//
//		Answer answer = new Answer("Answer");
//		question2.addAnswer(answer);
//		answerRepository.save(answer);
//
//		Page<Question> result = questionService.findUnanswered(Pageable.unpaged());
//
//		assertThat(result)
//				.hasSize(2)
//				.extracting(Question::getName)
//				.containsExactlyInAnyOrder("Question1", "Question3");
//	}
//
//	@Test
//	void shouldFindByQuery() {
//
//		String query = "abc";
//
//		Question question1 = new Question("Question1");
//		Question question2 = new Question("Question2-" + query);
//		Question question3 = new Question("Question3");
//
//		questionRepository.saveAll(List.of(question1, question2, question3));
//
//		Page<Question> result = questionService.findByQuery(query, Pageable.unpaged());
//
//		assertThat(result)
//				.hasSize(1)
//				.extracting(Question::getId)
//				.containsExactlyInAnyOrder(question2.getId());
//	}
//}