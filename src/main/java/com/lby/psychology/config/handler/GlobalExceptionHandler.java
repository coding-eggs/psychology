package com.lby.psychology.config.handler;

import com.lby.psychology.config.PsycException;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_STRING = "{}：code:{} # msg:{}";

    @ExceptionHandler(value = PsycException.class)
    public ResponseData<String> handlerPsycException(PsycException psycException){
        log.error(ERROR_STRING,psycException.getCode(),psycException.getMsg(),psycException);
        return new ResponseData<>(psycException.getCode(),null);
    }


    /**
     * 其他异常处理
     * @param exception 其他异常
     * @return 同意返回错误结果
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData<String> handlerOtherError(Exception exception){
        log.error(ERROR_STRING,EnumResponseType.ERROR.getCode(),exception.getMessage(),exception);
        return new ResponseData<>(EnumResponseType.ERROR.getCode(),null);
    }


    /***
     * 参数异常 -- ConstraintViolationException()
     * 用于处理类似http://localhost:8080/user/getUser?age=30&name=yoyo请求中age和name的校验引发的异常
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<String> urlParametersExceptionHandle(ConstraintViolationException e) {
        log.error("【请求参数异常】:", e);
        //收集所有错误信息
        List<String> errorMsg = e.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        ResponseData<String> responseData = new ResponseData<>();
        responseData.setCode(EnumResponseType.BEAN_VALIDATION_EXCEPTION.getCode());
        responseData.setMsg(errorMsg.toString());
        return responseData;
    }

    /***
     * 参数异常 --- MethodArgumentNotValidException和BindException
     * MethodArgumentNotValidException --- 用于处理请求参数为实体类时校验引发的异常 --- Content-Type为application/json
     * BindException --- 用于处理请求参数为实体类时校验引发的异常  --- Content-Type为application/x-www-form-urlencoded
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<String> bodyExceptionHandle(Exception e) {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setCode(EnumResponseType.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode());
        log.error("【请求参数异常】:", e);
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            bindingResult = ex.getBindingResult();
        } else {
            BindException ex = (BindException) e;
            ex.printStackTrace();
        }
        if (bindingResult != null) {
            //收集所有错误信息
            List<String> errorMsg = bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            responseData.setMsg(errorMsg.toString());
        }
        return responseData;
    }

}
