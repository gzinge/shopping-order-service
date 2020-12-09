package com.shopping.order.feignclient;


import com.shopping.order.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:58009")
public interface UserFeignClient {

    @GetMapping("/user/userId/{userId}")
    public User getUserByIdFromUserService(@PathVariable("userId") Long userId);
}

