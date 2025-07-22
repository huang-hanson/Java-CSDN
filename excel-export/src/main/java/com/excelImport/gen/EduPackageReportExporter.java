//package com.excelImport.gen;
//
//
//import com.excelImport.dto.EduPackageDailyReportDTO;
//import lombok.Data;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName EduPackageReportExporter
// * @Description TODO
// * @date 2025/6/30 11:01
// **/
//public class EduPackageReportExporter {
//
//    public static void main(String[] args) throws Exception {
//        EduPackageDailyReportDTO dto = mockData();
//
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("打包课成本日报");
//
//        int rowIdx = 0;
//
//        // 设置列宽
//        for (int i = 0; i < 12; i++) {
//            sheet.setColumnWidth(i, 18 * 256);
//        }
//
//        // 样式定义
//        CellStyle boldCenter = createStyle(workbook, true, true);
//        CellStyle center = createStyle(workbook, false, true);
//
//        // 第一行标题
//        Row row0 = sheet.createRow(rowIdx++);
//        Cell titleCell = row0.createCell(0);
//        titleCell.setCellValue("“无忧课堂”打包课成本明细日报表");
//        titleCell.setCellStyle(boldCenter);
//        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 11));
//
//        // 第二行：小标题
//        Row row1 = sheet.createRow(rowIdx++);
//        row1.createCell(1).setCellValue("课程信息");
//        row1.createCell(4).setCellValue("付出成本");
//        row1.createCell(7).setCellValue("课程说明");
//
//        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 1, 3));
//        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 4, 5));
//        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 7, 10));
//
//        for (int i : Arrays.asList(1, 4, 7)) {
//            row1.getCell(i).setCellStyle(boldCenter);
//        }
//
//        // 第三行：列头
//        Row row2 = sheet.createRow(rowIdx++);
//        String[] headers = {"时间", "所属课包", "课程ID", "课程名", "数量", "合作方打包结算价", "收入抵减", "课程性质", "合作方ID", "合作方简称", "合作方全称", "合作方分成比例"};
//        for (int i = 0; i < headers.length; i++) {
//            Cell cell = row2.createCell(i);
//            cell.setCellValue(headers[i]);
//            cell.setCellStyle(boldCenter);
//        }
//
//        // 数据行
//        for (EduPackageDailyReportDTO.PackageInfo info : dto.getPackageInfo()) {
//            Row dataRow = sheet.createRow(rowIdx++);
//            int col = 0;
//            dataRow.createCell(col++).setCellValue(info.getDateShow());
//            dataRow.createCell(col++).setCellValue(info.getProductId());
//            dataRow.createCell(col++).setCellValue(info.getLessonId());
//            dataRow.createCell(col++).setCellValue(info.getLessonName());
//            dataRow.createCell(col++).setCellValue(info.getNum());
//            dataRow.createCell(col++).setCellValue(info.getPacoPrice().toString());
//            dataRow.createCell(col++).setCellValue(info.getIncomeOffset().toString());
//            dataRow.createCell(col++).setCellValue(info.getProductType());
//            dataRow.createCell(col++).setCellValue(info.getCollaboratorId());
//            dataRow.createCell(col++).setCellValue(info.getNameAbbr());
//            dataRow.createCell(col++).setCellValue(info.getName());
//            dataRow.createCell(col++).setCellValue(info.getPercentage());
//
//            for (int i = 0; i < 12; i++) {
//                dataRow.getCell(i).setCellStyle(center);
//            }
//        }
//
//        // TOTAL 行
//        Row totalRow = sheet.createRow(rowIdx++);
//        Cell totalLabel = totalRow.createCell(0);
//        totalLabel.setCellValue("TOTAL");
//        totalLabel.setCellStyle(boldCenter);
//        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(rowIdx - 1, rowIdx - 1, 0, 3));
//
//        totalRow.createCell(4).setCellValue(dto.getNumCount());
//        totalRow.createCell(5).setCellValue(dto.getPacoPriceCount().toString());
//        totalRow.createCell(6).setCellValue(dto.getIncomeOffsetCount().toString());
//
//        for (int i : Arrays.asList(4, 5, 6)) {
//            totalRow.getCell(i).setCellStyle(center);
//        }
//
//        // 写入文件
//        try (OutputStream os = new FileOutputStream("output/package_report.xlsx")) {
//            workbook.write(os);
//            workbook.close();
//        }
//
//        System.out.println("✅ Excel xlsx 导出成功！");
//    }
//
//    private static CellStyle createStyle(Workbook wb, boolean bold, boolean center) {
//        CellStyle style = wb.createCellStyle();
//        if (center) {
//            style.setAlignment(HorizontalAlignment.CENTER);
//            style.setVerticalAlignment(VerticalAlignment.CENTER);
//        }
//        Font font = wb.createFont();
//        font.setFontName("等线");
//        font.setFontHeightInPoints((short) 11);
//        font.setBold(bold);
//        style.setFont(font);
//        return style;
//    }
//
//    private static EduPackageDailyReportDTO mockData() {
//        EduPackageDailyReportDTO dto = new EduPackageDailyReportDTO();
//        List<EduPackageDailyReportDTO.PackageInfo> list = new ArrayList<>();
//
//        for (int i = 1; i <= 5; i++) {
//            EduPackageDailyReportDTO.PackageInfo info = new EduPackageDailyReportDTO.PackageInfo();
//            info.setDate(LocalDate.now().minusDays(i));
//            info.setProductId("PKG-" + i);
//            info.setLessonId(1000 + i);
//            info.setLessonName("课程名称" + i);
//            info.setNum(i * 10);
//            info.setPacoPrice(BigDecimal.valueOf(100 + i));
//            info.setIncomeOffset(BigDecimal.valueOf(5 * i));
//            info.setProductType("0100");
//            info.setCollaboratorId(2000 + i);
//            info.setNameAbbr("合作方" + i);
//            info.setName("合作方全称" + i);
//            info.setPercentage("固定");
//            list.add(info);
//        }
//
//        dto.setPackageInfo(list);
//        dto.setNumCount(list.stream().mapToInt(EduPackageDailyReportDTO.PackageInfo::getNum).sum());
//        dto.setPacoPriceCount(list.stream().map(EduPackageDailyReportDTO.PackageInfo::getPacoPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
//        dto.setIncomeOffsetCount(list.stream().map(EduPackageDailyReportDTO.PackageInfo::getIncomeOffset).reduce(BigDecimal.ZERO, BigDecimal::add));
//        return dto;
//    }
//}