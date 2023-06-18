package com.example.backend.Service;

import com.example.backend.Entity.Order;
import com.example.backend.Entity.Stock;
import com.example.backend.Entity.User;
import com.example.backend.Repository.OrderRepository;
import com.example.backend.Repository.StockRepository;
import com.example.backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;
    @Override
    public Order createOrder(List<String> orderedStocks) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        Order order = new Order();
        order.setDateOfOrder(LocalDateTime.now());
//        order.setOrderingUser(user);
        List<Stock> stock = new ArrayList<>();
        for (String str : orderedStocks) {
            stock.add(stockRepository.findByStockName(str));
        }
        order.setOrderedItems(stock);
        return orderRepository.save(order);
    }

    @Override
    public String successFullOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Nie znaleziono zamowienia"));
        for(Stock ord : order.getOrderedItems()){
            ord.setStockAvailable(ord.getStockAvailable()+1);
        }
        orderRepository.delete(order);
        return "Success deleted and added to stock";
    }

    @Override
    public String orderNotSuccedeed(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Nie znaleziono zamowienia"));
        orderRepository.delete(order);
        return "Order deleted";
    }
}
