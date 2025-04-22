package com.demo.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.shardingsphere.entity.bo.UserInfoSharding;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName UserInfoShardingMapper
 * @Description 用户信息表-分表 mapper
 * @date 2025/4/22 13:44
 **/
@Mapper
public interface UserInfoShardingMapper extends BaseMapper<UserInfoSharding> {
}
