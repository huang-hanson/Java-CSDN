package com.encryption.algorithm.oneWayEncryption;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName HMACSHA256
 * @Description HMAC-SHA256   这是单向加密。即只能加密不能解密，需要双方都进行加密然后进行比较
 * @date 2024/11/26 13:33
 **/
public class HMACSHA256 {

    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * 双方约定的公共key
     */
    private static final String PUBLIC_KEY = "public_key";

    public static void main(String[] args) {
        String data = "Hello, World!";
        String signature = new HMACSHA256().hmacSha256(data, PUBLIC_KEY);
        System.out.println("data: " + data);
        System.out.println("signature: " + signature);
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
            // 使用密钥创建 SecretKeySpec
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256);

            // 获取 Mac 实例，使用 HmacSHA256 算法
            Mac mac = Mac.getInstance(HMAC_SHA256);

            // 初始化 Mac 实例
            mac.init(secretKey);

            // 计算哈希
            byte[] hash = mac.doFinal(data.getBytes());

            // 转换字节数组为十六进制字符串
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("HMAC-SHA256 计算异常", e);
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