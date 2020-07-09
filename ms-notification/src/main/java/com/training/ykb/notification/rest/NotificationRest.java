package com.training.ykb.notification.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.ykb.notification.NotificationService;

@RestController
@RequestMapping("/notify")
public class NotificationRest {

    @Autowired
    private NotificationService ns;

    @PostMapping("/customer/with/sms")
    public String notifCustomerSMS(@Validated @RequestBody final NotifyCustomer nc) {
        if (this.ns.notifyWithSMS(nc.getCustomerName(),
                                  nc.getCustomerNumber())) {
            return "OK";
        }
        return "FAIL";
    }

    @PostMapping("/customer/with/email")
    public String notifCustomerEMAIL(@Validated @RequestBody final NotifyCustomer nc) {
        if (this.ns.notifyWithEMAIL(nc.getCustomerName(),
                                    nc.getCustomerEmail())) {
            return "OK";
        }
        return "FAIL";
    }

}
