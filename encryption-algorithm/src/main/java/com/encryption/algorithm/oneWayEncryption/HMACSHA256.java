package com.encryption.algorithm.oneWayEncryption;

import com.csdn.dev.common.util.JacksonUtils;
import com.encryption.algorithm.entity.Channel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

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
    private static final String PUBLIC_KEY = "Z3A1e2bEXM9TLKiA4w1Vtb8jqzq9tA9OmyG0I61nPlQ=";

    public static void main(String[] args) throws JsonProcessingException {
//        String data = "Hello, World!";
//        String signature = new HMACSHA256().hmacSha256(data, PUBLIC_KEY);
//        System.out.println("data: " + data);
//        System.out.println("signature: " + signature);
        Channel channel = new Channel();
//        channel.setImei("868123039927020");
//        channel.setImei_md5("524f06f91c8fa92071ec85d5f23018d3");
//        channel.setOaid("818bfcd4-8a99-42e2-9bf7-2fdda71783b6");
//        channel.setOaid_md5("ba2b51a0fea359e4a8c68e6a629c12f3");
//        channel.setAndroidid("73101e08bdd5b0d78e1917aa96af6557");
//        channel.setAndroidid_md5("6772c24b3beea685a3707ac1720a70db");
//        channel.setIdfa("");
//        channel.setIdfa_md5("");
//        channel.setChannel("2230af99f17ab91dc1fc3a35b9578b04");
//        channel.setChannel2("2230af99f17ab91dc1fc3a35b9578b04");
//        channel.setClicktime(1733713530000L);
//        channel.setCallback("https://ks.excm.net/qcwy_m_cb.php?click_id=20ddbc9c7347d32f8434cd2a4b80fbcb");
        channel.setImei("");
        channel.setImei_md5("");
        channel.setOaid("17536e10563573f7");
        channel.setOaid_md5("3b34fc8e0091384e8a5b772b797c8414");
        channel.setAndroidid("");
        channel.setAndroidid_md5("");
        channel.setIdfa("");
        channel.setIdfa_md5("");
        channel.setChannel("2230af99f17ab91dc1fc3a35b9578b04");
        channel.setChannel2("2230af99f17ab91dc1fc3a35b9578b04");
        channel.setClicktime(1740644711094L);
//        channel.setCallback("https://ks.excm.net/qcwy_m_cb.php?click_id=20ddbc9c7347d32f8434cd2a4b80fbcb");
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 将对象转换为 JSON 字符串
        String jsonString = objectMapper.writeValueAsString(channel);
//        System.out.println(jsonString);
        String signature = new HMACSHA256().hmacSha256(jsonString.toString(), PUBLIC_KEY);
        String signature1 = new HMACSHA256().hmacSha256("123456", PUBLIC_KEY);
        log.error("data对象：{} 的sign是：{}", jsonString, signature);
        log.error("123456的sign是：{}", signature1);

        // 模拟向 POST 请求
//        channel.setSign("1da17411f250f536ef7b5cce823ae90caffd04406114213e8831b160579fd9aa");
//
//        // restTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        HttpEntity<String> requestEntity = new HttpEntity<>(JacksonUtils.jsonEncode(channel),headers);
//
//
//        ResponseEntity<String> exchange = restTemplate.exchange("https://tper.51job.com/mkt-media/open/noauth/ocpx/innovative/get-click-data", HttpMethod.POST, requestEntity, String.class);
//        log.error("exchange:{}", exchange.getBody());
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
//            String s1 = bytesToHex(hash);
//            String s2 = byteArrayToHexString(hash);
//            System.out.println(s1);
//            System.out.println(s2);
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

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
}