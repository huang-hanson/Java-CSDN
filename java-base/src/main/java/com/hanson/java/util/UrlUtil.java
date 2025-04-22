package com.hanson.java.util;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UrlUtil
 * @Description url 工具类
 * @date 2025/4/22 9:55
 **/
@Component
public class UrlUtil {


    // 正则判断是否为 URL
    private static final Pattern URL_PATTERN = Pattern.compile("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", Pattern.CASE_INSENSITIVE);

    public String decodeIfUrl(String input) {
        if (isUrl(input)) {
            try {
                // URL 解码
                return URLDecoder.decode(input, StandardCharsets.UTF_8.name());
            } catch (Exception e) {
                // 解码失败返回原始内容
                return input;
            }
        }
        return input;
    }

    public boolean isUrl(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return URL_PATTERN.matcher(str).matches();
    }

    public boolean checkCallbackUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}