
import xyz.itclay.heima_mm.utils.excel.base.ExcelType;
import xyz.itclay.heima_mm.utils.excel.base.OnReadDataHandler;
import xyz.itclay.heima_mm.utils.excel.base.expection.ExcelException;
import xyz.itclay.heima_mm.utils.excel.expose.ExcelTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ExcelImportTest
{
    public static void main(String[] args) throws Exception
    {
//        testImpoer();
    	  testExport();
//    	  test();
    }

    public static void test() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
    	Constructor<FileOutputStream> constructor = FileOutputStream.class.getConstructor(File.class);
    	FileOutputStream newInstance = constructor.newInstance(new File("E:\\text.xlsx"));
    	System.out.println(newInstance.getFD());
    }
    public static void testExport() throws Exception{
    	Map<String, Object> map1=new HashMap<String,Object>();
    	map1.put("id", "1231");
    	map1.put("name", "zhangxinhua1");
    	Map<String, Object> map2=new HashMap<String,Object>();
    	map2.put("id", "1232");
    	map2.put("name", "zhangxinhua2");
    	Map<String, Object> map3=new HashMap<String,Object>();
    	map3.put("id", "1233");
    	map3.put("name", "zhangxinhua3");
    	Map<String, Object> map4=new HashMap<String,Object>();
    	map4.put("id", "1234");
    	map4.put("name", "zhangxinhua4");
    	Map<String, Object> map5=new HashMap<String,Object>();
    	map5.put("id", "125");
    	map5.put("name", "zhangxinhua5");
    	List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
    	data.add(map1);
    	data.add(map2);
    	data.add(map3);
    	data.add(map4);
    	data.add(map5);
    	/*伪数据结束*/
    	
    	List<LinkedHashMap<String, Object>> _fieldMappedToSql=new ArrayList<LinkedHashMap<String, Object>>();
    	File file=new File("E:\\text.xlsx");
    	
    	LinkedHashMap<String, Object> param1=new LinkedHashMap<String,Object>();
    	/**
    	 * param （map）可以设置的可以有
    	 * field 字段
    	 * display 显示名
    	 * width 长度
    	 * isExport 是否显示
    	 * content 替换的内容
    	 * format 显示格式
    	 * 这些都可以随意设置设置不设置 ，不设置就采用默认的样式导出
    	 */
    	param1.put("field", "id");
    	param1.put("display", "序号");
    	param1.put("isExport", false);
    	param1.put("content", "测试写出excel");
    	LinkedHashMap<String, Object> param2=new LinkedHashMap<String,Object>();
    	param2.put("display", "姓名");
    	_fieldMappedToSql.add(param1);
    	_fieldMappedToSql.add(param2);
    	OutputStream outputStream=new FileOutputStream(file);
    	ExcelTool.$Local(_fieldMappedToSql, ExcelType.EXCEL2007).toExcel(data, "test",outputStream);
    }
	public static void testImpoer() throws ExcelException {
		File file  = new File("E:\\text.xlsx");
        ExcelTool.$import(true,true).toData(file, new OnReadDataHandler()
        {
            @Override
            public void handler(List<Object> rowData)
            {
                System.out.println(rowData);
            }
        });
	}
    
    
    
}
