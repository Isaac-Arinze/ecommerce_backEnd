package com.skytech.e_store.service;

import com.skytech.e_store.dto.product.ProductDto;
import com.skytech.e_store.exceptions.ProductNotExistException;
import com.skytech.e_store.model.Category;
import com.skytech.e_store.model.Product;
import com.skytech.e_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public static Product getProductFromDto (ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }
    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }
    // list of all the products
    public List<ProductDto> listProducts() {
        // first fetch all the products
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            // for each product change it to DTO
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }
    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        // set the id for updating
        product.setId(productID);
        // update
        productRepository.save(product);
    }
        public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }

}

