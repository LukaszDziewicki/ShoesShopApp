package webapplication.ShoesShopApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.repository.ProductRepository;

import java.util.List;

@Service
public class FilterServiceImp {


    @Autowired
    private ProductRepository productRepository;

    public List<Product> filterByCategory(Category category) {
        return productRepository.filterByCategory(category.getCategoryName());
    }
}
