package com.example.fullStackbackEnd.controller;
import com.example.fullStackbackEnd.exception.ProductNotFoundException;
import com.example.fullStackbackEnd.model.Branch;
import com.example.fullStackbackEnd.model.Product;
import com.example.fullStackbackEnd.model.ProductDTO;
import com.example.fullStackbackEnd.repository.BranchRepository;
import com.example.fullStackbackEnd.repository.ProductRepository;
import jdk.jfr.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    @PostMapping("/product")
    Product newProduct(@RequestBody ProductDTO newProduct){
        Optional<Branch> branch = branchRepository.findById(Long.valueOf(newProduct.getBranch_id()));
        try {
            Product product = new Product(newProduct.getId(), newProduct.getName(), branch.get());
            return productRepository.save(product);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new Product();
    }

    @GetMapping("/products")
    List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable  Long id){
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
    }

    @PutMapping("/product/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return productRepository.findById(id)
                .map(product -> {
                    product.setId(newProduct.getId());
                    product.setName(newProduct.getName());
                    return productRepository.save(product);
                }).orElseThrow(()->new ProductNotFoundException(id));
    }

    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException((id));
        }
        productRepository.deleteById(id);
        return "Product with id" +id+"has been deleted successfully";
    }
}