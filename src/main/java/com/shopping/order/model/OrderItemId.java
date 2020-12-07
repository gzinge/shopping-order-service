package com.shopping.order.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {

    private Long orderId;
    private Long itemId;

    public OrderItemId() {
    }

    public OrderItemId(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
