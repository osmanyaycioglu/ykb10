package com.training.ykb.spring;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @Value("${server.port}")
    private int port;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order_q", autoDelete = "false", durable = "true"),
                                             exchange = @Exchange(value = "order_kitchen_exc",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "order_q_key"))
    @SendTo("kitchen_exc/kitchen_q_key")
    public KitchenResponse xyz(final Order order) {
        System.out.println(this.port + " Gelen msg 1 : " + order);
        KitchenResponse kResponseLoc = new KitchenResponse();
        kResponseLoc.setOrderId(order.getOrderId());
        kResponseLoc.setDuration(TimeUnit.MILLISECONDS.convert(30,
                                                               TimeUnit.MINUTES));
        return kResponseLoc;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order_q_2",
                                                            autoDelete = "false",
                                                            durable = "true"),
                                             exchange = @Exchange(value = "order_kitchen_exc",
                                                                  durable = "true",
                                                                  type = ExchangeTypes.DIRECT),
                                             key = "order_q_key2"))
    public void abc(final Order order) {
        System.out.println(this.port + " Gelen msg 2 : " + order);
    }

}
