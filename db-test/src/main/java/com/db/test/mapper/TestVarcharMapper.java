package com.db.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.test.entity.bo.TestVarcharBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName TestVarcharMapper
 * @Description TODO
 * @date 2025/2/28 17:58
 **/
@Mapper
public interface TestVarcharMapper extends BaseMapper<TestVarcharBO> {
}
