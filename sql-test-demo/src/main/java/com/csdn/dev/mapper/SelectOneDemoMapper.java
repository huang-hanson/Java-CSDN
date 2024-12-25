package com.csdn.dev.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csdn.dev.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName SelectOneDemoService
 * @date 2024/12/25 19:41
 **/
@Mapper
public interface SelectOneDemoMapper extends BaseMapper<User> {
}
