package com.encryption.algorithm;

import com.encryption.algorithm.oneWayEncryption.HMACSHA256;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class EncryptionAlgorithmApplicationTests {

    private static final String PUBLIC_KEY = "Z3A1e2bEXM9TLKiA4w1Vtb8jqzq9tA9OmyG0I61nPlQ=";

    @Test
    void contextLoads() {

        HMACSHA256 hmacsha256 = new HMACSHA256();
        String data = "123456";
        String signature = hmacsha256.hmacSha256(data, PUBLIC_KEY);
        System.out.println("signature: " + signature);
        System.out.println("11223344556677889900aabbccddeeff".length());
        System.out.println("2230af99f17ab91dc1fc3a35b9578b04".length());
    }

}
