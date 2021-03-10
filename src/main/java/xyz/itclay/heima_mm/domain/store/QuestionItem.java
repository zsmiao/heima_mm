package xyz.itclay.heima_mm.domain.store;

import java.io.Serializable;

/**
 * 试题选项(StQuestionItem)实体类
 *
 * @author ZhangSsenmiao
 * @since 2021-03-07 19:13:29
 */
public class QuestionItem implements Serializable {
    private static final long serialVersionUID = 109228992523339910L;

    private String id;
    /**
     * 隶属题目
     */
    private String questionId;

    private String content;

    private String imgUrl;
    /**
     * 是否正确答案
     * 0 不是
     * 1 是
     */
    private String isRight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

}
