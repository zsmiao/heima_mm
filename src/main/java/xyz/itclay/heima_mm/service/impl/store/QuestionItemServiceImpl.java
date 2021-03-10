package xyz.itclay.heima_mm.service.impl.store;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.Question;
import xyz.itclay.heima_mm.domain.store.QuestionItem;
import xyz.itclay.heima_mm.mapper.store.QuestionItemMapper;
import xyz.itclay.heima_mm.mapper.store.QuestionMapper;
import xyz.itclay.heima_mm.service.store.QuestionItemService;
import xyz.itclay.heima_mm.service.store.QuestionService;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.util.List;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/7 9:39
 **/
public class QuestionItemServiceImpl implements QuestionItemService {

    @Override
    public List<QuestionItem> findAll(String questionId) {
        QuestionItemMapper mapper = MyBatisUtils.getMapper(QuestionItemMapper.class);
        return mapper.findAll(questionId);
    }

    @Override
    public void update(QuestionItem questionItem) {
        QuestionItemMapper mapper = MyBatisUtils.getMapper(QuestionItemMapper.class);
        mapper.update(questionItem);
    }

    @Override
    public void save(QuestionItem questionItem) {
        QuestionItemMapper mapper = MyBatisUtils.getMapper(QuestionItemMapper.class);
        questionItem.setId(GuidUtil.getUuid());
        mapper.save(questionItem);
    }

    @Override
    public QuestionItem findById(String id) {
        QuestionItemMapper mapper = MyBatisUtils.getMapper(QuestionItemMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void deleteQuestionItem(String id) {
        QuestionItemMapper mapper = MyBatisUtils.getMapper(QuestionItemMapper.class);
        mapper.deleteQuestionItem(id);
    }
}
