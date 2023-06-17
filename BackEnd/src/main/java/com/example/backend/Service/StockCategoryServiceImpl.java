package com.example.backend.Service;

import com.example.backend.Entity.StockCategory;
import com.example.backend.Repository.StockCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class StockCategoryServiceImpl implements StockCategoryService{
    StockCategoryRepository stockCategoryRepository;
   public StockCategory saveCategory(StockCategory stockCategory){
       return stockCategoryRepository.save(stockCategory);
   }
}
