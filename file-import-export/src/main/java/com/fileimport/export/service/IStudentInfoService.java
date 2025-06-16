package com.fileimport.export.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fileimport.export.entity.bo.StudentInfoBO;
import com.fileimport.export.entity.query.StudentExportPageQuery;
import com.fileimport.export.entity.query.StudentImportQuery;
import com.fileimport.export.entity.vo.StudentInfoImportCountVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName IStudentInfoService
 * @Description TODO
 * @date 2025/6/16 16:33
 **/
public interface IStudentInfoService extends IService<StudentInfoBO> {
    /**
     * 导入操作
     * @param inputStream
     * @return
     */
    StudentInfoImportCountVO doImport(InputStream inputStream);

    /**
     * 导出知识库
     * @param response
     * @param pageQuery
     * @throws IOException
     */
    void exportStudentInfo(HttpServletResponse response, StudentExportPageQuery pageQuery) throws IOException;

    /**
     * 导入学生信息
     * @return
     */
    StudentInfoImportCountVO importStudentInfo(List<StudentImportQuery> studentImportQuery, StudentInfoImportCountVO counterVO);
}
