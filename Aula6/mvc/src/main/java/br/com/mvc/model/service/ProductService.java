package br.com.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mvc.model.entity.Product;
import br.com.mvc.model.repository.ProductRepository;
import br.com.mvc.model.repository.StockRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;


    public ProductService(ProductRepository productRepository, StockRepository stockRepository){
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public Product create(Product product){

        Product savedProduct =  productRepository.save(product);
        Stock stock = Stock.builder().product(savedProduct).quantity(0).build();
        stockRepository.save(stock);
        return savedProduct;
    }   

    public List<Product> read(){
        return productRepository.findAll();
    }

}
