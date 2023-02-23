package pl.k4t.ideas100.question.domain.model;

import lombok.Data;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import pl.k4t.ideas100.category.domain.model.Category;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @ManyToOne
        private Category category;

    @OneToMany(mappedBy = "question")

        private Set<Answer> answers;

        private LocalDateTime created;

        private LocalDateTime modified;

    public Question() {

        this.id = UUID.randomUUID();
    }

    public Question(String name) {
        this();
        this.name = name;
    }

    @PrePersist
    void prePersist() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        modified = LocalDateTime.now();
    }

    public Question addAnswer(Answer answer){
        if (answers == null){
            answers = new LinkedHashSet<>();
        }
        answer.setQuestion(this);
        answers.add(answer);
        return this;
    }
}