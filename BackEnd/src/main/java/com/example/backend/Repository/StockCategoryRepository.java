package com.example.backend.Repository;

import com.example.backend.Entity.StockCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCategoryRepository extends CrudRepository<StockCategory, Integer> {
    StockCategory findByCategoryname (String categoryName);
}
