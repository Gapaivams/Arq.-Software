package br.com.mvc.model.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvc.model.entity.Stock;
import br.com.mvc.model.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> read() {
        return stockRepository.findAll();
    }

    /** Entrada de mercadoria: soma a quantidade ao estoque. */
    @Transactional
    public Stock increase(BigInteger productId, BigInteger amount) {
        validateAmount(amount);
        return changeQuantity(productId, amount);
    }

    /** Saída de mercadoria: subtrai a quantidade do estoque. */
    @Transactional
    public Stock decrease(BigInteger productId, BigInteger amount) {
        validateAmount(amount);
        return changeQuantity(productId, amount.negate());
    }

    private Stock changeQuantity(BigInteger productId, BigInteger delta) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Produto sem estoque"));

        BigInteger newQuantity = stock.getQuantity().add(delta);
        if (newQuantity.signum() < 0) {
            throw new RuntimeException("Quantidade insuficiente em estoque");
        }

        stock.setQuantity(newQuantity);
        return stockRepository.save(stock);
    }

    private void validateAmount(BigInteger amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero");
        }
    }
}