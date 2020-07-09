package com.training.ykb.order.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.training.ykb.order.config.MyErrorObj;

@Component
public class MyHealth implements HealthIndicator {

    @Override
    public Health health() {
        MyErrorObj errorObjLoc = new MyErrorObj();
        errorObjLoc.setDesc("Şu anda şu bozuk");
        errorObjLoc.setMiscroservice("xyz");
        return Health.up()
                     .withDetail("Okay",
                                 errorObjLoc)
                     .build();
    }

}
