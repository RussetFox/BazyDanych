package com.example.backend.Controller;

import com.example.backend.Entity.Order;
import com.example.backend.Entity.Stock;
import com.example.backend.Entity.StockCategory;
import com.example.backend.Service.OrderService;
import com.example.backend.Service.StockCategoryService;
import com.example.backend.Service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    private final StockCategoryService stockCategoryService;
    private final OrderService orderService;

    @PostMapping("/create/{category}")
    public ResponseEntity<Stock> createContent(@RequestBody Stock stock, @PathVariable String category) {
        return stockService.createStockItem(stock, category);
    }
    @PostMapping("/category/create")
    public StockCategory createCategory(@RequestBody StockCategory stockCategory){
        return stockCategoryService.saveCategory(stockCategory);
    }

    @PostMapping ("/create/order")
    public Order createdOrder(@RequestBody List<String> stocks){
        return orderService.createOrder(stocks);
    }

    @DeleteMapping("/order/notsucc/{id}")
    public String orderNotSuccessfull(@PathVariable Integer id){
        return orderService.orderNotSuccedeed(id);
    }

    @DeleteMapping("/order/succ/{id}")
    public String orderSuccessfull(@PathVariable Integer id){
        return orderService.successFullOrder(id);
    }
    @GetMapping("/getall")
    public List<Stock> getAllStock(){
        return stockService.getAllStock();
    }

    @GetMapping("/getall/{category}")
    public List<Stock> getAllStockInCategory(@PathVariable String category){
        return stockService.getAllStockInCategory(category);
    }

    @GetMapping("/categories/getall/asobjects")
    public List<StockCategory> getAllCategories(){
        return stockCategoryService.getAllCategories();
    }
    @GetMapping("/categories/getall/names")
    public List<String> getAllCategoriesNames(){
        return stockCategoryService.getAllCategoriesNames();
    }

    @PutMapping("/edit/{stockid}")
    public Optional<Stock> editStock(@RequestBody Stock stock, @PathVariable Integer stockid){
        return stockService.editStockInfo(stock, stockid);

    }
}
