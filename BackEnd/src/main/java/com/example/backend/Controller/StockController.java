package com.example.backend.Controller;

import com.example.backend.Entity.Stock;
import com.example.backend.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<Stock> createContent(@RequestBody Stock stock) {
        return stockService.createStockItem(stock);
    }

    @GetMapping("/")
    public String getHell() {
        return "Hell";
    }
}
