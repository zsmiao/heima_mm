package xyz.itclay.heima_mm.utils.excel.base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import xyz.itclay.heima_mm.utils.excel.base.expection.ExcelException;

import java.util.*;

public class BaseExcelUtil
{
    
    private Map<String,Integer> cacheMap=null;
    public BaseExcelUtil()
    {

    }

    public boolean validIsEmptySheet(Sheet sheet,int startRowIndex,int endRowIndex,int startColIndex,int endColIndex)
    {
        boolean flag = false;
        // 记录真实数据的个数
        int realDataRowNum = 0;
        for (int i = startRowIndex; i < endRowIndex; i++)
        {
            // 获取行对象
            Row row = sheet.getRow(i);
            // 记录每行中有效列个数
            int realDataColNum = 0;
            for (int j = startColIndex; j < endColIndex; j++)
            {
                // 获取第i+1行第j+1列的单元格
                Cell cell = row.getCell(j);
                if (cell == null || "".equals(cell.toString()))
                {
                    continue;
                }
                realDataColNum++;
            }
            if (realDataColNum > 0)
            {
                realDataRowNum++;
            }
        }
        if (realDataRowNum <= 0)
        {
            flag=true;
        }
        return flag;
    }
    
    /**
     * 功能描述：判断是否在指定列中出现重复行
     *
     *
     * @param
     *
     * @return [返回类型] 返回值 <详细描述>
     * 
     * @author xinhua
     * 
     * @throws ExcelException 
     *
     * @date 2017年4月13日上午11:21:56
     */
    public boolean validIsContainSameRowByTargetCols(Sheet sheet,String ... colName) throws ExcelException{
        if(colName.length==0){
            return true;
        }
        getExcelHeadAsMap(sheet);
        int [] indexs=new int[getExcelHeadAsList(sheet).size()];
        for (int index=0;index<indexs.length;index++)
        {
            indexs[index]=-1;
        }
        for (int i=0;i<colName.length;i++)
        {
            if(cacheMap.get(colName[i])==null||"".equals(cacheMap.get(colName[i]).toString())){
                throw new ExcelException("你指定的"+colName[i]+"列不在表格中");
            }
            indexs[i]=cacheMap.get(colName[i]);
        }
        int nullData=0;
        for (int index=0;index<indexs.length;index++)
        {
            if(indexs[index]==-1){
                nullData++;
                for (int j = index; j < indexs.length-1; j++)
                {
                    indexs[j]=indexs[j+1];
                }
            }
        }
        indexs = Arrays.copyOf(indexs, indexs.length-nullData);
        return validIsContainSameRowByTargetCols(sheet, indexs);
    }
    
    public boolean validIsContainSameRowByTargetCols(Sheet sheet,int ... colIndex) throws ExcelException{
        if(colIndex.length==0){
            return true;
        }
        //默认是不出现的
        Map<Object, Object> result=new LinkedHashMap<Object, Object>();
        for (int i : colIndex)
        {
            if(i>=sheet.getRow(0).getPhysicalNumberOfCells()){
                throw new ExcelException("你指定的"+(i+1)+"列超出了边界");
            }
            List<Object> cols = getCol(sheet, i);
            List<Object> newCols=new ArrayList<Object>();
            for (Object object : cols)
            {
                if(newCols.contains(object)){
                    result.put(i, true);
                    throw new ExcelException("按给定顺序检测到\""+sheet.getRow(0).getCell(i)+"\"列数据出现重复");
                }
                newCols.add(object);
            }
        }
        return true;
    }
    
    public List<Object> getCol(Sheet sheet , int index){
        List<Object> list=new ArrayList<Object>();
        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++)
        {
            list.add(sheet.getRow(i).getCell(index).toString());
        }
        return list;
    }
    
    /**
     * 
     * 功能描述：获取指定标签页的表头
     *
     *
     *
     * @return TODO[返回类型] 返回值 <详细描述>
     * 
     * @author xinhua
     *
     * @date 2017年4月13日下午1:28:54
     */
    public List<String> getExcelHeadAsList(Sheet sheet){
        List<String> list=new ArrayList<String>();
        Row row = sheet.getRow(0);
        for (Cell cell : row)
        {
            list.add(cell.toString());
        }
        return list;
    }
    
    public Map<String,Integer> getExcelHeadAsMap(Sheet sheet){
        if(cacheMap==null){
            cacheMap=new LinkedHashMap<String, Integer>();
            Row row = sheet.getRow(0);
            for (int index=0;index<row.getPhysicalNumberOfCells();index++)
            {
                Cell cell = row.getCell(index);
                cacheMap.put(cell.toString(), index);
            }
        }
        return cacheMap;
    }
    
    // 初步防反编译
    @SuppressWarnings("unused")
    private class ExcelZxhTests {

    }
}
