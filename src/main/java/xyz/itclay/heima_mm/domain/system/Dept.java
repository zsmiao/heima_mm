package xyz.itclay.heima_mm.domain.system;

import java.io.Serializable;

/**
 * (SsDept)实体类
 *
 * @author ZhangSsenmiao
 * @since 2021-03-06 11:25:23
 */
public class Dept implements Serializable {
    private static final long serialVersionUID = -93908532668070192L;

    private String deptId;

    private String deptName;

    private String parentId;
    /**
     * 1代表启用，0代表停用，默认为1
     */
    private Integer state;
    /**
     * 建立当前部门与父级部门的关联
     */
    private Dept parent;

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
