package am.itspace.shopspring.service;

import am.itspace.shopspring.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product, MultipartFile multipartFile);

    void deleteById(Integer id);

    List<Product> findAll();

    List<Product> findAllByCategoryId(Integer id);

    Optional<Product    > findById(Integer id);
}



