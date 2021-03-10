package xyz.itclay.heima_mm.servlet.store;

import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.QuestionItem;
import xyz.itclay.heima_mm.service.impl.store.QuestionItemServiceImpl;
import xyz.itclay.heima_mm.service.store.QuestionItemService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 题库选项servlet
 *
 * @author ZhangSenmiao
 * @date 2021/3/7 19:18
 **/
@WebServlet("/questionItem/*")
public class QuestionItemServlet extends BaseServlet {
    private final QuestionItemService qis = new QuestionItemServiceImpl();

    public void getQuestionItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId = req.getParameter("questionId");
        List<QuestionItem> questionItem = qis.findAll(questionId);
        req.setAttribute("questionId", questionId);
        req.setAttribute("page", questionItem);
        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(req, resp);
    }

    public void addOrUpdateQuestionItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class, "yyyy-MM-dd");
        //如果页面传递了当前数据的id，则为修改业务，否则为添加业务
        if (StringUtils.isNotBlank(questionItem.getId())) {
            qis.update(questionItem);
        } else {
            qis.save(questionItem);
        }
        //跳转回到页面list
        req.getRequestDispatcher("/questionItem/getQuestionItem").forward(req, resp);
    }

    public void toUpdateQuestionItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        QuestionItem questionItem = qis.findById(id);
        req.setAttribute("questionItem", questionItem);
        req.getRequestDispatcher("/questionItem/getQuestionItem").forward(req, resp);
    }

    public void deleteQuestionItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        qis.deleteQuestionItem(id);
        req.getRequestDispatcher("/questionItem/getQuestionItem").forward(req, resp);

    }
}
