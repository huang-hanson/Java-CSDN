package com.hanson.java;

import com.hanson.java.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UtilTest
 * @Description 工具类测试
 * @date 2025/4/22 9:56
 **/
@Slf4j
@SpringBootTest
public class UtilTest {

    @Resource
    UrlUtil urlUtil;

    String Base64Url = "https%3A%2F%2Fexport.bjadmix.com%2Ftrack%2Fconvert%3Fcb%3DeyJEZWR1Y3Rpb25SYXRpbyI6IjAiLCJhZGdyb3VwX2lkIjoiMzc5NjUiLCJjYWxsYmFja191cmwiOiJodHRwczovL2UtdTEudWJpeGlvZS5jb20vd2hhcmYvZXYyP2V2PXBvc3RiYWNrXHUwMDI2cD1DaUEyWVdJNFltUTRObUZpT0RjME9XUmhPVEkyTmpCaVlqZGhZVGczWlROak1oandMaUFCTXE4QkNnVXlNRFEzT1JJVE1UWTJOak15TnprMk5EZzROekk0TlRjMk1DRENBeWlpbHdJd0FqZ0JTQUZnQVdnQmdnRUdNakEwTnpreWlnRUNNVFNTQVFWSVQwNVBVcG9CQ0ZORVdTMUJUakF3b0FFRXFnRUJNYklCRTJOdmJTNW9hV2h2Ym05eUxtaHBZbTloY21TNkFSQUtCall5TURBd01CSUdOakl3TnpBd3dBRUMwZ0VnWlRobU5XUmhNRFV5TjJFNU56Z3laRGRtWmpaalpUTmpZamxtWVdSa05tWDZBUWc1TWpBd01qTXdNenFFQVFnTkVnVTBNREl5T1JvRk16RXlORGdpQlRNM09UWTFLZ1UzTnpjeU9EQUNPTmdFUU5VS1NnZ3pNVEkwTlRZM09GQUNZQUZ5QlRNeE1ESTRlaEFLQXpJak1oQ2huQUVhQlRJd01EQXhnQUVDaWdFRk16QXpNREdpQVFrSTJBUVFBVWpSclFLd0FkZ0V1Z0VQWTI5dExtcHZZaTVoYm1SeWIybGt5QUhMQ2RnQkRmQUJBVklETXk0d1dRQUFBQUFBQU5BX2FnNHpPUzR4TkRRdU1qRXhMakl5TmclM0QlM0RcdTAwMjZ0az1QUmdEV1FSZVVseFpVZ1VHRGxFQVdGTUZBUVVFQUZGUVVBUlJWMWtDWEFJJTNEIiwiY2FtcGFpZ25faWQiOiIzMTI0OCIsImNsaWNrX2lkIjoiZDAzZTl0YjdhMWRwb3RzMzdqMmciLCJjcmVhdGl2ZUlkIjoiX19DUklEX18iLCJpbWVpX21kNSI6IiIsImlwIjoiMzkuMTQ0LjIxMS4yMjYiLCJsaW5rX2lkIjoiMzE1NTMiLCJvIjoiNTE4MGZjMjMtMDg5NC00ODIwLWI2N2MtYzI1NjAyNzg5YTU3Iiwib2FpZCI6IiIsIm9haWRfbWQ1IjoiIiwicGxhdGZvcm1faWQiOiIxMSIsInByb2plY3RpZCI6IjQwMjI5IiwicHJvdmlkZXJfaWQiOiIzMDMwMSIsInJlcXVlc3RfaWQiOiI2YWI4YmQ4NmFiODc0OWRhOTI2NjBiYjdhYTg3ZTNjMiIsInNsb3RpZCI6Im5wZ2NNbiIsInRzIjoiMTc0NTI4MjI5MzQ5NyIsInRzX3NlIjoiMTc0NTI4MjI5MyIsInVhIjoiRGFsdmlrLzIuMS4wIChMaW51eDsgVTsgQW5kcm9pZCAxNDsgU0RZLUFOMDAgQnVpbGQvSE9OT1JTRFktQU4wMCkgIGNvbS5oaWhvbm9yLmhvcy85LjAuMTIuMzAwIn0=";

    @Test
    void test_is_url() {
        String s = urlUtil.decodeIfUrl(Base64Url);
        log.info("解码后的url:{}", s);
    }

    @Test
    void test_check_url() {
        boolean b = urlUtil.checkCallbackUrl(Base64Url);
        log.info("url是否合法:{}", b);
    }
}