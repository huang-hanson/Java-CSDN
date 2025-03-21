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
    private static final String ENCODEURL2 =  "http://ad.toutiao.com/track/activate/?callback=B.u0ad42en3G4O66ReHqEbMwUDkaoxZGicbvBXMXrE9r1XthILfUtN9uqD0k2F8gv7jlieQBg0AhUmpGefUYjyWwP1rRkAAHfp2znen5gzrss1PkGtiEIQ7Fjtwel8iYg9IHJgMED4TsbaiOPoVxYtSGb&os=0&muid=";
    private static final String ENCODEURL3 =  "http:\\/\\/tracking.e.qq.com\\/conv?cb=7sbdeGJwSuvFWVZGKYABUQ7QxoHbn2_ef18lO_vduBQ=&conv_id=12883641";
    private static final String ENCODEURL4 = "%7B%22weixinadinfo%22%3A%2232680504048.wx0ec6czh5ahdz3y00.0.1%22%2C%22uxinfo%22%3A%2232680504048%257Cwx0ec6czh5ahdz3y00%257C32680504048%257C0%257C1742525530%257C0%257C2%257C4031112715115510%257C%257CAgJ3XCTFasq7AfqFJTLVDdjpESjZFn3Tsx%252BY%252FEbkRqQBaz4I9uB%252BaRgTXEn0OfU1eA%253D%253D%257C32708090106%257C21%257C0%257CoDdoCty3p2qxvnTvAo6v-AuNTCrc%257C3%257C2156295973%22%2C%22partner%22%3A%22sem_wxxcx_1%22%2C%22wx_aid%22%3A%2232680504048%22%2C%22weixinadkey%22%3A%2210ee8cbd79d6269b0dd428d1a45de7a6567b819ac4ae557c9bfdbb4e9de9bd6a980bb0130065adbc28f60f655e9f3098%22%2C%22wx_traceid%22%3A%22wx0ec6czh5ahdz3y00%22%2C%22gdt_vid%22%3A%22wx0ec6czh5ahdz3y00%22%7D";
    private static final String DECODEURL = "www.baidu.com";

    private static final String DECODEURL2 = "security:0EFF5266B19CAF7E45E71566:A4E62E91504000E6B8CA08B381FDB25CDC5FAC06F42B08F2B38D86D85057021BEBB1E84568F13BA85F7AB74B808D6D8C34329FA7";

    public static void main(String[] args) {
        String decode = decode(ENCODEURL);
        String decode2 = decode(ENCODEURL2);
        String decode3 = decode(ENCODEURL3);
        String decode4 = decode(ENCODEURL4);
//        System.out.println(decode);
//        System.out.println(decode3);
        System.out.println(decode4);

        String encode = encode(DECODEURL2);
        String encode3 = encode(ENCODEURL3);
//        System.out.println(encode);
        System.out.println(encode3);

        System.out.println("997f651d3a1989f953d2c7c9945061ba".length());
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