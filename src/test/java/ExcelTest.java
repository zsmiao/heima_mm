import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import xyz.itclay.heima_mm.domain.store.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    @SneakyThrows
    @Test
    public void excelTest() {
//       1.获取对应的excel文件
        Workbook workbook = new XSSFWorkbook();
//       2.创建工作表
        Sheet sheet = workbook.createSheet();
        workbook.createSheet("测试标题");
//       3.创建行
        Row row = sheet.createRow(1);
//       4.创建行中的列
        Cell cell = row.createCell(1);
//       5.在单元格中写入数据
        cell.setCellValue("测试单元格");

//      创建文件对象，以excel形式输出文件
        File file = new File("test.xlsx");
//      输出通过流的形式对外输出
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//      将内存中的workbook数据写入到流中
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }

    @SneakyThrows
    @Test
    public void excelReadTest() {
//      获取要读取的文件工作簿对象
        Workbook workbook = new XSSFWorkbook("test.xlsx");
//      获取工作簿
        Sheet sheetAt = workbook.getSheetAt(0);
//      获取行
        Row row = sheetAt.getRow(1);
//      获取列
        Cell cell = row.getCell(1);
//      根据数据的类型获取数据
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);
        workbook.close();
    }

    @SneakyThrows
    @Test
    public void excelProjectTest() {
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

        List<Question> questionList = new ArrayList<>();
        Question qq = new Question();
        qq.setId("1");
        qq.setPicture("12");
        qq.setReviewStatus("13");
        qq.setAnalysis("14");
        qq.setCatalogId("15");
        qq.setCompanyId("16");
        qq.setDifficulty("17");
        qq.setIsClassic("18");
        qq.setRemark("19");
        qq.setState("21");
        qq.setSubject("31");
        qq.setType("41");
        questionList.add(qq);
        Question qqq = new Question();
        qqq.setId("1");
        qqq.setPicture("12");
        qqq.setReviewStatus("13");
        qqq.setAnalysis("14");
        qqq.setCatalogId("15");
        qqq.setCompanyId("16");
        qqq.setDifficulty("17");
        qqq.setIsClassic("18");
        qqq.setRemark("19");
        qqq.setState("21");
        qqq.setSubject("31");
        qqq.setType("41");
        questionList.add(qqq);


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

//      创建文件对象，以excel形式输出文件
        File file = new File("test.xlsx");
//      输出通过流的形式对外输出
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//      将内存中的workbook数据写入到流中
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
}
