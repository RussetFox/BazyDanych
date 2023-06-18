package com.example.backend.Service;

import com.example.backend.Entity.StockCategory;
import com.example.backend.Repository.StockCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockCategoryServiceImpl implements StockCategoryService{
    private final StockCategoryRepository stockCategoryRepository;
   public StockCategory saveCategory(StockCategory stockCategory){
       return stockCategoryRepository.save(stockCategory);
   }

    @Override
    public List<String> getAllCategoriesNames() {
        List<StockCategory> allCategories = (List<StockCategory>)stockCategoryRepository.findAll();
        List<String> nameList = new ArrayList<>();
        for(StockCategory sc : allCategories){
            nameList.add(sc.getCategoryname());
        }
        return nameList;
    }

    @Override
    public List<StockCategory> getAllCategories() {
        return (List<StockCategory>)stockCategoryRepository.findAll();
    }
}
