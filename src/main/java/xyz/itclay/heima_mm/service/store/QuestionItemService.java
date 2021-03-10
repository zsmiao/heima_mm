package xyz.itclay.heima_mm.service.store;

import xyz.itclay.heima_mm.domain.store.Question;
import xyz.itclay.heima_mm.domain.store.QuestionItem;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/7 9:35
 **/
public interface QuestionItemService {
    /**
     * 根据题目id查询选项
     *
     * @param questionId 题目id
     * @return 所有选项
     */
    List<QuestionItem> findAll(String questionId);

    void update(QuestionItem questionItem);

    void save(QuestionItem questionItem);

    QuestionItem findById(String id);

    void deleteQuestionItem(String id);
}
