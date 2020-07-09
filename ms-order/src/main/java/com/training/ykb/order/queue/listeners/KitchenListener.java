package com.training.ykb.order.queue.listeners;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class KitchenListener {


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "kitchen_q",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(value = "kitchen_exc",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "kitchen_q_key"))
    public void kitchenEventHandler(final KitchenResponse kr) {
        System.out.println("Meal prep duration : " + kr.getDuration());
    }

}
