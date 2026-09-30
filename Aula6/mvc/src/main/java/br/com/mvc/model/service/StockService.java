package br.com.mvc.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mvc.model.entity.Stock;
import br.com.mvc.model.repository.StockRepository;

@Service
public class StockService {
    
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    public Stock changeQuantity(BigInteger productId, BigInteger quantity){
        Stock stock = stockRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto sem estoque"));
        BigInteger newQuantity = stock.getQuantity().add(quantity);
        if(newQuantity.compareTo(BigInteger.ZERO) < 0){
            throw new RuntimeException("Quantidade insuficiente em estoque");
        }
        stock.setQuantity(newQuantity);
        return stockRepository.save(stock);
    }

    public List<Stock> read(){
        return stockRepository.findAll();
    }

}
