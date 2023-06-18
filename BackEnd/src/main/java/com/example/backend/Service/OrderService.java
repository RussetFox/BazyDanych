package com.example.backend.Service;

import com.example.backend.Entity.Order;
import com.example.backend.Entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order createOrder(List<String> orderedStocks);
    String successFullOrder(Integer orderId);
    String orderNotSuccedeed(Integer orderId);
}
