package am.itspace.shopspring.repository;

import am.itspace.shopspring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategoryId(Integer id);

    Optional<Product> findById(Integer id);


}
