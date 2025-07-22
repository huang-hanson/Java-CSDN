package com.excelImport.gen;

import com.excelImport.dto.EduMonthlyReportDTO;

import com.excelImport.dto.PayAmountDTO;
import com.excelImport.dto.ProductAmountDTO;
import com.excelImport.dto.TrainMonthlyReportDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.YearMonth;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GenEduMonthlyReportExcel
 * @Description excel生成
 * @date 2025/7/22 15:34
 **/
@Slf4j
@Service
public class GenEduMonthlyReportExcel {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 处理 下载财务报表
     */
    public void handleMonthlyReport(YearMonth queryYearMonth) throws JsonProcessingException {
        // 获取项目根目录
        String projectRoot = System.getProperty("user.dir"); // D:\code\java\Java-CSDN
        // 拼接目标目录路径
        File excelDir = new File(projectRoot, "excel-export/src/main/resources/excel/");

        if (!excelDir.exists()) {
            excelDir.mkdirs(); // 创建多级目录
        }

        EduMonthlyReportDTO eduMonthlyReportDTO = getEduMonthlyReportFromJson();
        log.warn("eduMonthlyReportDTO:{}", objectMapper.writeValueAsString(eduMonthlyReportDTO));

        // 定义Excel文件路径
        File xlsxFile = new File(excelDir, "EduMonthlyReport.xlsx");

        try (FileOutputStream fileOutputStream = new FileOutputStream(xlsxFile);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // Step 1：导出 Excel 到输出流
            this.exportMonthlyReport(eduMonthlyReportDTO, queryYearMonth, outputStream);

            // 将ByteArrayOutputStream的内容写入文件
            outputStream.writeTo(fileOutputStream);

            log.info("Excel文件已生成: {}", xlsxFile.getAbsolutePath());

        } catch (Exception e) {
            log.error("生成Excel文件失败", e);
            throw new RuntimeException("生成Excel文件失败", e);
        }
    }

    /**
     * 从 JSON 文件中获取教育月度报告数据
     *
     * 本方法尝试从类路径下的 json 文件中读取并解析教育月度报告数据
     * 使用 ClassPathResource 和 Jackson ObjectMapper 实现文件的读取和 JSON 解析
     *
     * @return EduMonthlyReportDTO 解析后的教育月度报告对象
     * @throws RuntimeException 如果读取或解析 JSON 文件时发生错误，则抛出运行时异常
     */
    private EduMonthlyReportDTO getEduMonthlyReportFromJson() {
        try {
            // 1. 使用 ClassPathResource 读取 resources 目录下的文件
            ClassPathResource resource = new ClassPathResource("json/EduMonthlyReportDTO.json");

            // 2. 获取输入流
            InputStream inputStream = resource.getInputStream();

            // 3. 使用 Jackson ObjectMapper 解析 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // 添加以下配置
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
            EduMonthlyReportDTO eduMonthlyReportDTO = objectMapper.readValue(inputStream, EduMonthlyReportDTO.class);

            return eduMonthlyReportDTO;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read EduMonthlyReportDTO.json", e);
        }
    }

    public void exportMonthlyReport(EduMonthlyReportDTO eduMonthlyReportDTO, YearMonth queryYearMonth,
                                    ByteArrayOutputStream outputStream) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // ========== 创建样式 ==========
            // 基础字体
            XSSFFont defaultFont = workbook.createFont();
            defaultFont.setFontName("等线");
            defaultFont.setFontHeightInPoints((short) 11);

            XSSFFont boldFont = workbook.createFont();
            boldFont.setFontName("等线");
            boldFont.setFontHeightInPoints((short) 11);
            boldFont.setBold(true);

            // 1. 主标题样式
            CellStyle mainTitleStyle = workbook.createCellStyle();
            mainTitleStyle.setFont(boldFont);
            mainTitleStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(mainTitleStyle, BorderStyle.MEDIUM);

            // 2. 分组标题样式 (课程信息/销售收入等)
            CellStyle groupTitleStyle = workbook.createCellStyle();
            groupTitleStyle.setFont(boldFont);
            groupTitleStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(groupTitleStyle, BorderStyle.MEDIUM);

            // 3. 列头样式
            CellStyle columnHeaderStyle = workbook.createCellStyle();
            columnHeaderStyle.setFont(boldFont);
            columnHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(columnHeaderStyle, BorderStyle.MEDIUM);

            // 4. 左对齐文本样式 (产品ID)
            CellStyle leftAlignStyle = workbook.createCellStyle();
            leftAlignStyle.setFont(defaultFont);
            leftAlignStyle.setAlignment(HorizontalAlignment.LEFT);
            leftAlignStyle.setBorderLeft(BorderStyle.THIN);
            leftAlignStyle.setBorderBottom(BorderStyle.THIN);

            // 5. 左对齐粗边框样式 (产品名称)
            CellStyle leftAlignBoldBorderStyle = workbook.createCellStyle();
            leftAlignBoldBorderStyle.setFont(defaultFont);
            leftAlignBoldBorderStyle.setAlignment(HorizontalAlignment.LEFT);
            leftAlignBoldBorderStyle.setBorderLeft(BorderStyle.MEDIUM);
            leftAlignBoldBorderStyle.setBorderRight(BorderStyle.MEDIUM);
            leftAlignBoldBorderStyle.setBorderTop(BorderStyle.MEDIUM);
            leftAlignBoldBorderStyle.setBorderBottom(BorderStyle.THIN);

            // 6. 居中数字样式 (数量)
            CellStyle centerNumberStyle = workbook.createCellStyle();
            centerNumberStyle.setFont(defaultFont);
            centerNumberStyle.setAlignment(HorizontalAlignment.CENTER);
            centerNumberStyle.setBorderLeft(BorderStyle.MEDIUM);
            centerNumberStyle.setBorderRight(BorderStyle.MEDIUM);
            centerNumberStyle.setBorderTop(BorderStyle.MEDIUM);
            centerNumberStyle.setBorderBottom(BorderStyle.THIN);

            // 7. 居中金额样式 (销售收入等)
            CellStyle centerAmountStyle = workbook.createCellStyle();
            centerAmountStyle.setFont(defaultFont);
            centerAmountStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(centerAmountStyle, BorderStyle.THIN);

            // 8. 百分比样式
            CellStyle percentageStyle = workbook.createCellStyle();
            percentageStyle.setFont(defaultFont);
            percentageStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(percentageStyle, BorderStyle.THIN);
            percentageStyle.setDataFormat(workbook.createDataFormat().getFormat("0%"));

            // 9. TOTAL行样式
            CellStyle totalStyle = workbook.createCellStyle();
            totalStyle.setFont(defaultFont);
            totalStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(totalStyle, BorderStyle.MEDIUM);

            // ========== 创建工作表 ==========
            XSSFSheet sheet = workbook.createSheet("Sheet1");

            // 设置列宽
            sheet.setColumnWidth(0, 93 * 256 / 7);    // 产品ID
            sheet.setColumnWidth(1, 253 * 256 / 7);   // 产品名称
            sheet.setColumnWidth(2, 63 * 256 / 7);    // 数量
            sheet.setColumnWidth(3, 102 * 256 / 7);   // 销售收入
            sheet.setColumnWidth(4, 74 * 256 / 7);    // 订单收入
            sheet.setColumnWidth(5, 73 * 256 / 7);    // 总收入
            sheet.setColumnWidth(6, 115 * 256 / 7);   // 收入抵减
            sheet.setColumnWidth(7, 66 * 256 / 7);     // 课程性质
            sheet.setColumnWidth(8, 66 * 256 / 7);     // 合作方ID
            sheet.setColumnWidth(9, 69 * 256 / 7);    // 合作方简称
            sheet.setColumnWidth(10, 93 * 256 / 7);    // 合作方全称
            sheet.setColumnWidth(11, 93 * 256 / 7);   // 合作方分成比例

            int rowIndex = 0;

            // ========== 主标题行 ==========
            Row titleRow = sheet.createRow(rowIndex++);
            titleRow.setHeightInPoints(15);
            CellRangeAddress titleMerge = new CellRangeAddress(0, 0, 0, 11);
            sheet.addMergedRegion(titleMerge);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(queryYearMonth.getYear() + "年" + queryYearMonth.getMonthValue() + "月度\"无忧课堂\"财务报表");
            titleCell.setCellStyle(mainTitleStyle);
            setMergedRegionBorders(sheet, titleMerge, BorderStyle.MEDIUM);

            // ========== 分组标题行 ==========
            Row groupHeaderRow = sheet.createRow(rowIndex++);
            groupHeaderRow.setHeightInPoints(15);

            // 课程信息 (合并A-B列)
            CellRangeAddress courseInfoMerge = new CellRangeAddress(1, 1, 0, 1);
            sheet.addMergedRegion(courseInfoMerge);
            Cell courseInfoCell = groupHeaderRow.createCell(0);
            courseInfoCell.setCellValue("课程信息");
            courseInfoCell.setCellStyle(groupTitleStyle);
            setMergedRegionBorders(sheet, courseInfoMerge, BorderStyle.MEDIUM);

            // 销售收入 (合并C-E列)
            CellRangeAddress salesInfoMerge = new CellRangeAddress(1, 1, 2, 4);
            sheet.addMergedRegion(salesInfoMerge);
            Cell salesInfoCell = groupHeaderRow.createCell(2);
            salesInfoCell.setCellValue("销售收入");
            salesInfoCell.setCellStyle(groupTitleStyle);
            setMergedRegionBorders(sheet, salesInfoMerge, BorderStyle.MEDIUM);

            // 产品收入 (合并F-G列)
            CellRangeAddress incomeInfoMerge = new CellRangeAddress(1, 1, 5, 6);
            sheet.addMergedRegion(incomeInfoMerge);
            Cell incomeInfoCell = groupHeaderRow.createCell(5);
            incomeInfoCell.setCellValue("产品收入");
            incomeInfoCell.setCellStyle(groupTitleStyle);
            setMergedRegionBorders(sheet, incomeInfoMerge, BorderStyle.MEDIUM);

            // 课程说明 (合并H-K列)
            CellRangeAddress courseDescMerge = new CellRangeAddress(1, 1, 7, 11);
            sheet.addMergedRegion(courseDescMerge);
            Cell courseDescCell = groupHeaderRow.createCell(7);
            courseDescCell.setCellValue("课程说明");
            courseDescCell.setCellStyle(groupTitleStyle);
            setMergedRegionBorders(sheet, courseDescMerge, BorderStyle.MEDIUM);

            // ========== 列头行 ==========
            Row headerRow = sheet.createRow(rowIndex++);
            headerRow.setHeightInPoints(15);

            // 修改后的headers数组，确保列顺序正确
            String[] headers = {
                    "产品ID", "产品名称", "数量", "销售收入", "订单收入",
                    "总收入", "收入抵减", "课程性质", "合作方ID", "合作方简称",
                    "合作方全称", "合作方分成比例"
            };

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(columnHeaderStyle);
            }

            // ========== 数据行 ==========
            if (eduMonthlyReportDTO.getFinancialReport() != null && !eduMonthlyReportDTO.getFinancialReport().isEmpty()) {
                for (TrainMonthlyReportDTO item : eduMonthlyReportDTO.getFinancialReport()) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.setHeightInPoints(15);

                    // 产品ID
                    Cell productIdCell = dataRow.createCell(0);
                    productIdCell.setCellValue(item.getProductId());
                    productIdCell.setCellStyle(leftAlignStyle);

                    // 产品名称
                    Cell productNameCell = dataRow.createCell(1);
                    productNameCell.setCellValue(item.getProductName());
                    productNameCell.setCellStyle(leftAlignBoldBorderStyle);

                    // 数量
                    Cell numCell = dataRow.createCell(2);
                    numCell.setCellValue(item.getNum());
                    numCell.setCellStyle(centerNumberStyle);

                    // 销售收入
                    Cell payAmountCell = dataRow.createCell(3);
                    payAmountCell.setCellValue(item.getPayAmountShow());
                    payAmountCell.setCellStyle(centerAmountStyle);

                    // 订单收入
                    Cell orderIncomeCell = dataRow.createCell(4);
                    orderIncomeCell.setCellValue(item.getOrderIncomeShow());
                    orderIncomeCell.setCellStyle(centerAmountStyle);

                    // 总收入
                    Cell totalIncomeCell = dataRow.createCell(5);
                    totalIncomeCell.setCellValue(item.getTotalIncomeShow());
                    totalIncomeCell.setCellStyle(centerAmountStyle);

                    // 收入抵减
                    Cell incomeOffsetCell = dataRow.createCell(6);
                    incomeOffsetCell.setCellValue(item.getIncomeOffsetShow());
                    incomeOffsetCell.setCellStyle(centerAmountStyle);

                    // 课程性质
                    Cell productTypeCell = dataRow.createCell(7);
                    productTypeCell.setCellValue(item.getProductType());
                    productTypeCell.setCellStyle(centerAmountStyle);

                    // 合作方ID
                    Cell idTypeCell = dataRow.createCell(8);
                    idTypeCell.setCellValue(item.getCollaboratorId());
                    idTypeCell.setCellStyle(centerAmountStyle);

                    // 合作方简称
                    Cell nameAbbrCell = dataRow.createCell(9);
                    nameAbbrCell.setCellValue(item.getNameAbbr());
                    nameAbbrCell.setCellStyle(centerAmountStyle);

                    // 合作方全称
                    Cell nameCell = dataRow.createCell(10);
                    nameCell.setCellValue(item.getName());
                    nameCell.setCellStyle(centerAmountStyle);

                    // 合作方分成比例
                    Cell proportionCell = dataRow.createCell(11);
                    proportionCell.setCellValue(item.getProportion());
                    proportionCell.setCellStyle(percentageStyle);
                }
            }

            // ========== TOTAL行 ==========
            Row totalRow = sheet.createRow(rowIndex++);
            totalRow.setHeightInPoints(15);

            // TOTAL标签 (合并A-B列)
            CellRangeAddress totalLabelMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
            sheet.addMergedRegion(totalLabelMerge);
            Cell totalLabelCell = totalRow.createCell(0);
            totalLabelCell.setCellValue("TOTAL（月度）");
            totalLabelCell.setCellStyle(totalStyle);
            setMergedRegionBorders(sheet, totalLabelMerge, BorderStyle.MEDIUM);

            // 数量总计
            Cell totalNumCell = totalRow.createCell(2);
            totalNumCell.setCellValue(eduMonthlyReportDTO.getFinancialReportTotal());
            totalNumCell.setCellStyle(totalStyle);

            // 销售收入总计
            Cell totalPayAmountCell = totalRow.createCell(3);
            totalPayAmountCell.setCellValue(eduMonthlyReportDTO.getPayAmountCountShow());
            totalPayAmountCell.setCellStyle(totalStyle);

            // 订单收入总计
            Cell totalOrderIncomeCell = totalRow.createCell(4);
            totalOrderIncomeCell.setCellValue(eduMonthlyReportDTO.getOrderIncomeCountShow());
            totalOrderIncomeCell.setCellStyle(totalStyle);

            // 总收入总计
            Cell totalIncomeCell = totalRow.createCell(5);
            totalIncomeCell.setCellValue(eduMonthlyReportDTO.getTotalIncomeCountShow());
            totalIncomeCell.setCellStyle(totalStyle);

            // 收入抵减总计
            Cell totalIncomeOffsetCell = totalRow.createCell(6);
            totalIncomeOffsetCell.setCellValue(eduMonthlyReportDTO.getIncomeOffsetCountShow());
            totalIncomeOffsetCell.setCellStyle(totalStyle);

            // 合并H-K列并设置边框 (课程说明部分)
            CellRangeAddress emptyMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 7, 11);
            sheet.addMergedRegion(emptyMerge);
            Cell emptyContentCell = totalRow.createCell(7);
            emptyContentCell.setCellStyle(totalStyle);
            setMergedRegionBorders(sheet, emptyMerge, BorderStyle.MEDIUM);

            // ========== 空行 ==========
            for (int i = 0; i < 4; i++) {
                Row emptyRow = sheet.createRow(rowIndex++);
                for (int j = 0; j < 12; j++) {
                    emptyRow.createCell(j).setCellStyle(workbook.createCellStyle());
                }
            }

            // ========== 支付渠道信息 ==========
            // 支付渠道标题行
            Row paymentTitleRow = sheet.createRow(rowIndex++);
            paymentTitleRow.setHeightInPoints(15);

            // 支付渠道标题 (合并A-C列)
            CellStyle paymentTitleStyle = workbook.createCellStyle();
            paymentTitleStyle.setFont(boldFont);
            paymentTitleStyle.setAlignment(HorizontalAlignment.LEFT);
            setBorders(paymentTitleStyle, BorderStyle.MEDIUM);

            CellStyle paymentContentStyle = workbook.createCellStyle();
            paymentContentStyle.setFont(defaultFont);
            paymentContentStyle.setAlignment(HorizontalAlignment.LEFT);
            setBorders(paymentContentStyle, BorderStyle.THIN);

            // 12. 支付渠道金额样式
            CellStyle paymentAmountStyle = workbook.createCellStyle();
            paymentAmountStyle.setFont(defaultFont);
            paymentAmountStyle.setAlignment(HorizontalAlignment.CENTER);
            setBorders(paymentAmountStyle, BorderStyle.MEDIUM);

            CellRangeAddress paymentTitleMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
            sheet.addMergedRegion(paymentTitleMerge);
            Cell paymentTitleCell = paymentTitleRow.createCell(0);
            paymentTitleCell.setCellValue("支付渠道");
            paymentTitleCell.setCellStyle(paymentTitleStyle);
            setMergedRegionBorders(sheet, paymentTitleMerge, BorderStyle.MEDIUM);

            // 成交金额标题
            Cell paymentAmountTitleCell = paymentTitleRow.createCell(3);
            paymentAmountTitleCell.setCellValue("成交金额");
            paymentAmountTitleCell.setCellStyle(paymentAmountStyle);

            // 空单元格 (D-L列)
            for (int i = 4; i < 12; i++) {
                paymentTitleRow.createCell(i).setCellStyle(workbook.createCellStyle());
            }

            // 支付渠道数据行
            if (eduMonthlyReportDTO.getPayChannelAmount() != null && !eduMonthlyReportDTO.getPayChannelAmount().isEmpty()) {
                for (PayAmountDTO item : eduMonthlyReportDTO.getPayChannelAmount()) {
                    Row paymentRow = sheet.createRow(rowIndex++);
                    paymentRow.setHeightInPoints(15);

                    // 支付方式 (合并A-C列)
                    CellRangeAddress paymentMethodMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
                    sheet.addMergedRegion(paymentMethodMerge);
                    Cell paymentMethodCell = paymentRow.createCell(0);
                    paymentMethodCell.setCellValue(item.getPayMethodShow());
                    paymentMethodCell.setCellStyle(paymentContentStyle);
                    setMergedRegionBorders(sheet, paymentMethodMerge, BorderStyle.THIN);

                    // 支付金额
                    Cell paymentAmountCell = paymentRow.createCell(3);
                    paymentAmountCell.setCellValue(item.getPayAmountShow());
                    paymentAmountCell.setCellStyle(paymentAmountStyle);

                    // 空单元格 (D-L列)
                    for (int i = 4; i < 12; i++) {
                        paymentRow.createCell(i).setCellStyle(workbook.createCellStyle());
                    }
                }
            }

            // 支付渠道总计行
            Row paymentTotalRow = sheet.createRow(rowIndex++);
            paymentTotalRow.setHeightInPoints(15);

            // 支付渠道总计标题 (合并A-C列)
            CellRangeAddress paymentTotalTitleMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
            sheet.addMergedRegion(paymentTotalTitleMerge);
            Cell paymentTotalTitleCell = paymentTotalRow.createCell(0);
            paymentTotalTitleCell.setCellValue("TOTAL（月度）");
            paymentTotalTitleCell.setCellStyle(paymentTitleStyle);
            setMergedRegionBorders(sheet, paymentTotalTitleMerge, BorderStyle.MEDIUM);

            // 支付渠道总计金额
            Cell paymentTotalAmountCell = paymentTotalRow.createCell(3);
            paymentTotalAmountCell.setCellValue(eduMonthlyReportDTO.getPayChannelAmountCountShow());
            paymentTotalAmountCell.setCellStyle(paymentAmountStyle);

            // 空单元格 (D-L列)
            for (int i = 4; i < 12; i++) {
                paymentTotalRow.createCell(i).setCellStyle(workbook.createCellStyle());
            }

            // ========== 空行 ==========
            for (int i = 0; i < 3; i++) {
                Row emptyRow = sheet.createRow(rowIndex++);
                for (int j = 0; j < 12; j++) {
                    emptyRow.createCell(j).setCellStyle(workbook.createCellStyle());
                }
            }

            // ========== 产品账务信息 ==========
            // 产品账务标题行
            Row productAccountTitleRow = sheet.createRow(rowIndex++);
            productAccountTitleRow.setHeightInPoints(15);

            // 产品账务标题 (合并A-D列)
            CellRangeAddress productAccountTitleMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 3);
            sheet.addMergedRegion(productAccountTitleMerge);
            Cell productAccountTitleCell = productAccountTitleRow.createCell(0);
            productAccountTitleCell.setCellValue(queryYearMonth.getYear() + "年" + queryYearMonth.getMonthValue() + "月度\"无忧课堂\"产品账务");
            productAccountTitleCell.setCellStyle(mainTitleStyle);
            setMergedRegionBorders(sheet, productAccountTitleMerge, BorderStyle.MEDIUM);

            // 空单元格 (E-L列)
            for (int i = 4; i < 12; i++) {
                productAccountTitleRow.createCell(i).setCellStyle(workbook.createCellStyle());
            }

            // 产品账务列头行
            Row productAccountHeaderRow = sheet.createRow(rowIndex++);
            productAccountHeaderRow.setHeightInPoints(15);

            // 产品类型 (合并A-B列)
            CellStyle productAccountTitleStyle = workbook.createCellStyle();
            productAccountTitleStyle.setFont(boldFont);
            productAccountTitleStyle.setAlignment(HorizontalAlignment.LEFT);
            productAccountTitleStyle.setBorderLeft(BorderStyle.MEDIUM);
            productAccountTitleStyle.setBorderRight(BorderStyle.MEDIUM);
            productAccountTitleStyle.setBorderTop(BorderStyle.MEDIUM);

            // 14. 产品账务内容样式
            CellStyle productAccountContentStyle = workbook.createCellStyle();
            productAccountContentStyle.setFont(defaultFont);
            productAccountContentStyle.setAlignment(HorizontalAlignment.LEFT);
            setBorders(productAccountContentStyle, BorderStyle.THIN);


            CellRangeAddress productTypeMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
            sheet.addMergedRegion(productTypeMerge);
            Cell productTypeCell = productAccountHeaderRow.createCell(0);
            productTypeCell.setCellValue("产品类型");
            productTypeCell.setCellStyle(productAccountTitleStyle);
            setMergedRegionBorders(sheet, productTypeMerge, BorderStyle.MEDIUM);

            // 总收入标题
            Cell totalIncomeTitleCell = productAccountHeaderRow.createCell(2);
            totalIncomeTitleCell.setCellValue("总收入");
            totalIncomeTitleCell.setCellStyle(productAccountTitleStyle);

            // 收入抵减标题
            Cell incomeOffsetTitleCell = productAccountHeaderRow.createCell(3);
            incomeOffsetTitleCell.setCellValue("收入抵减");
            incomeOffsetTitleCell.setCellStyle(productAccountTitleStyle);

            // 空单元格 (D-L列)
            for (int i = 4; i < 12; i++) {
                productAccountHeaderRow.createCell(i).setCellStyle(workbook.createCellStyle());
            }

            // 产品账务数据行
            if (eduMonthlyReportDTO.getProductAmount() != null && !eduMonthlyReportDTO.getProductAmount().isEmpty()) {
                for (ProductAmountDTO item : eduMonthlyReportDTO.getProductAmount()) {
                    Row productAccountRow = sheet.createRow(rowIndex++);
                    productAccountRow.setHeightInPoints(15);

                    // 产品类型
                    Cell productTypeDataCell = productAccountRow.createCell(0);
                    productTypeDataCell.setCellValue(item.getProductType());
                    productTypeDataCell.setCellStyle(productAccountContentStyle);

                    // 产品类型描述
                    Cell productTypeDescCell = productAccountRow.createCell(1);
                    productTypeDescCell.setCellValue(item.getProductTypeDesc());
                    productTypeDescCell.setCellStyle(productAccountContentStyle);

                    // 总收入
                    Cell totalIncomeDataCell = productAccountRow.createCell(2);
                    totalIncomeDataCell.setCellValue(item.getTotalIncomeShow());
                    totalIncomeDataCell.setCellStyle(centerAmountStyle);

                    // 收入抵减
                    Cell incomeOffsetDataCell = productAccountRow.createCell(3);
                    incomeOffsetDataCell.setCellValue(item.getIncomeOffsetShow());
                    incomeOffsetDataCell.setCellStyle(centerAmountStyle);

                    // 空单元格 (D-L列)
                    for (int i = 4; i < 12; i++) {
                        productAccountRow.createCell(i).setCellStyle(workbook.createCellStyle());
                    }
                }
            }

            // 产品账务总计行
            Row productAccountTotalRow = sheet.createRow(rowIndex++);
            productAccountTotalRow.setHeightInPoints(15);

            // 产品账务总计标题 (合并A-B列)
            CellRangeAddress productAccountTotalMerge = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
            sheet.addMergedRegion(productAccountTotalMerge);
            Cell productAccountTotalCell = productAccountTotalRow.createCell(0);
            productAccountTotalCell.setCellValue("TOTAL(月度)");
            productAccountTotalCell.setCellStyle(totalStyle);
            setMergedRegionBorders(sheet, productAccountTotalMerge, BorderStyle.MEDIUM);

            // 总收入总计
            Cell productTotalIncomeCell = productAccountTotalRow.createCell(2);
            productTotalIncomeCell.setCellValue(eduMonthlyReportDTO.getProductTotalIncomeCountShow());
            productTotalIncomeCell.setCellStyle(totalStyle);

            // 收入抵减总计
            Cell productIncomeOffsetCell = productAccountTotalRow.createCell(3);
            productIncomeOffsetCell.setCellValue(eduMonthlyReportDTO.getProductIncomeOffsetCountShow());
            productIncomeOffsetCell.setCellStyle(totalStyle);

            // 空单元格 (D-L列)
            for (int i = 4; i < 12; i++) {
                productAccountTotalRow.createCell(i).setCellStyle(workbook.createCellStyle());
            }

            // 创建其他工作表
            workbook.createSheet("Sheet2");
            workbook.createSheet("Sheet3");

            // 写入输出流
            workbook.write(outputStream);
        }
    }

    // 辅助方法：设置单元格边框
    private void setBorders(CellStyle style, BorderStyle borderStyle) {
        style.setBorderTop(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
    }

    // 辅助方法：设置合并单元格的边框
    private void setMergedRegionBorders(XSSFSheet sheet,
                                        CellRangeAddress region, BorderStyle borderStyle) {
        RegionUtil.setBorderTop(borderStyle, region, sheet);
        RegionUtil.setBorderBottom(borderStyle, region, sheet);
        RegionUtil.setBorderLeft(borderStyle, region, sheet);
        RegionUtil.setBorderRight(borderStyle, region, sheet);
    }
}