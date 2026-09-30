package br.com.mvc.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.model.entity.Stock;
import br.com.mvc.model.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping
    public String read(Model model){
        model.addAttribute("stocks", stockService.read());
        model.addAttribute("stock", Stock.builder().build());
        return "stocks";

    }

    @PostMapping("/stock/{productId}")
    public String updateStock(@PathVariable BigInteger productId, @RequestParam BigInteger amount) {
        stockService.changeQuantity(productId, amount);
        return "redirect:/stock";
    }

}
