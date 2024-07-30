package com.hanson.strategy.exception;

import com.hanson.strategy.enmus.CommonErrorEnum;
import com.hanson.strategy.enmus.ErrorEnum;
import com.hanson.strategy.enmus.ExceptionLogLevelEnum;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BaseException
 * @Description TODO
 * @date 2024/7/30 19:45
 **/
@Getter
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final ErrorEnum errorEnum;
    private String irisSpecialMessage = "";

    /**
     * 默认错误日志级别为ERROR
     */
    protected ExceptionLogLevelEnum errorLevel = ExceptionLogLevelEnum.ERROR;

    private Object resultBody = null;

    /**
     * 异常时需要返回的resultBody
     * @param result 返回的body
     * @author linton.cao
     * @date 2021/9/8 16:29
     */
    public void setResultBody(Object result){
        this.resultBody = result;
    }

    /**
     * 默认错误
     * @author linton.cao
     * @date 2021/9/2 13:33
     */
    public BaseException() {
        super(CommonErrorEnum.SYSTEM_ERROR.getCodeStr());
        this.errorEnum = CommonErrorEnum.SYSTEM_ERROR;
    }

    public BaseException (ErrorEnum errorEnum) {
        super(errorEnum.getCodeStr());
        this.errorEnum = errorEnum;
    }

    public BaseException (ErrorEnum errorEnum, String logMessage) {
        super(errorEnum.getCodeStr() + " - " + logMessage);
        this.errorEnum = errorEnum;
        this.irisSpecialMessage = logMessage;
    }

    public BaseException(ErrorEnum errorEnum, String logMessage, Throwable cause) {
        super(errorEnum.getCodeStr() + " - " + logMessage, cause);
        this.errorEnum = errorEnum;
    }

    public BaseException(ErrorEnum errorEnum, String logMessage, Object... messageParams) {
        super(errorEnum.getCodeStr() + " - " + formatLogMessage(logMessage, messageParams));
        this.errorEnum = errorEnum;
    }

    private static String formatLogMessage(String logMessage, Object... messageParams){
        String logStr = logMessage;
        if (messageParams.length>0) {
            logStr = MessageFormat.format(logMessage, messageParams);
        }
        return logStr;
    }
}