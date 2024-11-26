package com.encryption.algorithm.encode;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName URLEncode
 * @Description URL 编码
 * @date 2024/11/26 13:58
 *
 * URL 编码（百分号编码） 是 单向编码，并且它并不涉及加密。
 * 它的作用是将 URL 中的特殊字符（如空格、&、?、=, / 等）转换为符合 URL 标准的 ASCII 字符。
 * 这种编码主要用于在 URL 中传递数据时保证字符的安全性。
 **/
public class URLEncode {

    // URL 编码后的字符串
    private static final String ENCODEURL =  "http%3A%2F%2Focpc.baidu.com%2Focpcapi%2Fcb%2FactionCb%3Fa_type%3D%7B%7BATYPE%7D%7D%26a_value%3D%7B%7BAVALUE%7D%7D%26s%3D7330006337286914963%26o%3D1725603682443%26actType%3D3%26ext_info%3DAFD9NjMiLCJpIjoiNzEzIiwiaiI6IjE2NTI1IiwiayI6Ik5EZzBORGcyTlRVPSIsImwiOiI3MzMwMDA2MzM3Mjg2OTE0OTYzXzE3MjU2MDM2ODI0NDMiLCJtIjoiMTcyNTYwMzY4MjQ0MyIsIm4iOiIxOCIsIm8iOiIxODciLCJwIjoiMyIsInEiOiIyIiwiciI6IjQiLCJzIjoiMTgwMCIsInQiOiIxNTE3MzA5ODU3OTcxIiwieCI6IjEwMDEiLCJ5IjoiIiwieiI6IjAifQ%3D%3DIiwiMjQiOiIyNCIsIjI1IjoiMCIsIjI3IjoiNSIsIjI5IjoiMyIsIjMxIjoiMSIsIjMzIjoiMSIsIjM0IjoiMCIsIjM1IjoiMCIsIjM2IjoiMTAwMCIsIjM4IjoiODVkNWQ4MjM2NTVlOTBkNDBkOGI5NGM4YWQyODJjZWQwYzY2NmQ2MjMzNDdlNjM4NjgyYjYxZjJlYmNiZTJmZCIsIjM5IjoiNCIsIjQiOiIxIiwiNDAiOiI4Njg1OTExNTQwIiwiNDMiOiIwIiwiNDciOiIxIiwiNSI6IjI3IiwiNTAiOiIxIiwiNiI6IiIsIjciOiIwIiwiOCI6IjExMzM5MCIsIjkiOiI1IiwiYSI6IiIsImIiOiI5Q0NGMTg1OUFDNTdCMTFFRkI2RTRCNzczMzkwODhGMTpGRz0xIiwiYyI6IjAiLCJkIjoiMzI5MTI0ODYyIiwiZSI6Ijg2ODU5MTE1NDAiLCJmIjoiNjY2NzMxNDQ4NDkxIiwiZyI6IjQ4NDQ4NjU1IiwiaCI6IjczMzAwMDYzMzcyODY5MTQ5eyIxMCI6IjUzMDA0NjQwNCIsIjExIjoiNDUwMCIsIjEyIjoiMiIsIjE0IjoiNiIsIjE1IjoiODVkNWQ4MjM2NTVlOTBkNDBkOGI5NGM4YWQyODJjZWQwYzY2NmQ2MjMzNDdlNjM4NjgyYjYxZjJlYmNiZTJmZCIsIjE2IjoiNCIsIjE3IjoiMyIsIjE4IjoiMTIiLCIxOSI6IjAyOGQzNjRmOThiMDQyNzE4MWY5ODM5OTc2MDE1NDBkIiwiMjEiOiIxIiwiMjIiOiJ0cnVl";

    private static final String DECODEURL = "www.baidu.com";

    public static void main(String[] args) {
        String decode = decode(ENCODEURL);
        System.out.println(decode);

        String encode = encode(DECODEURL);
        System.out.println(encode);
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
            String decodedUrl = URLDecoder.decode(ENCODEURL, "UTF-8");
            return decodedUrl;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error decoding URL: " + str, e);
        }
    }

    /**
     * url编码
     *
     * @param str 编码前的url
     * @return 编码后的url
     */
    private static String encode(String str) {
        try {
            // 使用 URLEncoder 编码
            String encodedUrl = URLEncoder.encode(str, "UTF-8");
            return encodedUrl;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding URL: " + str, e);
        }
    }

}