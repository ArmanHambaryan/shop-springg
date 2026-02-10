package am.itspace.shopspring.service.impl;

import am.itspace.shopspring.model.Product;
import am.itspace.shopspring.repository.ProductRepository;
import am.itspace.shopspring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${shop.upload.images.directory.path}")
    private String imageDirectoryPath;
    private final ProductRepository productRepository;

    @Override
    public void save(Product product, MultipartFile multipartFile) {
        if (!multipartFile.isEmpty() && multipartFile != null) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                product.setPictureName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategoryId(Integer id) {
        return productRepository.findAllByCategoryId(id);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

}
