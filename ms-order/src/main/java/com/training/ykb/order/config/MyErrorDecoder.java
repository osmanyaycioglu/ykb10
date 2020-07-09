package com.training.ykb.order.config;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.Response.Body;
import feign.codec.ErrorDecoder;

@Component
public class MyErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKeyParam,
                            final Response responseParam) {
        if (responseParam.status() != 404) {
            Body bodyLoc = responseParam.body();
            ObjectMapper oMapperLoc = new ObjectMapper();
            try {
                MyErrorObj errorObjLoc = oMapperLoc.readValue(bodyLoc.asInputStream(),
                                                              MyErrorObj.class);
                MyRestErrorException m = new MyRestErrorException(errorObjLoc.getDesc());
                m.setErrorObj(errorObjLoc);
                return m;
            } catch (Exception e) {
                return new MyRestErrorException("Unknown : " + e.getMessage());
            }
        }
        return new MyRestErrorException("404 : Not reachable.");

    }

}
