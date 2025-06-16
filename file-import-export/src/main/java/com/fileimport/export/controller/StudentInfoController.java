package com.fileimport.export.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.csdn.dev.common.entity.dto.Result;
import com.csdn.dev.common.util.DateUtils;
import com.fileimport.export.constant.Constant;
import com.fileimport.export.entity.query.StudentExportPageQuery;
import com.fileimport.export.entity.vo.StudentInfoImportCountVO;
import com.fileimport.export.enums.FileImportErrorEnum;
import com.fileimport.export.exception.FileImportException;
import com.fileimport.export.service.IStudentInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Optional;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoCOntroller
 * @Description TODO
 * @date 2025/6/16 17:48
 **/
@Slf4j
@RestController
public class StudentInfoController {

    @Resource
    private IStudentInfoService studentInfoService;

    /**
     * 知识库-导入
     *
     * @param multipartFile 导入的文件
     * @return
     * @ignore
     */
    @PostMapping("/student-info/import")
    public Result<StudentInfoImportCountVO> importKnowledgeBase(@RequestPart MultipartFile multipartFile) throws IOException {

        // 判断是否为excel文件
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = Optional.ofNullable(fileName).map(name -> name.substring(fileName.lastIndexOf(StringPool.DOT) + 1)).orElse(null);
        if (!Lists.newArrayList("xls", "xlsx").contains(fileSuffix)) {
            throw new FileImportException(FileImportErrorEnum.UPLOAD_EXCEL_TYPE_ERROR, "文件格式不正确，文件名：" + fileName);
        }

        // 判断文件大小
        long size = multipartFile.getSize();
        if (size > Constant.MAX_UPLOAD_EXCEL_SIZE) {
            throw new FileImportException(FileImportErrorEnum.UPLOAD_EXCEL_MAX_SIZE_ERROR, "文件大于10M，文件大小：" + size);
        }

        InputStream inputStream = multipartFile.getInputStream();
        StudentInfoImportCountVO counterVO = studentInfoService.doImport(inputStream);
        return Result.success(counterVO);
    }

    /**
     * 知识库-导出
     *
     * @param pageQuery 查询参数
     * @return
     * @ignore
     */
    @PostMapping("/student-info/export")
    public void exportKnowledgeBase(HttpServletResponse response, @RequestBody StudentExportPageQuery pageQuery) {
        String fileName = "学生信息" + DateUtils.getTodayDateString(DateUtils.DATE_STRING_PATTERN);
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            studentInfoService.exportStudentInfo(response, pageQuery);
        } catch (Exception e) {
            log.error("知识库导出{}，程序执行异常，e-{}", fileName, e.toString());
            throw new FileImportException(FileImportErrorEnum.FILE_EXPORT_FAILURE_ERROR, "文件导出异常", e);
        }
    }
}