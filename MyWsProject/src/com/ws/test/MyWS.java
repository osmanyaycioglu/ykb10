package com.ws.test;

import javax.jws.WebService;

@WebService
public class MyWS {

    public String hello(final String str) {
        return "Hello " + str;
    }
}
