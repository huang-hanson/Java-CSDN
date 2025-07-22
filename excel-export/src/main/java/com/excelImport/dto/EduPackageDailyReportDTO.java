package com.excelImport.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PackageDailyReportDTO
 * @Description 打包课日常明细表
 * @date 2025/6/28 13:38
 **/
@Data
public class EduPackageDailyReportDTO {

    private List<PackageInfo> packageInfo;

    private Integer numCount;

    private BigDecimal pacoPriceCount;

    private BigDecimal incomeOffsetCount;

    @Data
    public static class PackageInfo {

        /**
         * 时间
         */
        private String dateShow;

        /**
         * 所属课包
         */
        private String productId;

        /**
         * 课程ID
         */
        private Integer lessonId;

        /**
         * 课程名
         */
        private String lessonName;

        /**
         * 数量
         */
        private Integer num;

        /**
         * 合作方打包结算价
         */
        private BigDecimal pacoPrice;

        /**
         * 收入抵减
         */
        private BigDecimal incomeOffset;

        /**
         * 课程性质
         * 0100：无忧课堂自营课程
         * 0200：无忧课堂HY课程
         * 0300：无忧课堂非HY课程
         */
        private String productType;

        /**
         * 合作方ID
         */
        private Integer collaboratorId;

        /**
         * 合作方简称
         */
        private String nameAbbr;

        /**
         * 合作方全称
         */
        private String name;

        /**
         * 合作方分成比例
         */
        private String percentage = "固定";

        public void setDate(LocalDate date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            this.dateShow = date.format(formatter);
        }
    }
}