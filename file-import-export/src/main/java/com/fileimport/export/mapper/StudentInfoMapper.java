package com.fileimport.export.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fileimport.export.entity.bo.StudentInfoBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName StudentInfoMapper
 * @Description TODO
 * @date 2025/6/16 16:34
 **/
@Mapper
public interface StudentInfoMapper extends BaseMapper<StudentInfoBO> {
}
