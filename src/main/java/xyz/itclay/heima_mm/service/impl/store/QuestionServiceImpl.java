package xyz.itclay.heima_mm.service.impl.store;

import com.github.pagehelper.PageHelper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.itclay.heima_mm.domain.store.Question;
import xyz.itclay.heima_mm.mapper.store.QuestionMapper;
import xyz.itclay.heima_mm.service.store.QuestionService;
import xyz.itclay.heima_mm.utils.DateTimeUtil;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/7 9:39
 **/
public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<Question> findByPage(String pageNum, String pageSize) {
        QuestionMapper mapper = MyBatisUtils.getMapper(QuestionMapper.class);
        Integer pageN = 1;
        Integer pageS = 10;
        if (StringUtils.isNotBlank(pageNum)) {
            pageN = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)) {
            pageS = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(pageN, pageS);
        return mapper.findAll();
    }

    @Override
    public Question findById(String id) {
        QuestionMapper mapper = MyBatisUtils.getMapper(QuestionMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void updateQuestion(Question question) {
        QuestionMapper mapper = MyBatisUtils.getMapper(QuestionMapper.class);
        question.setCourseId("1");
        mapper.updateQuestion(question);
    }

    @Override
    public String addQuestion(Question question, boolean flag) {
        QuestionMapper mapper = MyBatisUtils.getMapper(QuestionMapper.class);
        question.setId(GuidUtil.getUuid());
        question.setReviewStatus("0");
        try {
            question.setCreateTime(DateTimeUtil.getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (flag) {
            //设置当前存储的图片名称为id值
            question.setPicture(question.getId());
        }
        mapper.addQuestion(question);

        return question.getId();
    }


    /**
     * 报表导出
     * @return
     */
    @SneakyThrows
    @Override
    public ByteArrayOutputStream getReport() {
        QuestionMapper mapper = MyBatisUtils.getMapper(QuestionMapper.class);
        List<Question> questionList = mapper.findAll();
        //       1.获取对应的excel文件
        Workbook workbook = new XSSFWorkbook();
//       2.创建工作表
        Sheet sheet = workbook.createSheet("数据文件");

//      制作标题
//      合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 12));
        Row row1 = sheet.createRow(1);
        Cell row1Cell = row1.createCell(1);
        row1Cell.setCellValue("在线试题导出信息");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        row1Cell.setCellStyle(cellStyle);
//      制作标题
        String[] fields = {"题目ID", "所属公司ID", "所属目录ID", "题目简介", "题干描述",
                "题干配图", "题目分析", "题目类型", "题目难度", "是否经典题", "题目状态", "审核状态"};
        Row row2 = sheet.createRow(2);
        for (int i = 0; i < fields.length; i++) {
            Cell row2Cell = row2.createCell(1 + i);
            row2Cell.setCellValue(fields[i]);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            row2Cell.setCellStyle(cellStyle);
        }
        //制作数据区
        int row_index = 0;
        for (Question q : questionList) {
            int cell_index = 0;
            Row row_temp = sheet.createRow(3 + row_index++);

            Cell cell_data_1 = row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());    //++
            cell_data_1.setCellStyle(cellStyle);

            Cell cell_data_2 = row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());    //++
            cell_data_2.setCellStyle(cellStyle);

            Cell cell_data_3 = row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());    //++
            cell_data_3.setCellStyle(cellStyle);

            Cell cell_data_4 = row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());    //++
            cell_data_4.setCellStyle(cellStyle);

            Cell cell_data_5 = row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());    //++
            cell_data_5.setCellStyle(cellStyle);

            Cell cell_data_6 = row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());    //++
            cell_data_6.setCellStyle(cellStyle);

            Cell cell_data_7 = row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());    //++
            cell_data_7.setCellStyle(cellStyle);

            Cell cell_data_8 = row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());    //++
            cell_data_8.setCellStyle(cellStyle);

            Cell cell_data_9 = row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());    //++
            cell_data_9.setCellStyle(cellStyle);

            Cell cell_data_10 = row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());    //++
            cell_data_10.setCellStyle(cellStyle);

            Cell cell_data_11 = row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());    //++
            cell_data_11.setCellStyle(cellStyle);

            Cell cell_data_12 = row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());    //++
            cell_data_12.setCellStyle(cellStyle);
        }
/*
//      创建文件对象，以excel形式输出文件
        File file = new File("test.xlsx");
//      输出通过流的形式对外输出
        FileOutputStream fileOutputStream = new FileOutputStream(file);*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      将内存中的workbook数据写入到流中
        workbook.write(byteArrayOutputStream);
        workbook.close();
        return byteArrayOutputStream;
    }
}

