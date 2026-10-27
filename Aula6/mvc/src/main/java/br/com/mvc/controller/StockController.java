package br.com.mvc.controller;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mvc.model.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public String read(Model model) {
        model.addAttribute("stocks", stockService.read());
        return "stock";
    }

    @PostMapping("/{productId}/in")
    public String increase(@PathVariable BigInteger productId, @RequestParam BigInteger amount) {
        stockService.increase(productId, amount);
        return "redirect:/stock";
    }

    @PostMapping("/{productId}/out")
    public String decrease(@PathVariable BigInteger productId, @RequestParam BigInteger amount) {
        stockService.decrease(productId, amount);
        return "redirect:/stock";
    }
}