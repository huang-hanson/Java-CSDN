package com.excelImport.controller;

import com.excelImport.gen.GenEduMonthlyReportExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.YearMonth;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GenReportExcelController
 * @Description 生成excel
 * @date 2025/7/22 15:55
 **/
@RestController
public class GenReportExcelController {

    @Resource
    private GenEduMonthlyReportExcel genEduMonthlyReportExcel;

    @GetMapping("/genExcel")
    public String genExcel() throws JsonProcessingException {
        genEduMonthlyReportExcel.handleMonthlyReport(YearMonth.now());
        return "success";
    }
}