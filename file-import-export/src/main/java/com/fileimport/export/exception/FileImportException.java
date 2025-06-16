package com.fileimport.export.exception;

import com.csdn.dev.common.enums.ExceptionLogLevelEnum;
import com.csdn.dev.common.exception.BaseException;
import com.fileimport.export.enums.FileImportErrorEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FileImoprtException
 * @Description TODO
 * @date 2025/6/16 16:47
 **/
@Getter
@Slf4j
public class FileImportException extends BaseException {

    /** 异常日志级别 */
    private final ExceptionLogLevelEnum errorLevel = ExceptionLogLevelEnum.ERROR;

    public FileImportException() {
        super();
    }

    public FileImportException(FileImportErrorEnum fileImportErrorEnum) {
        super(fileImportErrorEnum);
    }

    public FileImportException(FileImportErrorEnum fileImportErrorEnum, String logMessage) {
        super(fileImportErrorEnum, logMessage);
    }

    public FileImportException(FileImportErrorEnum fileImportErrorEnum, String logMessage, Throwable cause) {
        super(fileImportErrorEnum, logMessage, cause);
    }
}