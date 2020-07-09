package com.training.ykb.order.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.training.ykb.clients.account.CustomerPayment;
import com.training.ykb.clients.account.CustomerPaymentResponse;
import com.training.ykb.clients.account.IAccountCient;
import com.training.ykb.clients.notification.INotificationClient;
import com.training.ykb.clients.notification.NotifyCustomer;
import com.training.ykb.clients.restaurant.IRestaurantClient;
import com.training.ykb.clients.restaurant.OrderTotal;
import com.training.ykb.order.config.MyRestErrorException;
import com.training.ykb.order.models.Order;

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
}
