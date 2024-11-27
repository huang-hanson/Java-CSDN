package com.encryption.algorithm.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Channel
 * @Description TODO
 * @date 2024/11/27 15:51
 **/
@Data
public class Channel {

    private String channel;

    private String channel2;

    private String imei;

    private String imei_md5;

    private String oaid;

    private String oaid_md5;

    private String androidid;

    private String androidid_md5;

    private String idfa;

    private String idfa_md5;

    private Long clicktime;
//    private String clicktime;
}