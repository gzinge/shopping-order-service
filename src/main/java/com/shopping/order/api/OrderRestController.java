package com.shopping.order.api;

import com.shopping.order.model.ItemData;
import com.shopping.order.model.OrderDataWrapper;
import com.shopping.order.model.OrderReviewDetails;
import com.shopping.order.model.OrderReviewDetailsWrapper;
import com.shopping.order.service.OrderBS;
import com.shopping.order.service.OrderBSI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderBSI orderBS;

    @PostMapping("/create/user/{userId}")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDataWrapper orderDataWrapper, @PathVariable("userId") Long userId) throws Exception {
        try {
            int i = orderBS.createOrder(orderDataWrapper.getItemDataList(),userId);
        }catch (Exception ex) {
            logger.error("Exception occurred while creating Order", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Order Created Successfully", HttpStatus.OK);
    }

    @PostMapping("/getPrice")
    public ResponseEntity<Object> getTotalPrice(@RequestBody OrderDataWrapper orderDataWrapper) throws Exception {
        Double totalCost = null;
        try {
            totalCost = orderBS.getTotalPrice(orderDataWrapper.getItemDataList());
        }catch (Exception ex) {
            logger.error("Exception occurred while calculating Price", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(totalCost, HttpStatus.OK);
    }

    @GetMapping("/getOrderReview/orderId/{orderId}")
    public ResponseEntity<Object> getOrderReviewDetails(@PathVariable("orderId") Long orderId) throws Exception {
        OrderReviewDetails details = null;
        try {
            details = orderBS.getOrderReviewDetails(orderId);
        }catch (Exception ex) {
            logger.error("Exception occurred while getting order review detail", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(details, HttpStatus.OK);
    }

    @PostMapping("/update/orderId/{orderId}")
    public ResponseEntity<Object> updateOrder(@RequestBody OrderDataWrapper orderDataWrapper, @PathVariable("orderId") Long orderId) throws Exception {
        try {
            int i = orderBS.updateOrder(orderDataWrapper.getItemDataList(), orderId);
        }catch (Exception ex) {
            logger.error("Exception occurred while updating Order", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>("Order Updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/getOrderReview/all")
    public ResponseEntity getAllOrderReviewDetails() throws Exception {
        OrderReviewDetailsWrapper orderReviewDetailsWrapper = new OrderReviewDetailsWrapper();
        try {
            orderReviewDetailsWrapper.setOrderReviewDetailsList(orderBS.getAllOrderReviewDetails());
        }catch (Exception ex) {
            logger.error("Exception occurred while getting all Order review details", ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(orderReviewDetailsWrapper, HttpStatus.OK);
    }
}
