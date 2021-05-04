package webapplication.ShoesShopApp.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }
}
