package com.training.ykb.order.remote.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MyRemoteEvent extends RemoteApplicationEvent {

    private static final long serialVersionUID = 8595977400330334549L;

    private String            descString;
    private int               action;


    public MyRemoteEvent() {
    }

    public MyRemoteEvent(final Object source,
                         final String originService) {
        super(source,
              originService);
    }


    public String getDescString() {
        return this.descString;
    }


    public void setDescString(final String descStringParam) {
        this.descString = descStringParam;
    }


    public int getAction() {
        return this.action;
    }


    public void setAction(final int actionParam) {
        this.action = actionParam;
    }

    @Override
    public String toString() {
        return "MyRemoteEvent [descString=" + this.descString + ", action=" + this.action + "]";
    }


}
