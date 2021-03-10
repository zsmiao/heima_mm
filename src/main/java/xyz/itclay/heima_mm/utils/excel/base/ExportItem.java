package xyz.itclay.heima_mm.utils.excel.base;

/**
 * 
 * 简介：将ExcelConfig中的注解信息接收过来<BR/>
 *
 * 描述：主要是获取注解的信息<BR/>
 *
 * @author  xinhua
 *
 *
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2017年3月22日下午1:20:01
 */
public class ExportItem
{
    /*属性名*/
    private String field;
    /*属性的类型*/
    private String fieldTypeName;
    /*显示名*/
    private String display;
    /*显示宽度*/
    private Short width;
    /*是否导出标识*/
    private Boolean isExport;
    /*不导出是默认显示内容*/
    private String content;
    /*单元格格式*/
    private String format;
    
    public ExportItem() {
        super();
    }

    public ExportItem($Build b) {
        this.field = b.field;
        this.fieldTypeName=b.fieldTypeName;
        this.display = b.display;
        this.width = b.width;
        this.isExport = b.isExport;
        this.content=b.content;
        this.format=b.format;
    }
    
    public String getFormat()
    {
        return format;
    }
    public void setFormat(String format)
    {
        this.format = format;
    }
    public String getField()
    {
        return field;
    }
    public void setField(String field)
    {
        this.field = field;
    }
    public String getDisplay()
    {
        return display;
    }
    public void setDisplay(String display)
    {
        this.display = display;
    }
    public Short getWidth()
    {
        return width;
    }
    public void setWidth(Short width)
    {
        this.width = width;
    }
    public Boolean getIsExport()
    {
        return isExport;
    }
    public void setIsExport(Boolean isExport)
    {
        this.isExport = isExport;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    public String getFieldTypeName()
    {
        return fieldTypeName;
    }
    public void setFieldTypeName(String fieldTypeName)
    {
        this.fieldTypeName = fieldTypeName;
    }
    
    public static class $Build
    {
        /*属性名*/
        private String field="field";
        /*属性类别名*/
        private String fieldTypeName="Object";
        /*显示名*/
        private String display="field";
        /*显示宽度*/
        private Short width=300;
        /*是否导出标识*/
        private Boolean isExport=true;
        /*不导出是默认显示内容*/
        private String content="******";
        /*单元格格式样式*/
        private String format="";
        
        public String getFormat()
        {
            return format;
        }
        public $Build setFormat(String format)
        {
            if(!"".equals(format)){
               this.format = format;
            }
            return this;
        }
        public String getField()
        {
            return field;
        }
        public $Build setField(String field)
        {
            if(!"".equals(field)){
                this.field = field;
             }
            return this;
        }
        public String getDisplay()
        {
            return display;
        }
        public $Build setDisplay(String display)
        {
            if(!"".equals(display)){
                this.display = display;
            }
            return this;
        }
        public Short getWidth()
        {
            return width;
        }
        public $Build setWidth(Short width)
        {
            if(width>0){
                this.width = width;
            }
            return this;
        }
        public Boolean getIsExport()
        {
            return isExport;
        }
        public $Build setIsExport(Boolean isExport)
        {
            this.isExport = isExport;
            return this;
        }
        public String getContent()
        {
            return content;
        }
        public $Build setContent(String content)
        {
            if(!"".equals(content)){
                this.content = content;
            }
            return this;
        }
        
        public ExportItem create()
        {
            return new ExportItem(this);
        }
        public String getFieldTypeName()
        {
            return fieldTypeName;
        }
        public $Build setFieldTypeName(String fieldTypeName)
        {
            if(!"".equals(fieldTypeName)){
                this.fieldTypeName = fieldTypeName;
            }
            return this;
        }
    }
}
