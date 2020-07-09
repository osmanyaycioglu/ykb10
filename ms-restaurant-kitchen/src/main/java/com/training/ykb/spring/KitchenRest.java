package com.training.ykb.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RequestScope
// @SessionScope
// @ApplicationScope

@RestController
@RequestMapping("/kitchen")
@RefreshScope
public class KitchenRest {

    @Value("${my.prop.name}")
    private String myProp;

    @GetMapping("/show")
    public String showProp() {
        return this.myProp;
    }

}
