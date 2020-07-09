package com.training.ykb.restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class Restaurant {

    private final Map<String, Long> menu = new HashMap<>();

    @PostConstruct
    public void init() {
        this.menu.put("kebap",
                      30L);
        this.menu.put("lahmacun",
                      10L);
        this.menu.put("tavuk",
                      20L);
        this.menu.put("pizza",
                      25L);
        this.menu.put("salata",
                      15L);
    }

    public Long getPrice(final String meal) {
        return this.menu.get(meal);
    }

    public long calculatePrice(final List<String> order) {
        long total = 0;
        for (String stringLoc : order) {
            Long price = this.getPrice(stringLoc);
            if (price == null) {
                throw new IllegalStateException(stringLoc + " menumüzde bulunmamaktadır.");
            }
            total += price.longValue();
        }
        return total;
    }
}
