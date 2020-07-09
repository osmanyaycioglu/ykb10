package com.training.ykb.spring;


public class KitchenResponse {

    private Long orderId;
    private Long duration;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final Long orderIdParam) {
        this.orderId = orderIdParam;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(final Long durationParam) {
        this.duration = durationParam;
    }


}
