package com.training.ykb.notification.rest;

import javax.validation.constraints.NotEmpty;

public class NotifyCustomer {

    @NotEmpty(message = "customerName boş olamaz.")
    private String customerName;
    @NotEmpty(message = "customerEmail boş olamaz.")
    private String customerEmail;
    @NotEmpty(message = "customerNumber boş olamaz.")
    private String customerNumber;

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(final String customerNameParam) {
        this.customerName = customerNameParam;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(final String customerEmailParam) {
        this.customerEmail = customerEmailParam;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(final String customerNumberParam) {
        this.customerNumber = customerNumberParam;
    }


}
