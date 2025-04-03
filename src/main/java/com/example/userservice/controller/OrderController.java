package com.example.userservice.controller;

import com.example.userservice.model.Order;
import com.example.userservice.model.User;
import com.example.userservice.service.OrderService;
import com.example.userservice.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Error processing request: {}", e.getMessage(), e);
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        try {
            Order createOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Failed to create user: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<Boolean> sendOrder(@PathVariable Long id) {
        System.out.println("ID recibido: " + id); // Debug

        boolean result = orderService.sendOrder(id);
        return result ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

}
