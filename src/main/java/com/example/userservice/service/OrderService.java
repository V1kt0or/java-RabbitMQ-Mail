package com.example.userservice.service;

import com.example.userservice.Dto.OrderSendEmailDto;
import com.example.userservice.messaging.MessagePublisher;
import com.example.userservice.model.EmailNotification;
import com.example.userservice.model.Order;
import com.example.userservice.model.User;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MessagePublisher messagePublisher;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MessagePublisher messagePublisher) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.messagePublisher = messagePublisher;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) {


        // Save user to database
        Order savedOrder = orderRepository.save(order);
        logger.info("Order created successfully: {}", savedOrder.getId());



        return savedOrder;
    }

    public  boolean sendOrder(Long id){
        try {
            // Create email notification
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            User user = userRepository.findById(order.getIdClient())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            OrderSendEmailDto orderSend = new OrderSendEmailDto (order.getAddress(), user.getName(), user.getEmail());
            EmailNotification notification = EmailNotification.forNewOrderRegistration(orderSend);

            // Send notification to RabbitMQ (o simplemente registra si RabbitMQ no está disponible)
            messagePublisher.publishEmailNotification(notification);
        } catch (Exception e) {
            // Log the error but don't fail the user creation
            logger.error("Failed to process email notification: {}", e.getMessage());
            return false;
        }
        return true;
    }


}
