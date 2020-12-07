package com.shopping.order.model;

import java.util.List;
import java.util.Set;

public class OrderReviewDetails {

    private Long orderId;
    private Set<ItemReviewData> itemReview;
    private Double orderCost;
    private Long userId;

    public OrderReviewDetails() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Set<ItemReviewData> getItemReview() {
        return itemReview;
    }

    public void setItemReview(Set<ItemReviewData> itemReview) {
        this.itemReview = itemReview;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
