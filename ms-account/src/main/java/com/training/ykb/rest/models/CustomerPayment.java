package com.training.ykb.rest.models;

import javax.validation.constraints.NotNull;

public class CustomerPayment {

    @NotNull(message = "customerId boş olamaz.")
    private Long customerId;
    @NotNull(message = "amount boş olamaz.")
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
