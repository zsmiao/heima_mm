package xyz.itclay.heima_mm.utils.excel.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 简介：Excel导出项配置（这里针对的是实体的配置）<BR/>
 *
 * 描述：在导出的实体中进行表格列的配置，最后只需一行就解决导出问题<BR/>
 *
 * @author  xinhua
 *
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2017年3月22日上午11:07:37
 */
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target({ ElementType.FIELD })//注解用于字段
public @interface ExportConfig {

	
    /**
     * 
     * 功能描述：表头显示名，如果不传则此处默认为field,在ExcelKit中则会处理为对应的字段名
     *
     *
     *
     * @return [java.lang.String] <默认为字段名>
     * 
     * @author xinhua
     *
     * @date 2017年3月22日上午10:58:22
     */
	String value() default "field";
	
	/**
	 * 
	 * 功能描述：单元格宽度
	 *
	 *
	 *
	 * @return [java.lang.Short]  <默认300>
	 * 
	 * @author xinhua
	 *
	 * @date 2017年3月22日上午11:01:08
	 */
	short width() default 300;
	
	/**
	 * 
	 * 功能描述：是否导出数据(如果不导出数据,Ĭ将以blankContent属性字段填充单元格)
	 *
	 *
	 *
	 * @return [java.lang.boolean] 返回值 <默认true>
	 * 
	 * @author xinhua
	 *
	 * @date 2017年3月22日上午11:02:10
	 */
	boolean isExport() default true;
	
	/**
	 * 
	 * 功能描述：主要是针对isExport为true是显示的文本
	 *
	 *
	 *
	 * @return [java.lang.String]  <默认******>
	 * 
	 * @author xinhua
	 *
	 * @date 2017年3月22日上午11:04:55
	 */
	String blankContent() default "******";
	
	/**
	 * 
	 * 功能描述：单元格显示的格式
	 *
	 *
     *
     * @return [java.lang.String]  <默认"">
	 * 
	 * @author xinhua
	 *
	 * @date 2017年3月22日下午4:40:02
	 */
	String format() default "";
	
	
}
