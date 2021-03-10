package xyz.itclay.heima_mm.service.store;

import xyz.itclay.heima_mm.domain.store.Question;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/7 9:35
 **/
public interface QuestionService {

    List<Question> findByPage(String pageNum, String pageSize);

    Question findById(String id);

    void updateQuestion(Question question);

    String addQuestion(Question question, boolean flag);

    ByteArrayOutputStream getReport();
}
