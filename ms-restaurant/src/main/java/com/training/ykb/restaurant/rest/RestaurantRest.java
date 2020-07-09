package com.training.ykb.restaurant.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ykb.restaurant.Restaurant;

@RestController
@RequestMapping("/restaurant")
public class RestaurantRest {

    @Autowired
    private Restaurant restaurant;

    @PostMapping("/calculate/order")
    public OrderTotal calculate(@RequestBody final Order mor) {
        long calculatePriceLoc = this.restaurant.calculatePrice(mor.getOrderItems());
        return new OrderTotal(mor.getOrderId(),
                              calculatePriceLoc);
    }
}
