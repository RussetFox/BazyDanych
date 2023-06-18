package com.example.backend.Service;

import com.example.backend.Entity.Stock;
import com.example.backend.Entity.StockCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StockService {

    List<Stock> getAllStock();

    Optional<Stock> editStockInfo(Stock editedStock, Integer stockId);

    List<Stock> getAllStockInCategory(String category);

    ResponseEntity<Stock> createStockItem(Stock stock, String categoryName);

    void deleteStockById(Integer stockId);
}
