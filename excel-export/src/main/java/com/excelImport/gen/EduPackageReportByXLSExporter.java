package com.excelImport.gen;

import com.excelImport.dto.EduPackageDailyReportDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Data;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EduPackageReportByXLSExporter
 * @Description TODO
 * @date 2025/6/30 11:18
 **/

public class EduPackageReportByXLSExporter {

    public static void main(String[] args) throws Exception {
        // 准备数据
        EduPackageDailyReportDTO dto = mockData();

        // 模板配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassLoaderForTemplateLoading(EduPackageReportByXLSExporter.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 加载模板
        Template template = cfg.getTemplate("package_report.xml.ftl");

        // 输出路径
        try (Writer out = new OutputStreamWriter(new FileOutputStream("output/package_report.xls"), StandardCharsets.UTF_8)) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("packageInfo", dto.getPackageInfo());
            dataMap.put("numCount", dto.getNumCount());
            dataMap.put("pacoPriceCount", dto.getPacoPriceCount());
            dataMap.put("incomeOffsetCount", dto.getIncomeOffsetCount());

            // 渲染模板
            template.process(dataMap, out);
        }

        System.out.println("Excel 报表生成成功！");
    }

    private static EduPackageDailyReportDTO mockData() {
        EduPackageDailyReportDTO dto = new EduPackageDailyReportDTO();
        List<EduPackageDailyReportDTO.PackageInfo> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            EduPackageDailyReportDTO.PackageInfo info = new EduPackageDailyReportDTO.PackageInfo();
            info.setDate(LocalDate.now().minusDays(i));
            info.setProductId("PKG-" + i);
            info.setLessonId(1000 + i);
            info.setLessonName("课程名称" + i);
            info.setNum(i * 10);
            info.setPacoPrice(BigDecimal.valueOf(100 + i));
            info.setIncomeOffset(BigDecimal.valueOf(5 * i));
            info.setProductType("0100");
            info.setCollaboratorId(2000 + i);
            info.setNameAbbr("合作方" + i);
            info.setName("合作方全称" + i);
            info.setPercentage("固定");
            list.add(info);
        }

        dto.setPackageInfo(list);
        dto.setNumCount(list.stream().mapToInt(EduPackageDailyReportDTO.PackageInfo::getNum).sum());
        dto.setPacoPriceCount(list.stream().map(EduPackageDailyReportDTO.PackageInfo::getPacoPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        dto.setIncomeOffsetCount(list.stream().map(EduPackageDailyReportDTO.PackageInfo::getIncomeOffset).reduce(BigDecimal.ZERO, BigDecimal::add));
        return dto;
    }

}