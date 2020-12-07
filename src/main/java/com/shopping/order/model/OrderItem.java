package com.shopping.order.model;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @EmbeddedId
    private OrderItemId id = new OrderItemId();

    @ManyToOne()
    @MapsId("orderId")
    private Order order;

    @ManyToOne()
    @MapsId("itemId")
    private Item item;

    private Double quantity;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
