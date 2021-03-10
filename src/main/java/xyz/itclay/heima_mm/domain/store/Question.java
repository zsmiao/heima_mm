package xyz.itclay.heima_mm.domain.store;

import java.util.Date;
import java.io.Serializable;

/**
 * 试题表(StQuestion)实体类
 *
 * @author ZhangSsenmiao
 * @since 2021-03-07 09:28:54
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 120075480588524970L;

    private String id;
    /**
     * 方向id
     */
    private String catalogId;

    private String catalogName;
    /**
     * 冗余设计，为了提高数据提取
     */
    private String courseId;

    private String courseName;
    /**
     * 试题编号
     */
    private String number;

    private String subject;
    /**
     * 题目类型： 1. 单选  2. 多选 5. 简答
     */
    private String type;
    /**
     * 难度：
     * 1 简单
     * 2 一般
     * 3 困难
     */
    private String difficulty;
    /**
     * 答案解析
     */
    private String analysis;

    private String analysisVideo;

    private String remark;
    /**
     * 是否精选题目
     * 0 不是
     * 1 是
     */
    private String isClassic;
    /**
     * 题目状态
     * 0 待发布（待审核、已拒绝）
     * 1 已发布（已审核）
     * 2 已下架（已审核）
     */
    private String state;
    /**
     * 0 待审核
     * 1 已审核
     * 2 已拒绝
     */
    private String reviewStatus;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
    /**
     * 企业id
     */
    private String companyId;

    private String companyName;

    private String picture;

    private Company company;
    private Catalog catalog;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysisVideo() {
        return analysisVideo;
    }

    public void setAnalysisVideo(String analysisVideo) {
        this.analysisVideo = analysisVideo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsClassic() {
        return isClassic;
    }

    public void setIsClassic(String isClassic) {
        this.isClassic = isClassic;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
