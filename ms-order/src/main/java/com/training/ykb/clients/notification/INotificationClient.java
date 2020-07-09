package com.training.ykb.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.ykb.order.config.MyRestErrorException;

@FeignClient("notification")
@RequestMapping("/notify")
public interface INotificationClient {

    @PostMapping("/customer/with/sms")
    public String notifCustomerSMS(@Validated @RequestBody final NotifyCustomer nc) throws MyRestErrorException;

    @PostMapping("/customer/with/email")
    public String notifCustomerEMAIL(@Validated @RequestBody final NotifyCustomer nc) throws MyRestErrorException;

}
