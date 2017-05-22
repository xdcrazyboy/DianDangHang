package com.alisure.result;

/**
 * Created by ALISURE on 2017/4/30.
 */
public class Result {

    private int status = Status.Status_Error;

    public Result() {

    }

    public Result(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public Result setStatus(int status) {
        this.status = status;
        return this;
    }
}

