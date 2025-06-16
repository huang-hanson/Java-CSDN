package com.fileimport.export.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fileimport.export.entity.bo.StudentInfoBO;
import com.fileimport.export.entity.query.StudentExportPageQuery;
import com.fileimport.export.entity.query.StudentImportQuery;
import com.fileimport.export.entity.vo.StudentInfoExportVO;
import com.fileimport.export.entity.vo.StudentInfoImportCountVO;
import com.fileimport.export.enums.DepartmentEnum;
import com.fileimport.export.enums.MajorEnum;
import com.fileimport.export.mapper.StudentInfoMapper;
import com.fileimport.export.service.IStudentInfoService;
import com.fileimport.export.service.StudentInfoReadListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoServiceImpl
 * @Description TODO
 * @date 2025/6/16 16:33
 **/
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfoBO> implements IStudentInfoService {

    @Override
    public StudentInfoImportCountVO doImport(InputStream inputStream) {
        StudentInfoReadListener readListener = new StudentInfoReadListener(this);
        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(inputStream, StudentImportQuery.class, readListener);
        // 表明标题行数为1
        excelReaderBuilder.headRowNumber(1);
        excelReaderBuilder.sheet().doRead();
        return readListener.getCounterVO();
    }

    @Override
    public void exportStudentInfo(HttpServletResponse response, StudentExportPageQuery pageQuery) throws IOException {
        int pageSize = 2000;

        LambdaQueryWrapper<StudentInfoBO> queryWrapper = getKnowledgeBaseLambdaQueryWrapper(pageQuery);
        Page<StudentInfoBO> page = this.page(new Page<>(1, pageSize), queryWrapper);
        long pages = page.getPages();

        // 分批查询导出
        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), StudentInfoExportVO.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            for (long p = 1; p <= pages; p++) {
                page = this.page(new Page<>(p, pageSize), queryWrapper);
                List<StudentInfoExportVO> list = page.getRecords().stream().map(item -> {
                    StudentInfoExportVO vo = new StudentInfoExportVO(item);
                    return vo;
                }).collect(Collectors.toList());
                excelWriter.write(list, writeSheet);
            }
        }
    }

    /**
     * 学生信息列表及导出查询条件
     *
     * @param pageQuery
     * @return
     */
    @NotNull
    private static LambdaQueryWrapper<StudentInfoBO> getKnowledgeBaseLambdaQueryWrapper(StudentExportPageQuery pageQuery) {
        LambdaQueryWrapper<StudentInfoBO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(pageQuery.getGender() != null, StudentInfoBO::getGender, pageQuery.getGender());
        queryWrapper.eq(pageQuery.getStatus() != null, StudentInfoBO::getStatus, pageQuery.getStatus());
        queryWrapper.orderByDesc(StudentInfoBO::getStudentNo);
        return queryWrapper;
    }

    @Override
    public StudentInfoImportCountVO importStudentInfo(List<StudentImportQuery> studentImportQuery, StudentInfoImportCountVO counterVO) {
        List<Integer> insertStudentRowNums = studentImportQuery.stream().map(StudentImportQuery::getRowNum).collect(Collectors.toList());
        List<StudentInfoBO> collect = studentImportQuery.stream().map(query -> convertStudentInfoBO(query)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            boolean insertBatch = false;
            try {
                insertBatch = this.saveBatch(collect);
            } catch (Exception e) {
                log.error("批量新增记录失败, e");
            }

            if (insertBatch) {
                insertStudentRowNums.forEach(n -> counterVO.insertSuccessIncrement());
            } else {
                insertStudentRowNums.forEach(counterVO::failureIncrement);
            }
        }
        return counterVO;
    }

    @NotNull
    private StudentInfoBO convertStudentInfoBO(StudentImportQuery query) {
        StudentInfoBO newStudentInfoBO = new StudentInfoBO();

        newStudentInfoBO.setStudentNo(query.getStudentNo());
        newStudentInfoBO.setName(query.getName());
        newStudentInfoBO.setGender(query.getGender());
        newStudentInfoBO.setBirthday(query.getBirthday());
        newStudentInfoBO.setIdCard(query.getIdCard());
        newStudentInfoBO.setDepartmentId(DepartmentEnum.getEnumByName(query.getDepartment()).getCode());
        newStudentInfoBO.setMajorId(MajorEnum.getEnumByName(query.getMajor()).getCode());
        newStudentInfoBO.setClassName(query.getClassName());
        newStudentInfoBO.setEnrollmentYear(query.getEnrollmentYear());
        newStudentInfoBO.setStatus(0);
        newStudentInfoBO.setPhone(query.getPhone());
        newStudentInfoBO.setEmail(query.getEmail());
        newStudentInfoBO.setAddress(query.getAddress());
        newStudentInfoBO.setPoliticalStatus(query.getPoliticalStatus());
        newStudentInfoBO.setNationality(query.getNationality());
        newStudentInfoBO.setCreateTime(LocalDateTime.now());
        newStudentInfoBO.setUpdateTime(LocalDateTime.now());
        newStudentInfoBO.setDeleted(0);

        return newStudentInfoBO;
    }
}