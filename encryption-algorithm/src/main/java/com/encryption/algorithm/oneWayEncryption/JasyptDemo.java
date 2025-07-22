package com.encryption.algorithm.oneWayEncryption;

import lombok.Data;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName JasyptDemo
 * @Description TODO
 * @date 2025/7/16 15:55
 **/
public class JasyptDemo {
    public static void main(String[] args) {
        String filePath = "C:/Users/hanson.huang/Desktop/EduMonthlyReport349832990916387645.xlsx";
        String uploadUrl = "https://lego.51job.com/internals/lego/oss/putFile/11063";
        String relationPath = "cInternalTemp202507";
        String role = "finance-file-readable";
        String cookie = "guid=0f2621914463cfb845d9405663c88006; uid=wKhG4mheBssq8HyrI+z1Ag==";

        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Cookie", cookie);

        // 创建文件资源
        File file = new File(filePath);
        FileSystemResource fileResource = new FileSystemResource(file);

        // 创建表单数据
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        body.add("role", role);
        body.add("relationPath", relationPath);

        // 创建HTTP请求实体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送请求并获取响应
        ResponseEntity<OSSResult> response = restTemplate.exchange(
                uploadUrl,
                HttpMethod.POST,
                requestEntity,
                OSSResult.class
        );

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

    @Data
    public static  class OSSResult {
        private int code;
        private DataList data;

        @Data
        public static  class DataList {
            private int confId;
            private String fileId;
            private String ossId;
            private String srcUrl;
            private String originUrl;
            private String fileCate;
            private String filename;
            private String mimetype;
            private String fullOssId;
        }
    }
}