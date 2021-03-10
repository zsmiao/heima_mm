package xyz.itclay.heima_mm.utils.excel.base.expection;


/**
 * 用来处理导入导出中遇到的问题  数据源为空 有重复行,待导入数据已在数据库中存在等
 * @author 张新华 870775401@qq.com
 * @version 创建时间 ： 2016-8-8 下午04:53:18
 *
 */
public class ExcelException extends Exception {
	
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public ExcelException() {
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Throwable cause) {
        super(cause);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
