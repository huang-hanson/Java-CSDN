package com.encryption.algorithm;

import com.encryption.algorithm.entity.OcpxBackFromTableDTO;
import com.encryption.algorithm.oneWayEncryption.HMACSHA256;
import com.encryption.algorithm.utils.JacksonUtils;
import com.encryption.algorithm.utils.ObjMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Slf4j
@SpringBootTest
class EncryptionAlgorithmApplicationTests {

    private static final String PUBLIC_KEY = "Z3A1e2bEXM9TLKiA4w1Vtb8jqzq9tA9OmyG0I61nPlQ=";

    @Resource
    private ObjMapperUtils objMapperUtils;

    @Test
    void contextLoads() {

        HMACSHA256 hmacsha256 = new HMACSHA256();
        String data = "123456";
        String signature = hmacsha256.hmacSha256(data, PUBLIC_KEY);
        System.out.println("signature: " + signature);
        System.out.println("11223344556677889900aabbccddeeff".length());
        System.out.println("2230af99f17ab91dc1fc3a35b9578b04".length());
    }


    @Test
    void test() {
        String message = "%7B%22messageBody%22%3A%22%7B%5C%22traceData%5C%22%3A%5C%22%7B%5C%5C%5C%22partner%5C%5C%5C%22%3A%5C%5C%5C%22sem_dlxcx_1%5C%5C%5C%22%2C%5C%5C%5C%22gdt_vid%5C%5C%5C%22%3A%5C%5C%5C%22wx0xlg7ah4a45t4i%5C%5C%5C%22%2C%5C%5C%5C%22weixinadinfo%5C%5C%5C%22%3A%5C%5C%5C%2234417816522.wx0xlg7ah4a45t4i.0.0%5C%5C%5C%22%7D%5C%22%2C%5C%22uuid%5C%22%3A%5C%2217428085197904488792%5C%22%2C%5C%22openId%5C%22%3A%5C%22oPzgV0fsCB_I8JTyHKP9BUGqbaFY%5C%22%2C%5C%22unionId%5C%22%3A%5C%22oU4p5uNOmQZEGsi1E1RzGWgeJ4ck%5C%22%2C%5C%22partner%5C%22%3A%5C%22sem_dlxcx_1%5C%22%2C%5C%22type%5C%22%3A%5C%22c_register%5C%22%7D%22%2C%22messageCreateTime%22%3A1743041795%2C%22messageKey%22%3A%2247d756c8da6c160691739536eee9aa36%22%7D";
        String decode = decode(message);

        JSONObject result = JSON.parseObject(decode);
        String msgBody = result.getString("messageBody");
        String msgKey = result.getString("messageKey");

        OcpxBackFromTableDTO ocpxBackFromTableDTO = objMapperUtils.parseObject(msgBody, OcpxBackFromTableDTO.class);
        log.warn(JacksonUtils.jsonEncode(ocpxBackFromTableDTO));
    }

    /**
     * url解码
     *
     * @param str 编码后的url
     * @return 解密后的url
     */
    private static String decode(String str) {
        try {
            // 使用 URLDecoder 解码
            String decodedUrl = URLDecoder.decode(str, "UTF-8");
            return decodedUrl;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error decoding URL: " + str, e);
        }
    }
}
