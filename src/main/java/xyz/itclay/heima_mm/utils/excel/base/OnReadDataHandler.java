package xyz.itclay.heima_mm.utils.excel.base;


import java.util.List;

/**
 * Excel数据读取回调
 * @author wuwz
 */
public interface OnReadDataHandler {

	/**
	 * 处理当前行数据
	 * 具体实现类中在处理数据，可以将数据放入Javabean中。也可以将数据存放在map中。
	 * 这种将接口暴露给用户自定义实现
	 * @param rowData 当前行数据,以rowData.get(cellIndex)的方式获取,如果cell的值为ExceklKit._emptyCellValue,则表示该单元格为空。
	 */
	void handler(List<Object> rowData);
}
