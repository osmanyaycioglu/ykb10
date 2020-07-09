package com.ws.test;

import javax.xml.ws.Endpoint;

public class WSRun {

    public static void main(final String[] args) {
        Endpoint.publish("http://127.0.0.1:8090/myws",
                         new MyWS());
    }
}
