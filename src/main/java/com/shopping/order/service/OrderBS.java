package com.shopping.order.service;

import com.shopping.order.feignclient.ItemFeignClient;
import com.shopping.order.feignclient.UserFeignClient;
import com.shopping.order.model.*;
import com.shopping.order.repository.OrderItemRepository;
import com.shopping.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderBS implements OrderBSI {

    Logger logger = LoggerFactory.getLogger(OrderBS.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemFeignClient itemFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public int createOrder(List<ItemData> orderData, Long userId) throws Exception {
        if (orderData != null && !orderData.isEmpty() && userId != null) {
            Order newOrder = new Order();
            createOrUpdateOrder(orderData, userId, newOrder);
            return 1;
        }
        return 0;
    }


    private void createOrUpdateOrder(List<ItemData> orderData, Long userId, Order newOrder) throws Exception {
        Set<Item> items = new HashSet<>();

        //Here we need to connect ItemService afterwards.
        for (ItemData itemData : orderData) {
            Item item = getItemByIdFromItemService(itemData.getItemId());
            if(item != null) {
                itemData.setPrice(item.getPrice());
                items.add(item);
            }
        }
        newOrder.setItems(items);

        //Here we need to connect userService afterwards.
        User user = getUserByIdFromUserService(userId);
        if(user != null) {
            newOrder.setUser(user);
        }

        newOrder.setOrderCost(getTotalPrice(orderData));

        orderRepository.save(newOrder);

        logger.info("------------------ order id : "+newOrder.getId());
        for (ItemData itemData : orderData) {
            OrderItemId orderItemId = new OrderItemId(newOrder.getId(), itemData.getItemId());
            Optional<OrderItem> opt =  orderItemRepository.findById(orderItemId);
            if(opt.isPresent()) {
                OrderItem orderItem = opt.get();
                orderItem.setQuantity(itemData.getOrderQty());
                orderItemRepository.save(orderItem);
            }
        }

    }

    public Item getItemByIdFromItemService(Long itemId) {
        return itemFeignClient.getItemByIdFromItemService(itemId);
    }

    public User getUserByIdFromUserService(Long userId) {
        return userFeignClient.getUserByIdFromUserService(userId);
    }

    @Override
    public Double getTotalPrice(List<ItemData> orderData) throws Exception {
        Double totalCost = 0.0;
        for (ItemData itemData : orderData) {
            totalCost = totalCost + (itemData.getPrice() * itemData.getOrderQty());
        }
        return totalCost;
    }

    @Override
    public OrderReviewDetails getOrderReviewDetails(Long orderId) throws Exception {
        if (orderId != null) {
            OrderReviewDetails orderReviewDetails = null;
            Optional<Order> opt = orderRepository.findById(orderId);
            if (opt.isPresent()) {
                orderReviewDetails = new OrderReviewDetails();
                Order order = opt.get();
                orderReviewDetails.setOrderId(order.getId());
                populateItemReviewDataSet(orderReviewDetails, order);
                orderReviewDetails.setUserId(order.getUser().getId());
                orderReviewDetails.setOrderCost(order.getOrderCost());
                return orderReviewDetails;
            }
        }
        return null;
    }

    private void populateItemReviewDataSet(OrderReviewDetails orderReviewDetails, Order order) {
        Set<ItemReviewData> itemReviewDataSet = new HashSet<>();
        Set<Item> items = order.getItems();
        for(Item item : items) {
            ItemReviewData itemReview = new ItemReviewData();
            itemReview.setId(item.getId());
            itemReview.setPrice(item.getPrice());
            itemReview.setImage(item.getImage());
            itemReview.setName(item.getName());
            itemReview.setCategory(item.getCategory());
            itemReview.setQtyAvailable(item.getQtyAvailable());
            itemReview.setDescription(item.getDescription());

            OrderItemId orderItemId = new OrderItemId(order.getId(), item.getId());
            Optional<OrderItem> opto = orderItemRepository.findById(orderItemId);
            if(opto.isPresent()) {
               OrderItem orderItem =  opto.get();
               itemReview.setOrderQty(orderItem.getQuantity());
            }
            itemReviewDataSet.add(itemReview);
        }
        orderReviewDetails.setItemReview(itemReviewDataSet);
    }

    @Override
    public int updateOrder(List<ItemData> orderData, Long orderId) throws Exception {
        if (orderId != null) {
            Optional<Order> opt = orderRepository.findById(orderId);
            if (opt.isPresent()) {
                Order order = opt.get();
                createOrUpdateOrder(orderData, order.getUser().getId(), order);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<OrderReviewDetails> getAllOrderReviewDetails() throws Exception {
        List<Order> orders = orderRepository.findAll();
        List<OrderReviewDetails> allOrderReviews = new ArrayList<>();

        for(Order order : orders) {
            if(order != null) {
                OrderReviewDetails orderReviewDetails = new OrderReviewDetails();
                orderReviewDetails.setOrderId(order.getId());
                populateItemReviewDataSet(orderReviewDetails, order);
                orderReviewDetails.setUserId(order.getUser().getId());
                orderReviewDetails.setOrderCost(order.getOrderCost());
                allOrderReviews.add(orderReviewDetails);
            }
        }
        return allOrderReviews;
    }
}
