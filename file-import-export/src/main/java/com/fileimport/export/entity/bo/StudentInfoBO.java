package com.fileimport.export.entity.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoBO
 * @Description TODO
 * @date 2025/6/4 13:55
 **/
@Data
@TableName("t_student_info")
public class StudentInfoBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学号（唯一）
     */
    @TableField(value = "student_no", fill = FieldFill.INSERT)
    private String studentNo;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别（0-女，1-男）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 所属院系ID
     */
    private Integer departmentId;

    /**
     * 所属专业ID
     */
    private Integer majorId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 入学年份
     */
    private Integer enrollmentYear;

    /**
     * 学生状态（0-在读，1-休学，2-退学，3-毕业）
     */
    private Integer status;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 家庭住址
     */
    private String address;

    /**
     * 政治面貌（0-群众，1-团员，2-党员）
     */
    private Integer politicalStatus;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志（0-未删除，1-已删除）
     */
    @TableLogic
    private Integer deleted;
}