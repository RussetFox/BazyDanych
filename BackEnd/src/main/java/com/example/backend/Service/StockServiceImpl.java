package com.example.backend.Service;

import com.example.backend.Entity.Stock;
import com.example.backend.Entity.StockCategory;
import com.example.backend.Repository.StockCategoryRepository;
import com.example.backend.Repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockCategoryRepository stockCategoryRepository;

    @Override
    public List<Stock> getAllStock() {
        return (List<Stock>) stockRepository.findAll();
    }

    @Override
    public Optional<Stock> editStockInfo(Stock editedStock, Integer stockId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setStockAvailable(editedStock.getStockAvailable());
        stock.setStockName(editedStock.getStockName());
        stock.setStockDescription(editedStock.getStockDescription());
        stock.setCategory(editedStock.getCategory());
        return Optional.of(stockRepository.save(stock));
    }

    @Override
    public List<Stock> getAllStockInCategory(String category) {
       StockCategory sc = stockCategoryRepository.findByCategoryname(category);
       return sc.getStockInCategory();
    }

    @Override
    public ResponseEntity<Stock> createStockItem(Stock stock, String stockCategory) {
        StockCategory category = stockCategoryRepository.findByCategoryname(stockCategory);
        stock.setCategory(category);
        Stock savedStock = stockRepository.save(stock);
        return ResponseEntity.ok(savedStock);
    }
    @Override
    public void deleteStockById(Integer stockId) {
        stockRepository.deleteById(stockId);
    }
}
