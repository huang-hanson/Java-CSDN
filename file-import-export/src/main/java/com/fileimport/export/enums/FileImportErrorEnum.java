package com.fileimport.export.enums;

import com.csdn.dev.common.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName FileImportErrorEnum
 * @Description TODO
 * @date 2025/6/16 16:48
 **/
@Getter
public enum FileImportErrorEnum implements ErrorEnum {

    UPLOAD_EXCEL_TYPE_ERROR(735711, "导入文件格式错误，非excel格式"),
    UPLOAD_EXCEL_MAX_ROW_ERROR(735713, "导入文件行数太大，不能超过10000行"),
    UPLOAD_EXCEL_MAX_SIZE_ERROR(735712, "导入文件大小错误，文件不能超过10M"),
    FILE_EXPORT_FAILURE_ERROR(735021, "文件导出失败"),
    ;


    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误描述
     */
    private final String msg;

    /**
     * 设置 code 与 msg
     */
    FileImportErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCodeStr() {
        return this.code.toString();
    }
}