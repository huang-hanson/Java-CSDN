package com.demo.shardingsphere.algo;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.zip.CRC32;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DeliveryShardingAlgorithm
 * @Description 分片算法
 * @date 2025/4/22 16:17
 **/
@Component
public final class DeliveryShardingAlgorithm implements StandardShardingAlgorithm<String> {

    private static final Logger log = LoggerFactory.getLogger(DeliveryShardingAlgorithm.class);
//    private static final String REAL_TABLE_NAME_PREFIX = "t_order_info";
//    private static final Long REAL_TABLE_TOTAL_COUNT = 1000L;
//    private static final String DB_NAME_PREFIX = "order_";
//    private static final Long DB_TOTAL_COUNT = 16L;
    // 举个简单的例子
    private static final String REAL_TABLE_NAME_PREFIX = "t_order_info";
    private static final Long REAL_TABLE_TOTAL_COUNT = 10L;
    private static final String DB_NAME_PREFIX = "order_";
    private static final Long DB_TOTAL_COUNT = 2L;

    public DeliveryShardingAlgorithm() {
    }

    public String dbAlgorithm(String orderId) {
        Long a = crc32(orderId);
        long b = a % DB_TOTAL_COUNT;
        return Long.toString(b);
    }

    public String tableAlgorithm(String orderId) {
        Long a = crc32(orderId);
        Long b = a / REAL_TABLE_TOTAL_COUNT;
        Long c = b % REAL_TABLE_TOTAL_COUNT + 1L;
        return String.format("%02d", c);
    }


    private static Long crc32(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        return crc32.getValue();
    }

    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> preciseShardingValue) {
        String orderId = String.valueOf(preciseShardingValue.getValue());
        String databaseIndex;
        String targetNode;
        if (((String)tableNames.stream().findFirst().orElse("t_order_info")).startsWith(preciseShardingValue.getLogicTableName())) {
            databaseIndex = this.tableAlgorithm(orderId);
            targetNode = String.format("%s%s", "t_order_info", databaseIndex);
            log.info("sharding table: {}", targetNode);
            return targetNode;
        } else {
            databaseIndex = this.dbAlgorithm(orderId);
            targetNode = String.format("%s%s", "order_", databaseIndex);
            log.info("sharding database: {}", targetNode);
            return targetNode;
        }
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        throw new UnsupportedOperationException("暂未支持区间查询");
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "DELIVERY";
    }

}