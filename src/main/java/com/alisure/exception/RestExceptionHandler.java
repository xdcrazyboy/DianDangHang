package com.alisure.exception;

import com.alisure.entity.Result;
import com.alisure.entity.Status;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by ALISURE on 2017/3/24.
 */
@ControllerAdvice
public class RestExceptionHandler {

    //loginSession异常
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result loginExceptionHandler(LoginException loginException) {
        return new Result(Status.LoginException);
    }

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(RuntimeException runtimeException) {
        return new Result(Status.RuntimeException);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        return new Result(Status.NullPointerException);
    }
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Result classCastExceptionHandler(ClassCastException ex) {
        return new Result(Status.ClassCastException);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result iOExceptionHandler(IOException ex) {
        return new Result(Status.IOException);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public Result noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return new Result(Status.NoSuchMethodException);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Result indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return new Result(Status.IndexOutOfBoundsException);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public Result requestNotReadable(HttpMessageNotReadableException ex){
        return new Result(Status.HttpMessageNotReadableException);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public Result requestTypeMismatch(TypeMismatchException ex){
        return new Result(Status.TypeMismatchException);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Result requestMissingServletRequest(MissingServletRequestParameterException ex){
        return new Result(Status.MissingServletRequestParameterException);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Result request405(){
        return new Result(Status.HttpRequestMethodNotSupportedException);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public Result request406(){
        return new Result(Status.HttpMediaTypeNotAcceptableException);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    public Result server500(RuntimeException runtimeException){
        return new Result(Status.ConversionNotSupportedException);
    }
}
