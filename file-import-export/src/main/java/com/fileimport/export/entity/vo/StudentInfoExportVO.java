package com.fileimport.export.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.fileimport.export.entity.bo.StudentInfoBO;
import com.fileimport.export.enums.DepartmentEnum;
import com.fileimport.export.enums.MajorEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoExportVO
 * @Description 学生信息导出实体
 * @date 2025/6/16 18:29
 **/
@Data
@NoArgsConstructor
@ColumnWidth(value = 30)
@ContentFontStyle(fontName = "宋体", fontHeightInPoints = 12)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER, wrapped = BooleanEnum.TRUE)
public class StudentInfoExportVO {
    /**
     * 自增主键id
     */
    @ColumnWidth(value = 10)
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 学号（必填）
     */
    @ExcelProperty(value = "学号", index = 0)
    private String studentNo;

    /**
     * 学生姓名（必填）
     */
    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    @ExcelProperty(value = "性别", index = 2)
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
    private String idCard;

    /**
     * 所属院系
     */
    @ExcelProperty(value = "所属院系", index = 5)
    private String department;

    /**
     * 所属专业
     */
    @ExcelProperty(value = "所属专业", index = 6)
    private String major;

    /**
     * 班级名称
     */
    @ExcelProperty(value = "班级", index = 7)
    private String className;

    /**
     * 入学年份
     */
    @ExcelProperty(value = "入学年份", index = 8)
    private Integer enrollmentYear;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话", index = 9)
    private String phone;

    /**
     * 电子邮箱
     */
    @ExcelProperty(value = "电子邮箱", index = 10)
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 家庭住址
     */
    @ExcelProperty(value = "家庭住址", index = 11)
    @ColumnWidth(20)
    private String address;

    /**
     * 政治面貌（0-群众，1-团员，2-党员）
     */
    @ExcelProperty(value = "政治面貌", index = 12)
    private Integer politicalStatus;

    /**
     * 民族
     */
    @ExcelProperty(value = "民族", index = 13)
    private String nationality;


    public StudentInfoExportVO(StudentInfoBO studentInfoBO) {

        this.id = studentInfoBO.getId();
        this.studentNo = studentInfoBO.getStudentNo();
        this.name = studentInfoBO.getName();
        this.gender = studentInfoBO.getGender();
        this.birthday = studentInfoBO.getBirthday();
        this.idCard = studentInfoBO.getIdCard();
        this.department = DepartmentEnum.getEnumByCode(studentInfoBO.getDepartmentId()).getName();
        this.major = MajorEnum.getEnumByCode(studentInfoBO.getMajorId()).getName();
        this.className = studentInfoBO.getClassName();
        this.phone = studentInfoBO.getPhone();
        this.enrollmentYear = studentInfoBO.getEnrollmentYear();
        this.email = studentInfoBO.getEmail();
        this.address = studentInfoBO.getAddress();
        this.politicalStatus = studentInfoBO.getPoliticalStatus();
        this.nationality = studentInfoBO.getNationality();
    }
}