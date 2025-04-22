package com.csdn.dev.common.entity.dto;

import com.csdn.dev.common.enums.CommonErrorEnum;
import com.csdn.dev.common.enums.ErrorEnum;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Result
 * @Description 各微服务统一的调用结果返回类
 * @date 2025/4/22 14:04
 **/
@Data
@NoArgsConstructor
public class Result<T> {

    public static String SUCCESS_STATUS = "1";
    public static String SUCCESS_MSG = "成功";

    @NotNull
    private String status;
    @NotNull
    private String message;
    @NotNull
    private T resultbody;

    public Result(String status, String message, T resultbody){
        this.status = status;
        this.message = message;
        this.resultbody = resultbody;
    }

    /**
     * 成功返回
     */
    public static Result success(){
        return new Result<>(SUCCESS_STATUS, SUCCESS_MSG, null);
    }
    public static<T> Result<T> success(T resultbody){
        return new Result<>(SUCCESS_STATUS, SUCCESS_MSG, resultbody);
    }
    public static<T> Result<T> success(T resultbody, String message){
        return new Result<>(SUCCESS_STATUS, message, resultbody);
    }

    /**
     * 默认的错误返回
     */
    public static Result fail(){
        return fail(CommonErrorEnum.SYSTEM_ERROR);
    }
    public static<T> Result<T> fail(T resultbody){
        return fail(CommonErrorEnum.SYSTEM_ERROR, resultbody);
    }

    /**
     * 根据ErrorEnum直接返回
     */
    public static Result fail(ErrorEnum errorEnum) {
        return fail(errorEnum, null);
    }
    public static<T> Result<T> fail(ErrorEnum errorEnum, T resultbody) {
        return fail(errorEnum.getCodeStr(), errorEnum.getMsg(), resultbody);
    }

    /**
     * 根据status和message返回错误
     */
    public static Result fail(String status, String message){
        return fail(status, message, null);
    }
    public static<T> Result<T> fail(String status, String message, T resultbody){
        return new Result<>(status, message, resultbody);
    }

    public boolean requestSucceeded() {
        return SUCCESS_STATUS.equals(this.status);
    }
}