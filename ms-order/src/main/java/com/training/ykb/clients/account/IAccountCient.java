package com.training.ykb.clients.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.ykb.order.config.MyRestErrorException;

@FeignClient("account")
@RequestMapping("/account")
public interface IAccountCient {

    @PostMapping("/pay")
    public CustomerPaymentResponse payment(@RequestBody final CustomerPayment paymentParam) throws MyRestErrorException;
}
