package com.training.ykb.clients.account;


public class CustomerPayment {

    private Long customerId;
    private Long amount;

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(final Long customerIdParam) {
        this.customerId = customerIdParam;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(final Long amountParam) {
        this.amount = amountParam;
    }


}
