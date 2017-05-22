package com.alisure.result;

/**
 * Created by ALISURE on 2017/3/24.
 */
public class ResultObject<T> extends Result{
    private T object;

    public ResultObject() {

    }

    public ResultObject(int status, T object) {
        super.setStatus(status);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Result setResultObject(T object) {
        this.object = object;
        if(object == null){
            return new Result().setStatus(Status.Status_NULL_Result);
        }
        /*返回结果*/
        return setStatus(Status.Status_OK);
    }

}
