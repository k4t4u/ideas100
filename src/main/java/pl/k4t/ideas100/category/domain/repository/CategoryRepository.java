package pl.k4t.ideas100.category.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import pl.k4t.ideas100.category.dto.CategoryWithStatisticsDto;
import pl.k4t.ideas100.category.domain.model.Category;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "select new pl.k4t.ideas100.category.dto.CategoryWithStatisticsDto(" +
            "c.id, c.name, count(q), count(a)) " +
            "from Category c " +
            "left join c.questions q " +
            "left join q.answers a " +
            "group by c.id ")
    List<CategoryWithStatisticsDto> findAllWithStatistics();
}
