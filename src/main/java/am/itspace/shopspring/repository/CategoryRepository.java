package am.itspace.shopspring.repository;

import am.itspace.shopspring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
