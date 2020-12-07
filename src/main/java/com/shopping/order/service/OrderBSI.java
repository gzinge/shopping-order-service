package com.shopping.order.service;

import com.shopping.order.model.ItemData;
import com.shopping.order.model.OrderReviewDetails;

import java.util.List;

public interface OrderBSI {

    public int createOrder(List<ItemData> orderData, Long userId) throws Exception;

    public Double getTotalPrice(List<ItemData> orderData) throws Exception;

    public OrderReviewDetails getOrderReviewDetails(Long orderId) throws Exception;

    public int updateOrder(List<ItemData> orderData, Long orderId) throws Exception;

    public List<OrderReviewDetails> getAllOrderReviewDetails() throws Exception;
}
