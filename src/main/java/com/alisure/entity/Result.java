package com.alisure.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 结果包装器
 */
@ApiModel
public class Result {

    @ApiModelProperty(value = "返回结果的状态",required = true)
    private int status = Status.Status_Error;

    @ApiModelProperty(value = "全部的数据",required = true)
    private Object data = null;

    public Result() {

    }
    public Result(int status) {
        this.status = status;
    }

    public Result(boolean flag) {
        if(flag){
            this.status = Status.Status_OK;
        }else {
            this.status = Status.Status_Error;
        }
    }

    public Result(Object data) {
        if(data == null){
            this.status = Status.Status_NULL_Result;
        }else if(data instanceof List && ((List) data).size() == 0){
            this.status = Status.Status_NULL_Result;
        }else{
            this.status = Status.Status_OK;
            this.data = data;
        }
    }

    public Result(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public Result setStatus(int status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
