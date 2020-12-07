package com.shopping.order.model;

import java.util.List;

public class OrderReviewDetailsWrapper {

    private List<OrderReviewDetails> orderReviewDetailsList;

    public OrderReviewDetailsWrapper() {
    }

    public List<OrderReviewDetails> getOrderReviewDetailsList() {
        return orderReviewDetailsList;
    }

    public void setOrderReviewDetailsList(List<OrderReviewDetails> orderReviewDetailsList) {
        this.orderReviewDetailsList = orderReviewDetailsList;
    }
}
