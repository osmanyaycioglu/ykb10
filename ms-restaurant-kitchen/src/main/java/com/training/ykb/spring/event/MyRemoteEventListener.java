package com.training.ykb.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyRemoteEventListener {

    @EventListener(MyRemoteEvent.class)
    public void handleEvent(final MyRemoteEvent m) {
        System.out.println("Gelen event : " + m);
    }

}
