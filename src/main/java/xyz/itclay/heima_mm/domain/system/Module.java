package xyz.itclay.heima_mm.domain.system;

import java.io.Serializable;

/**
 * (SsModule)实体类
 *
 * @author ZhangSsenmiao
 * @since 2021-03-09 15:31:54
 */
public class Module implements Serializable {
    private static final long serialVersionUID = 487052634589712787L;

    private String moduleId;

    private String parentId;

    private String parentName;

    private String name;

    private Double isLeaf;

    private String ico;

    private String cpermission;

    private String curl;
    /**
     * 0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态
     */
    private Double ctype;
    /**
     * 1启用0停用
     */
    private Double state;
    /**
     * 按钮时，可以标识其归属，\n            查询某页面按钮时就用此属性查询
     */
    private String belong;

    private String remark;

    private Double orderNo;

    private Module module;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Double isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getCpermission() {
        return cpermission;
    }

    public void setCpermission(String cpermission) {
        this.cpermission = cpermission;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public Double getCtype() {
        return ctype;
    }

    public void setCtype(Double ctype) {
        this.ctype = ctype;
    }

    public Double getState() {
        return state;
    }

    public void setState(Double state) {
        this.state = state;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Double orderNo) {
        this.orderNo = orderNo;
    }

}
