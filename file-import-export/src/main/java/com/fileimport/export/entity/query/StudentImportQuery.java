package com.fileimport.export.entity.query;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

import java.time.LocalDate;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentImportQuery
 * @Description 学硕导入对象实体
 * @date 2025/6/4 14:09
 **/
@Data
public class StudentImportQuery {

    /**
     * 数据行号，由导入读取时写入
     */
    @ExcelIgnore
    private Integer rowNum;

    /**
     * 学号（必填）
     */
    @ExcelProperty(value = "学号", index = 0)
    @NotBlank(message = "学号不能为空")
    @Length(max = 20, message = "学号长度不能超过20个字符")
    private String studentNo;

    /**
     * 学生姓名（必填）
     */
    @ExcelProperty(value = "姓名", index = 1)
    @NotBlank(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50个字符")
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    @ExcelProperty(value = "性别", index = 2)
    @NotNull(message = "性别不能为空")
    @Min(value = 0, message = "性别值不合法")
    @Max(value = 1, message = "性别值不合法")
    private Integer gender;

    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期", index = 3)
    @ColumnWidth(15)
    private LocalDate birthday;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号", index = 4)
    @Length(min = 15, max = 18, message = "身份证号长度应在15-18位之间")
    private String idCard;

    /**
     * 所属院系
     */
    @ExcelProperty(value = "所属院系", index = 5)
    @Length(max = 100, message = "院系名称长度不能超过100个字符")
    private String department;

    /**
     * 所属专业
     */
    @ExcelProperty(value = "所属专业", index = 6)
    @Length(max = 100, message = "专业名称长度不能超过100个字符")
    private String major;

    /**
     * 班级名称
     */
    @ExcelProperty(value = "班级", index = 7)
    @Length(max = 50, message = "班级名称长度不能超过50个字符")
    private String className;

    /**
     * 入学年份
     */
    @ExcelProperty(value = "入学年份", index = 8)
    @Min(value = 2000, message = "入学年份不能早于2000年")
    @Max(value = 2100, message = "入学年份不能晚于2100年")
    private Integer enrollmentYear;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话", index = 9)
    @Pattern(regexp = "^1[3-9]\\d{9}$|^\\d{3,4}-\\d{7,8}$", message = "联系电话格式不正确")
    private String phone;

    /**
     * 电子邮箱
     */
    @ExcelProperty(value = "电子邮箱", index = 10)
    @Email(message = "邮箱格式不正确")
    @Length(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;

    /**
     * 家庭住址
     */
    @ExcelProperty(value = "家庭住址", index = 11)
    @ColumnWidth(20)
    @Length(max = 200, message = "家庭住址长度不能超过200个字符")
    private String address;

    /**
     * 政治面貌（0-群众，1-团员，2-党员）
     */
    @ExcelProperty(value = "政治面貌", index = 12)
    @Min(value = 0, message = "政治面貌值不合法")
    @Max(value = 2, message = "政治面貌值不合法")
    private Integer politicalStatus;

    /**
     * 民族
     */
    @ExcelProperty(value = "民族", index = 13)
    @Length(max = 30, message = "民族长度不能超过30个字符")
    private String nationality;

    /**
     * 备注信息
     */
    @ExcelProperty(value = "备注", index = 14)
    @ColumnWidth(25)
    @Length(max = 500, message = "备注长度不能超过500个字符")
    private String remark;
}