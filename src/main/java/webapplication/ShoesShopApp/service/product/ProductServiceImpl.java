package webapplication.ShoesShopApp.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;




    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product, MultipartFile[] file) {
        int count = 0;
        for (MultipartFile images: file
        ) {
            if(count == 0){
                product.setPrimaryImage(images.getOriginalFilename());
            }
            if(count == 1){
                product.setSecondImage(images.getOriginalFilename());
            }
            if(count == 2){
                product.setThirdImage(images.getOriginalFilename());
            }
            if(count == 3){
                product.setFourthImage(images.getOriginalFilename());
            }
            count++;
        }

        productRepository.save(product);

    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }


    public List<Product> getAscList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public List<Product> getDescList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    public List<Product> getAlphabeticallySortedList(){
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(product -> product.getProductName().toLowerCase()));
        return products;

    }

    public List<Product> findByCategory(String category){
        return productRepository.filterByCategory(category);
    }

    public List<Product> getFilteredByCategory(Collection<String> listOfCategoryName){
        List<Product> productList = productRepository.findByCategoryCategoryNameIn(listOfCategoryName);
        return productList;
    }

    public List<Product> getFilteredBySize(Collection<String> listOfSizesValue){
        List<Product> productList = productRepository.findBySizesValueIn(listOfSizesValue);
        return productList;
    }

    public List<Product> getFilteredByColor(Collection<String> listOfColorName){
        List<Product> productList = productRepository.findByColorsColorNameIn(listOfColorName);
        return productList;
    }

    public  List<Product> getFilteredBySizesAndCategoryAndColors(Collection<String> listOfSizesValue,
                                                                 Collection<String> listOfCategoryName,
                                                                 Collection<String> listOfColorName){
        List<Product> productList = productRepository
                .findBySizesValueInAndCategoryCategoryNameInAndColorsColorNameIn(
                        listOfSizesValue,
                        listOfCategoryName,
                        listOfColorName);
        return productList;
}


   /* public boolean checkIfProductExist(int i, List<Product> productList) {



        if (productList.get(i).getProductName().equals(productList.get(i + 1).getProductName()) &&
                productList.get(i).getCategory().equals(productList.get(i + 1).getCategory()) &&
                productList.get(i).getPrice().equals(productList.get(i + 1).getPrice()) &&
                productList.get(i).getColors().equals(productList.get(i + 1).getColors()) &&
                productList.get(i).getSizes().equals(productList.get(i + 1).getSizes())) {
            int suma = productList.get(i).getAmount() + productList.get(i + 1).getAmount();
            productList.get(i).setAmount(suma);

            productList.remove(i + 1);
            return true;

        }
        return false;


    }

    public boolean checkIfNotEqual(int i, List<Product> productList) {

         if(productList.get(i).getProductName().equals(productList.get(i + 1).getProductName()) &&
                productList.get(i).getCategory().equals(productList.get(i + 1).getCategory()) &&
                productList.get(i).getPrice().equals(productList.get(i + 1).getPrice()) &&
                productList.get(i).getColors().equals(productList.get(i + 1).getColors()) &&
                !(productList.get(i).getSizes().equals(productList.get(i + 1).getSizes()))){
            Set<Size> sizes = productList.get(i).getSizes();
            sizes.addAll(productList.get(i + 1).getSizes());
            int suma = productList.get(i).getAmount() + productList.get(i + 1).getAmount();
            productList.get(i).setAmount(suma);
            productList.remove(i + 1);



            productList.get(i).setSizes(sizes);
            return true;
        }
         return false;


    }

    public void addAmountAndSetSizes(List<Product> products){

        for (int i = 0; i < products.size() + 6; i++) {

            if(checkIfProductExist(i,products)){
                int suma = products.get(i).getAmount() + products.get(i + 1).getAmount();
                products.get(i).setAmount(suma);

                products.remove(i + 1);

            }
            else if(checkIfNotEqual(i,products)){
                Set<Size> sizes = products.get(i).getSizes();
                sizes.addAll(products.get(i + 1).getSizes());
                int suma = products.get(i).getAmount() + products.get(i + 1).getAmount();
                products.get(i).setAmount(suma);
                products.remove(i + 1);
                products.get(i).setSizes(sizes);
            }
        }
    }*/

   public void editSpecificProduct(long id, ProductDto productDto){
       Product product = getProductById(id);
       product.setProductName(productDto.getProductName());
       product.setAmount(productDto.getAmount());
       product.setPrice(productDto.getPrice());
       product.setCategory(productDto.getCategory());
       product.setSizes(productDto.getSizes());
       product.setColors(productDto.getColors());
       productRepository.save(product);

   }
}


