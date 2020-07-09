package com.training.ykb.order.config;

public class MyRestErrorException extends Exception {

    private MyErrorObj errorObj;

    public MyRestErrorException() {
    }

    public MyRestErrorException(final String str) {
        super(str);
    }

    public MyErrorObj getErrorObj() {
        return this.errorObj;
    }

    public void setErrorObj(final MyErrorObj errorObjParam) {
        this.errorObj = errorObjParam;
    }


}
