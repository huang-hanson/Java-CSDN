package com.fileimport.export.entity.query;

import com.csdn.dev.common.entity.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentExportPageQuery
 * @Description TODO
 * @date 2025/6/16 18:17
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentExportPageQuery extends PageQuery {
    /**
     * 学生状态（0-在读，1-休学，2-退学，3-毕业）
     */
    private Integer status;
    /**
     * 性别（0-女，1-男）
     */
    private Integer gender;
}