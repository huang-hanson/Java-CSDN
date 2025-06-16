package com.fileimport.export.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.property.ExcelReadHeadProperty;
import com.alibaba.excel.util.ListUtils;
import com.fileimport.export.entity.query.StudentImportQuery;
import com.fileimport.export.entity.vo.StudentInfoImportCountVO;
import com.fileimport.export.enums.FileImportErrorEnum;
import com.fileimport.export.exception.FileImportException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoReadListener
 * @Description 学新信息模板的读取类
 * @date 2025/6/16 16:21
 * <p>
 * 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 * <a href="https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read#web%E4%B8%AD%E7%9A%84%E8%AF%BB">Easy Excel 导入示例</a>
 **/
@Slf4j
public class StudentInfoReadListener implements ReadListener<StudentImportQuery> {

    /**
     * excel文件导入最大行数
     */
    public static final int MAX_ROW_NUM = 10000;
    /**
     * 分批写入数据库，避免OOM
     */
    private static final int BATCH_COUNT = 2000;
    private List<StudentImportQuery> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private final IStudentInfoService studentInfoService;

    @Getter
    private final StudentInfoImportCountVO counterVO = new StudentInfoImportCountVO();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public StudentInfoReadListener(IStudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. It is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(StudentImportQuery data, AnalysisContext context) {
        // 获取表头行数
        ExcelReadHeadProperty excelReadHeadProperty = context.currentReadHolder().excelReadHeadProperty();
        int headRowNumber = excelReadHeadProperty.getHeadRowNumber();

        // 获取表格总行数（含表头）
        Integer totalRowNumber = context.readSheetHolder().getApproximateTotalRowNumber();
        if (totalRowNumber > MAX_ROW_NUM) {
            throw new FileImportException(FileImportErrorEnum.UPLOAD_EXCEL_MAX_ROW_ERROR, "导入文件行数太多，总行数：" + totalRowNumber);
        }

        // 获取记录行号并写入，用于后续计数
        Integer rowIndex = context.readRowHolder().getRowIndex();
        data.setRowNum(rowIndex + headRowNumber);

        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            studentInfoService.importStudentInfo(cachedDataList, counterVO);
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        studentInfoService.importStudentInfo(cachedDataList, counterVO);
    }
}