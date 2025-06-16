package com.fileimport.export.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.fileimport.export.entity.query.StudentImportQuery;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentExcelGenerator {

    private static final String[] DEPARTMENTS = {"计算机学院", "经济学院", "法学院", "医学院", "艺术学院"};
    private static final String[] MAJORS = {"计算机科学与技术", "软件工程", "经济学", "法学", "临床医学", "音乐表演"};
    private static final String[] NATIONALITIES = {"汉族", "回族", "满族", "蒙古族", "藏族", "维吾尔族"};

    private static final AtomicLong STUDENT_NO_COUNTER = new AtomicLong(2023000001L);

    public static void main(String[] args) {
//        String fileName = "student_data_3000.xlsx";  // 完整路径
        // 指定目标目录（相对或绝对路径）
        String dirPath = "./file-import-export/src/main/resources/testData/";  // 项目根目录下的 testData 文件夹
        String fileName = dirPath + "student_data_3000.xlsx";

        // 确保目录存在
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();  // 创建多级目录
        }

        // 设置表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);

        // 设置内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        // 创建样式策略
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(
                headWriteCellStyle,
                contentWriteCellStyle
        );

        // 生成3000条数据
        List<StudentImportQuery> data = IntStream.range(0, 3000)
                .parallel()
                .mapToObj(i -> generateStudent(i + 1))
                .collect(Collectors.toList());

        // 写入Excel
        EasyExcel.write(fileName, StudentImportQuery.class)
                .registerWriteHandler(styleStrategy) // 注册样式策略
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 自动列宽
                .sheet("学生数据")
                .doWrite(data);

        System.out.println("Excel文件生成完成: " + fileName);
    }

    private static StudentImportQuery generateStudent(int rowNum) {
        Random random = new Random();
        StudentImportQuery student = new StudentImportQuery();

        student.setRowNum(rowNum);
        student.setStudentNo(String.valueOf(STUDENT_NO_COUNTER.getAndIncrement()));
        student.setName(generateChineseName(random));
        student.setGender(random.nextInt(2));
        student.setBirthday(LocalDate.of(1995 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28)));
        student.setIdCard(generateIdCard(random, student.getBirthday()));
        student.setDepartment(DEPARTMENTS[random.nextInt(DEPARTMENTS.length)]);
        student.setMajor(MAJORS[random.nextInt(MAJORS.length)]);
        student.setClassName(student.getDepartment().substring(0, 2) + (20 + random.nextInt(5)) + "级" + (1 + random.nextInt(10)) + "班");
        student.setEnrollmentYear(2018 + random.nextInt(5));
        student.setPhone("1" + (3 + random.nextInt(7)) + String.format("%09d", random.nextInt(1000000000)));
        student.setEmail(student.getStudentNo() + "@university.edu.cn");
        student.setAddress(generateAddress(random));
        student.setPoliticalStatus(random.nextInt(3));
        student.setNationality(NATIONALITIES[random.nextInt(NATIONALITIES.length)]);
        student.setRemark(random.nextInt(10) < 2 ? "特殊学生，需关注" : "");

        return student;
    }

    private static String generateChineseName(Random random) {
        String[] SURNAMES = {"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴"};
        String[] GIVEN_NAMES = {"伟", "芳", "娜", "秀英", "敏", "静", "丽", "强", "磊", "军", "洋",
                "勇", "艳", "杰", "娟", "涛", "明", "超", "秀兰", "霞", "平", "刚"};

        return SURNAMES[random.nextInt(SURNAMES.length)] +
                (random.nextBoolean() ? GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)] :
                        GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)] + GIVEN_NAMES[random.nextInt(GIVEN_NAMES.length)]);
    }

    private static String generateIdCard(Random random, LocalDate birthday) {
        // 生成模拟身份证号
        return "110" + (100 + random.nextInt(900)) + // 区县代码
                birthday.toString().replace("-", "") + // 出生日期
                String.format("%03d", random.nextInt(1000)) + // 顺序码
                (random.nextBoolean() ? "X" : String.valueOf(random.nextInt(10))); // 校验码
    }

    private static String generateAddress(Random random) {
        String[] PROVINCES = {"北京", "上海", "广东", "江苏", "浙江", "四川", "湖北", "湖南", "山东", "河南"};
        String[] CITIES = {"市辖区", "海淀区", "朝阳区", "浦东新区", "天河区", "西湖区", "武侯区", "武昌区", "芙蓉区", "历下区"};
        String[] STREETS = {"中关村大街", "人民路", "解放路", "中山路", "建设路", "文化路", "和平路", "新华路", "青年路", "学院路"};

        return PROVINCES[random.nextInt(PROVINCES.length)] + "省" +
                CITIES[random.nextInt(CITIES.length)] +
                STREETS[random.nextInt(STREETS.length)] +
                (100 + random.nextInt(900)) + "号";
    }
}