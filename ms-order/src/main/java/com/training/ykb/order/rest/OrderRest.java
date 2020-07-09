package com.training.ykb.order.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.test.MyWS;
import com.test.MyWSService;
import com.training.ykb.clients.account.CustomerPayment;
import com.training.ykb.clients.account.CustomerPaymentResponse;
import com.training.ykb.clients.account.IAccountCient;
import com.training.ykb.clients.notification.INotificationClient;
import com.training.ykb.clients.notification.NotifyCustomer;
import com.training.ykb.clients.restaurant.IRestaurantClient;
import com.training.ykb.clients.restaurant.OrderTotal;
import com.training.ykb.order.config.MyRestErrorException;
import com.training.ykb.order.models.Order;
import com.training.ykb.order.remote.event.MyRemoteEvent;

@RestController
@RequestMapping("/order")
public class OrderRest {

    @Autowired
    private EurekaClient        ec;

    @Autowired
    @Qualifier("loadbalanced")
    private RestTemplate        rt;

    @Autowired
    @Qualifier("direct")
    private RestTemplate        drt;

    @Autowired
    private IAccountCient       ac;

    @Autowired
    private IRestaurantClient   rc;

    @Autowired
    private INotificationClient nc;

    @Autowired
    private RabbitTemplate      rabt;

    @Autowired
    private ApplicationContext  context;

    @Autowired
    @Output(SpringCloudBusClient.OUTPUT)
    private MessageChannel      cloudBusOutboundChannel;


    @PostMapping("/fullfill")
    public String fullFill(@Validated @RequestBody final Order orderParam) {
        OrderTotal oTotalLoc = null;
        try {

            oTotalLoc = this.rc.calculate(orderParam);

            CustomerPayment customerPaymentLoc = new CustomerPayment();
            customerPaymentLoc.setAmount(oTotalLoc.getAmount());
            customerPaymentLoc.setCustomerId(orderParam.getCustomerId());
            CustomerPaymentResponse paymentLoc = this.ac.payment(customerPaymentLoc);

            NotifyCustomer ncust = new NotifyCustomer();
            ncust.setCustomerEmail(paymentLoc.getEmail());
            ncust.setCustomerName(paymentLoc.getCustomerName());
            ncust.setCustomerNumber(paymentLoc.getCellNumber());
            String notifCustomerSMSLoc = this.nc.notifCustomerSMS(ncust);

            return notifCustomerSMSLoc;
        } catch (MyRestErrorException e) {
            System.out.println(e.getErrorObj());
            return "FAIL";
        } catch (Exception e) {
            return "FAIL";
        }
    }

    @GetMapping("/wstest")
    public String name() {
        MyWSService m = new MyWSService();
        MyWS myWSPortLoc = m.getMyWSPort();
        return myWSPortLoc.hello("osman");

    }

    @PostMapping("/queue")
    public String testQueue(@RequestBody final Order orderParam) {
        this.rabt.convertAndSend("order_kitchen_exc",
                                 "order_q_key",
                                 orderParam);
        return "OK";
    }

    @GetMapping("/event")
    public String testEvent() {
        MyRemoteEvent eventLoc = new MyRemoteEvent(this,
                                                   this.context.getId());
        eventLoc.setAction(100);
        eventLoc.setDescString("test Event");
        this.cloudBusOutboundChannel.send(MessageBuilder.withPayload(eventLoc)
                                                        .build());
        this.context.publishEvent(eventLoc);
        return "OK";
    }

}
