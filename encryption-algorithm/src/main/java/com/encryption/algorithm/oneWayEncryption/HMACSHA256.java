package com.encryption.algorithm.oneWayEncryption;

import com.encryption.algorithm.entity.Channel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName HMACSHA256
 * @Description HMAC-SHA256   这是单向加密。即只能加密不能解密，需要双方都进行加密然后进行比较
 * @date 2024/11/26 13:33
 **/
@Slf4j
public class HMACSHA256 {

    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * 双方约定的公共key
     */
//    private static final String PUBLIC_KEY = "public_key";
    private static final String PUBLIC_KEY = "VtvUx90UR7eAIzF1nLw3lWSNNYJuve42nRaPRfOEGdI=";

    public static void main(String[] args) throws JsonProcessingException {
//        String data = "Hello, World!";
//        String signature = new HMACSHA256().hmacSha256(data, PUBLIC_KEY);
//        System.out.println("data: " + data);
//        System.out.println("signature: " + signature);
        Channel channel = new Channel();
        channel.setImei("868123039927020");
        channel.setImei_md5("524f06f91c8fa92071ec85d5f23018d3");
        channel.setOaid("818bfcd4-8a99-42e2-9bf7-2fdda71783b6");
        channel.setOaid_md5("ba2b51a0fea359e4a8c68e6a629c12f3");
        channel.setAndroidid("73101e08bdd5b0d78e1917aa96af6557");
        channel.setAndroidid_md5("6772c24b3beea685a3707ac1720a70db");
        channel.setIdfa("");
        channel.setIdfa_md5("");
        channel.setChannel("2230af99f17ab91dc1fc3a35b9578b04");
        channel.setChannel2("2230af99f17ab91dc1fc3a35b9578b04");
        channel.setClicktime(1732686824000L);
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 将对象转换为 JSON 字符串
        String jsonString = objectMapper.writeValueAsString(channel);
        System.out.println(jsonString);
        String signature = new HMACSHA256().hmacSha256(jsonString.toString(), PUBLIC_KEY);
        System.out.println(signature);
    }

    /**
     * 计算 HMAC-SHA256 签名
     *
     * @param data 要签名的数据
     * @param key  密钥
     * @return HMAC-SHA256 签名结果（十六进制格式）
     */
     public String hmacSha256(String data, String key) {
         try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKey);
            byte[] hash = mac.doFinal(data.getBytes());
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("HMAC-SHA256 计算失败", e);
        }
     }

     /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}