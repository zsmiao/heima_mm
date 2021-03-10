package xyz.itclay.heima_mm.utils.excel.expose;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.itclay.heima_mm.utils.excel.base.*;
import xyz.itclay.heima_mm.utils.excel.base.expection.ExcelException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 简介：使用者可以直接使用的类，来对表格进行导入导出操作<BR/>
 * <p>
 * 描述：导入导出<BR/>
 *
 * @author xinhua
 * @version V1.00
 * @date 2017年3月22日上午11:12:42
 * @see [Excel导入导出类]
 * @since JDK1.7
 */
public class ExcelTool extends BaseExcelUtil {
    /* 用来记录日志 */
    private static Logger log = Logger.getLogger(ExcelTool.class);
    //通过map承载数据的map配置信息
    private List<LinkedHashMap<String, Object>> _fieldMappedToSql;
    //通过实体承载数据的实体类型  通过实体配置的信息会采用注解的方式让用户在实体类中填写
    private Class<?> _class;
    //通过浏览器将Excel传递初期的响应头
    private HttpServletResponse _response;
    //默认采用最新的Excel编辑系统
    private ExcelType _excelType = ExcelType.EXCEL2007;
    // true开启验证表格是否为空功能
    private boolean _isValidEmpty;
    // true开启验证表格指定列重复功能。为false是就算传入列也不会执行验证
    private boolean _isValidSame;
    /**
     * 默认以此值填充空单元格,可通过 setEmptyCellValue(string)改变其默认值。
     */
    @SuppressWarnings("unused")
    private String _emptyCellValue = "EMPTY_CELL_VALUE";

    /**
     * <默认构造函数>
     */
    protected ExcelTool() {
    }

    /**
     * _fieldMappedToSql 集合中单个的map格式可以如下设置
     * param （map）可以设置的可以有
     * field 字段
     * display 显示名
     * width 长度
     * isExport 是否显示
     * content 替换的内容
     * format 显示格式
     * 这些都可以随意设置设置不设置 ，不设置就采用默认的样式导出
     *
     * @param _fieldMappedToSql 通过map为载体的map与表格的对应关系的map
     * @param _excelType        导出的表格形式
     */
    protected ExcelTool(List<LinkedHashMap<String, Object>> _fieldMappedToSql, ExcelType _excelType) {
        this(_fieldMappedToSql, null, _excelType);
    }

    /**
     * @param _fieldMappedToSql
     * @param _response
     * @param _excelType        和上面的构造函数一样 只不过多了response，目的是通过浏览器导出Excel
     *                          正常web项目中使用
     */
    protected ExcelTool(List<LinkedHashMap<String, Object>> _fieldMappedToSql,
                        HttpServletResponse _response, ExcelType _excelType) {
        this._response = _response;
        this._fieldMappedToSql = _fieldMappedToSql;
        if (_excelType != null) {
            this._excelType = _excelType;
        }
    }

    /**
     * @param _class     实体类型
     * @param _excelType 导出的Excel类型
     */
    protected ExcelTool(Class<?> _class, ExcelType _excelType) {
        this(_class, null, _excelType);
    }

    /**
     * <浏览器构造>
     */
    protected ExcelTool(Class<?> _class, HttpServletResponse _response, ExcelType _excelType) {
        this._response = _response;
        this._class = _class;
        if (_excelType != null) {
            this._excelType = _excelType;
        }
    }

    /**
     * <浏览器构造>
     */
    protected ExcelTool(boolean isValidEmpty, boolean isValidSame, ExcelType _excelType) {
        this._isValidEmpty = isValidEmpty;
        this._isValidSame = isValidSame;
        if (_excelType != null) {
            this._excelType = _excelType;
        }
    }

    /**
     * 功能描述：用于构造ExcelTool类，获取Excel数据进行数据操作
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:30:50
     */
    public static ExcelTool $import() {
        return new ExcelTool();
    }

    /**
     * 功能描述：用于构造ExcelTool类，获取Excel数据进行数据操作
     *
     */
    public static ExcelTool $import(boolean isValidEmpty, boolean isValidSame) {
        return new ExcelTool(isValidEmpty, isValidSame, null);
    }

    public static ExcelTool $import(boolean isValidEmpty, boolean isValidSame, ExcelType _excelType) {
        return new ExcelTool(isValidEmpty, isValidSame, _excelType);
    }

    /**
     * 功能描述：用于构造本地ExcelTool类，生成本地Excel文件
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:24:08
     */
    public static ExcelTool $Local(Class<?> _class) {
        return new ExcelTool(_class, null);
    }

    public static ExcelTool $Local(Class<?> _class, ExcelType _excelType) {
        return new ExcelTool(_class, null, _excelType);
    }

    /**
     * 功能描述：用于构造本地ExcelTool类，生成本地Excel文件
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:24:08
     */
    public static ExcelTool $Local(List<LinkedHashMap<String, Object>> _fieldMappedToSql) {
        return new ExcelTool(_fieldMappedToSql, null);
    }

    public static ExcelTool $Local(List<LinkedHashMap<String, Object>> _fieldMappedToSql, ExcelType _excelType) {
        return new ExcelTool(_fieldMappedToSql, _excelType);
    }

    /**
     * 功能描述：用于构造浏览器ExcelTool类，通过浏览器处理Excel文件
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:27:49
     */
    public static ExcelTool $Brower(Class<?> _class,
                                    HttpServletResponse _response) {
        return new ExcelTool(_class, _response, null);
    }

    public static ExcelTool $Brower(Class<?> _class,
                                    HttpServletResponse _response, ExcelType _excelType) {
        return new ExcelTool(_class, _response, _excelType);
    }

    /**
     * 功能描述：用于构造浏览器ExcelTool类，通过浏览器处理Excel文件
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:27:49
     */
    public static ExcelTool $Brower(List<LinkedHashMap<String, Object>> _fieldMappedToSql,
                                    HttpServletResponse _response) {
        return new ExcelTool(_fieldMappedToSql, _response, null);
    }

    public static ExcelTool $Brower(List<LinkedHashMap<String, Object>> _fieldMappedToSql,
                                    HttpServletResponse _response, ExcelType _excelType) {
        return new ExcelTool(_fieldMappedToSql, _response, _excelType);
    }

    /**
     * 功能描述：通过浏览器导出Excel，在调用此方法必须调用$Brower对ExcelTool进行构造
     *
     * @return [java.lang.boolean] <导出状态>
     * @author xinhua
     * @date 2017年3月22日上午11:34:37
     */
    public boolean toExcel(List<?> data, String fileName) {
        if (this._response == null) {
            throw new RuntimeException(
                    "请先初始化  HttpServletResponse 对象! (通过调用 $Brower(Class<?> _class,HttpServletResponse _response))");
        }
        try {
            return toExcel(data, fileName, _response.getOutputStream());
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.info("出错啦 168L");
            }
        }
        return false;
    }


    public boolean toExcel(List<?> data, String fileName, OutputStream out) throws Exception {
        return toExcel(data, fileName, this._excelType,
                new OnSettingHanlder() {

                    @Override
                    public CellStyle getHeadCellStyle(Workbook wb) {
                        CellStyle cellStyle = wb.createCellStyle();
                        Font font = wb.createFont();
                        cellStyle.setFillForegroundColor((short) 12);
                        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);// 填充模式
                        cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框为细边框
                        cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框为细边框
                        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框为细边框
                        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框为细边框
                        cellStyle.setAlignment(CellStyle.ALIGN_LEFT);// 对齐
                        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
                        cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
                        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                        font.setFontHeightInPoints((short) 14);// 字体大小
                        font.setColor(HSSFColor.WHITE.index);
                        // 应用标题字体到标题样式
                        cellStyle.setFont(font);
                        return cellStyle;
                    }

                    @Override
                    public CellStyle getBodyCellStyle(Workbook wb) {
                        return null;
                    }

                    @Override
                    public String getExportFileName(String sheetName) {
                        return String.format("导出-%s-%s", sheetName,
                                System.currentTimeMillis());
                    }
                }, out);
    }

    public boolean toExcel(List<?> data, String fileName, ExcelType type,
                           OnSettingHanlder handler, OutputStream out) throws Exception {
        // 记录用时
        long begin = System.currentTimeMillis();
        if (data == null || data.size() == 0) {
            if (log.isDebugEnabled()) {
                log.debug("没有检测到导出数据，将生成 Excel导入模版。");
            }
        }
        List<ExportItem> items = new ArrayList<ExportItem>();

        // 开始检测是通过javabean还是通过map方式导出数据
        if (this._class != null) {
            items = getConfigFromBean(data);
        } else if (this._fieldMappedToSql != null) {
            items = getConfigFromMap(data);
        }

        // 开始创建workbook，创建Excel
        Workbook wb = this.createWorkbook(type);
        // 首先创建Excel的工作页签
        Sheet sheet = wb.createSheet("zxh_" + fileName);
        // 通过标签页创建表头
        Row headRow = sheet.createRow(0);
        // 通过ExportItem信息显示表头信息
        for (int index = 0; index < items.size(); index++) {
            ExportItem exportItem = items.get(index);
            // 首先创建该行指定列的单元格
            Cell cell = headRow.createCell(index);
            sheet.setColumnWidth(index, (short) (exportItem.getWidth() * 35.7));
            cell.setCellValue(exportItem.getDisplay());
            CellStyle style = handler.getHeadCellStyle(wb);
            if (style != null) {
                cell.setCellStyle(style);
            }
        }
        // 遍历表头以外信息
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                // 这里从第一行开始 所以+1
                Row body = sheet.createRow(i + 1);
                // 信息属性顺序按照ExportItem加入顺序显示 保证同步
                for (int j = 0; j < items.size(); j++) {
                    ExportItem exportItem = items.get(j);
                    Cell cell = body.createCell(j);
                    if (exportItem.getIsExport()) {
                        try {
                            if (this._class != null) {
                                cell.setCellValue(BeanUtils.getProperty(data.get(i), exportItem.getField()));
                            } else if (this._fieldMappedToSql != null) {
                                @SuppressWarnings("unchecked")
                                Map<String, Object> param = (Map<String, Object>) data.get(i);
                                cell.setCellValue(param.get(exportItem.getField()).toString());
                            }
                            setStyleByType(wb, exportItem.getFormat());
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (log.isDebugEnabled()) {
                                log.error("在处理bean属性取值是出错了");
                            }
                        }
                    } else {
                        cell.setCellValue(exportItem.getContent());
                    }
                }
            }
        }
        try {
            if (_response != null) {
                // 通过浏览器下载Excel
                // 浏览器响应头
                fileName += getExcelSuffix(type);
                _response.setContentType(getContentType(type));
                _response.setHeader(
                        "Content-disposition",
                        "attachment; filename="
                                + new String(fileName.getBytes("gbk"),
                                "iso8859-1"));

            }
            if (out == null) {
                out = _response.getOutputStream();
            }
            wb.write(out);
            out.flush();
            out.close();
            log.info("Excel处理完成,共生成数据:" + (data != null ? data.size() : 0)
                    + "行 (不包含表头),耗时："
                    + ((System.currentTimeMillis() - begin) / 1000F)
                    + " seconds.");
        } catch (Exception e) {
            e.printStackTrace();
            if (log.isDebugEnabled()) {
                log.error("出现未知错误");
            }
        }
        return true;
    }

    /**
     * 功能描述：获取Excel表格数据,这种方法主需要传入处理数据的 实现类就可以了。相当于将数据暴露给你 。 你自己处理处理的接收流程
     * 默认读取第一页的内容。读其他页有其他方法
     */
    public void toData(File excelFile, OnReadDataHandler handler,
                       String... uniqueFields) throws ExcelException {
        toData(excelFile, handler, 0, uniqueFields);
    }

    /**
     * 功能描述：获取Excel表格数据,这种方法主需要传入处理数据的 实现类就可以了。相当于将数据暴露给你 。 你自己处理处理的接收流程
     */
    public void toData(File excelFile, OnReadDataHandler handler,
                       int sheetIndex, String... uniqueFields) throws ExcelException {
        toData(excelFile, handler, sheetIndex, 1, -1, 0, -1, uniqueFields);
    }

    /**
     * 功能描述：功能描述：获取Excel表格数据,这种方法主需要传入处理数据的 实现类就可以了。相当于将数据暴露给你 。 你自己处理处理的接收流程
     *
     * @throws ExcelException
     * @date 2017年4月12日下午5:52:55
     */
    public void toData(File excelFile, OnReadDataHandler handler,
                       int sheetIndex, int startRowIndex, int endRowIndex,
                       int startColIndex, int endColIndex, String... uniqueFields)
            throws ExcelException {
        // 记录最终获取的数据的行数 表格最多65535行/sheet 。所以这里用long类型
        @SuppressWarnings("unused")
        long dataSize = 0L;
        // 根据文件地址创建Excel对象
        Workbook workbook = createWorkbook(excelFile);
        // 根据Excel对象获取表格标签页
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        // 根据标签页获取行 如果结束行设置为-1 则表示选中的是全部行
        endRowIndex = (int) (endRowIndex == -1 ? sheet
                .getPhysicalNumberOfRows() : endRowIndex);
        if (endRowIndex > 0) {
            // 列也是一样
            endColIndex = (int) (endColIndex == -1 ? sheet.getRow(0)
                    .getPhysicalNumberOfCells() : endRowIndex);
        }
        /**
         * 获取数据前先判定表格数据的合法性 合法性：数据指定列是否出现重复+是否是空数据 前提是构造的时候开启了相应的功能
         */
        if (this._isValidEmpty) {
            boolean isEmpty = validIsEmptySheet(sheet, startRowIndex,
                    endRowIndex, startColIndex, endColIndex);
            if (isEmpty) {
                throw new ExcelException("您上传的是一个空的表格！请检查");
            }
        }
        if (this._isValidSame) {
            // 传入需要验证的列名。和Excel中对应一样
            if (validIsContainSameRowByTargetCols(sheet, uniqueFields)) {
                // 表格检查结束
            }
        }
        // 开始拿数据
        for (int i = startRowIndex; i < endRowIndex; i++) {
            // 获取行
            Row row = sheet.getRow(i);
            List<Object> rowData = new ArrayList<Object>();
            for (int j = startColIndex; j < endColIndex; j++) {
                Cell cell = row.getCell(j);
                if (cell != null && !"".equals(cell.toString())) {
                    rowData.add(cell.toString());
                }
            }
            if (rowData.size() > 0) {
                //将改行的数据传递到外面，让用户自己决定如何处理数据
                handler.handler(rowData);
            }
        }
    }

    /**
     * @param data 需要导出的数据的集合
     * @return 获取数据中列在表格中的属性(宽度 ， 显示名 ， 格式...等等属性)的对象
     * @throws Exception
     */
    public List<ExportItem> getConfigFromMap(List<?> data) throws Exception {
        List<ExportItem> items = new ArrayList<ExportItem>();
        // 获取map配置信息  检测map数据中是否存在没有指定字段的map
        boolean fieldFlag = true;
        for (LinkedHashMap<String, Object> config : _fieldMappedToSql) {
            if (config.get("field") == null) {
                fieldFlag = false;
                break;
            }
        }
        if (_fieldMappedToSql.size() == 0) {
            fieldFlag = false;
        }
        if (fieldFlag) {
            //用户设定了数据库中字段和Excel中的列的对应关系。采用用户设置的显示
            items = getConfigFromMapFilledField(data);
        } else {
            //用户设定了数据库中字段和Excel中的列的对应关系。采用默认设置的显示
            items = getConfigFromMapNotFillField(data);
        }
        return items;
    }

    public List<ExportItem> getConfigFromMapNotFillField(List<?> data)
            throws Exception {
        //没有指定字段映射关系的默认按顺序映射
        List<ExportItem> items = new ArrayList<ExportItem>();
        //获取第一行用户获取属性值
        @SuppressWarnings("unchecked")
        Map<String, Object> object = (Map<String, Object>) data.get(0);
        if (object.size() > _fieldMappedToSql.size()) {
            System.err.println("您的数据映射不完整，后续的将采用默认映射实现");
        }
        for (int i = 0; i < object.size(); i++) {
            String dataField = getKeyFromMapIndex(object, i).toString();
            Map<String, Object> config = new HashMap<String, Object>();
            //按顺序显示，后面用户没有设置则用我们默认的风格显示
            if (i < _fieldMappedToSql.size()) {
                config = _fieldMappedToSql.get(i);
            }
            items.add(new ExportItem.$Build().setField(dataField)
                    //如果config集合设置了表头的显示名则显示否则显示field1（序号）
                    .setDisplay(config.get("display") == null ? dataField + i + 1 : config.get("display").toString())
                    .setIsExport(config.get("isExport") == null ? true : (boolean) config.get("isExport"))
                    .setContent(config.get("content") == null ? "" : config.get("content").toString())
                    .setWidth(config.get("width") == null ? 0 : (short) config.get("width"))
                    .setFieldTypeName(config.get("fieldTypeName") == null ? "" : config.get("fieldTypeName").toString())
                    .setFormat(config.get("format") == null ? "" : config.get("format").toString())
                    .create());

        }
        return items;
    }

    public List<ExportItem> getConfigFromMapFilledField(List<?> data) throws Exception {
        //指定字段映射关系的按映射关系操作
        List<ExportItem> items = new ArrayList<ExportItem>();
        @SuppressWarnings("unchecked")
        Map<String, Object> object = (Map<String, Object>) data.get(0);
        for (int i = 0; i < _fieldMappedToSql.size(); i++) {
            Map<String, Object> config = _fieldMappedToSql.get(i);

            String dataField = getKeyFromMapIndex(object, i).toString();
            items.add(new ExportItem.$Build().setField(config.get("field").toString())
                    //如果config集合设置了表头的显示名则显示否则显示field1（序号）
                    .setDisplay(config.get("display") == null ? dataField + i : config.get("display").toString())
                    .setIsExport(config.get("isExport") == null ? true : (boolean) config.get("isExport"))
                    .setContent(config.get("content") == null ? "" : config.get("content").toString())
                    .setWidth(config.get("width") == null ? 0 : (short) config.get("width"))
                    .setFieldTypeName(config.get("fieldTypeName") == null ? "" : config.get("fieldTypeName").toString())
                    .setFormat(config.get("format") == null ? "" : config.get("format").toString())
                    .create());
        }
        return items;
    }

    public List<ExportItem> getConfigFromBean(List<?> data) {
        List<ExportItem> items = new ArrayList<ExportItem>();
        // 通过反射获取javabean中配置表头信息
        for (Field field : _class.getDeclaredFields()) {
            String property = "";
            try {
                property = BeanUtils.getProperty(data.get(0), field.getName());
            } catch (IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (property != null) {
                // 获取bean中属性上的注解信息
                ExportConfig config = field.getAnnotation(ExportConfig.class);
                if (config != null) {
                    items.add(new ExportItem.$Build()
                            .setField(field.getName())
                            .setDisplay(
                                    "field".equals(config.value()) ? field
                                            .getName() : config.value())
                            .setWidth(config.width())
                            .setIsExport(config.isExport())
                            .setContent(config.blankContent())
                            .setFieldTypeName(field.getClass().getSimpleName())
                            .setFormat(config.format()).create());
                }
            }
        }
        return items;
    }

    public Object getKeyFromMapIndex(Map<?, ?> map, Integer keyIndex) throws Exception {
        Object result = null;
        int index = 0;
        if (keyIndex < 0) {
            throw new Exception("Map集合请求索引不能小于0 : " + keyIndex);
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (index == keyIndex) {
                return entry.getKey();
            }
            index++;
        }
        return result;
    }

    /**
     * 功能描述：设置导出中空单元格的显示风格
     *
     * @return [tom.zhangxinhua.expose.ExcelTool] <ExcelTool实体类>
     * @author xinhua
     * @date 2017年3月22日上午11:32:44
     */
    public ExcelTool setEmptyCellValue(String _emptyCellValue) {
        this._emptyCellValue = _emptyCellValue;
        // 返回对象为了可以进行链式操作
        return this;
    }

    /**
     * 功能描述：文件流的方式获取到表格对象
     *
     * @return [org.apache.poi.ss.usermodel.Workbook] <表格的Excel对象>
     * @author xinhua
     * @date 2017年3月22日下午1:40:13
     */
    public Workbook createWorkbook(File file) {
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));// 2003
        } catch (Exception e) {
            try {
                workbook = new XSSFWorkbook(new FileInputStream(file));// 2003以上
            } catch (Exception e1) {
                throw new RuntimeException("不能读取有效的Excel数据！");
            }
        }
        return workbook;
    }

    /**
     * 功能描述：创建不同版本的Excel空表格
     *
     * @return [org.apache.poi.ss.usermodel.Workbook] <表格的Excel对象>
     * @author xinhua
     * @date 2017年3月22日下午1:40:13
     */
    public Workbook createWorkbook(ExcelType type) {
        if (type == ExcelType.EXCEL2003) {
            return new HSSFWorkbook();
        }
        return new XSSFWorkbook();
    }

    /**
     * 功能描述：通过不同的版本Excel获取不同的后缀信息
     *
     * @return [java.lang.String] <Excel后缀>
     * @author xinhua
     * @date 2017年3月22日下午1:42:24
     */
    public String getExcelSuffix(ExcelType type) {
        if (type == ExcelType.EXCEL2003) {
            return ".xls";
        } else {
            return ".xlsx";
        }
    }

    /**
     * 功能描述：不同版本的Excel的文本格式不同
     *
     * @return [java.lang.String] <Excel在浏览器中的文本内容>
     * @author xinhua
     * @date 2017年3月22日下午2:08:30
     */
    public String getContentType(ExcelType type) {
        if (type == ExcelType.EXCEL2003) {
            return "application/vnd.ms-excel";
        } else {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
    }

    /**
     * 功能描述：返回具体格式的样式
     *
     * @return [org.apache.poi.ss.usermodel.CellStyle] <样式>
     * @author xinhua
     * @date 2017年3月22日下午4:35:49
     */
    public CellStyle setStyleByType(Workbook wb, String formatContent) {
        CellStyle cellStyle = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat(formatContent));
        return cellStyle;
    }

    // 初步防反编译
    @SuppressWarnings("unused")
    private class ExposeZxhTests {

    }
}
