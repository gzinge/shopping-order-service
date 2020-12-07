package com.shopping.order.repository;

import com.shopping.order.model.OrderItem;
import com.shopping.order.model.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
