package com.example.backend.Service;

import com.example.backend.Entity.StockCategory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StockCategoryService {
    StockCategory saveCategory(StockCategory stockCategory);
    List<String> getAllCategoriesNames();
    List<StockCategory> getAllCategories();
}
