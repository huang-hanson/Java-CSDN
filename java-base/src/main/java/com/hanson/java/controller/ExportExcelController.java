package com.hanson.java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ExportExcelController
 * @Description 测试单线程导出Excel和多线程导出Excel
 * @date 2024/12/24 10:01
 **/
@RestController
public class ExportExcelController {

    @GetMapping("/singleThread")
    public String singleThread() {
        return "singleThread";
    }
}