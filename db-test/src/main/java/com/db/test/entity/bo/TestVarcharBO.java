package com.db.test.entity.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestVarchar
 * @Description 测试Varchar和char
 * @date 2025/2/28 17:56
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@TableName("t_test_varchar")
public class TestVarcharBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("field1")
    private String field1;

    @TableField("field2")
    private String field2;

}