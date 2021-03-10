package xyz.itclay.heima_mm.servlet.store;

import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.domain.store.Question;
import xyz.itclay.heima_mm.service.impl.store.QuestionServiceImpl;
import xyz.itclay.heima_mm.service.store.QuestionService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/7 9:36
 **/
@WebServlet("/question/*")
public class QuestionServlet extends BaseServlet {
    private final QuestionService questionService = new QuestionServiceImpl();

    public void getQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Question> list = questionService.findByPage(pageNum, pageSize);
        PageInfo<Question> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req, resp);
    }

    public void addQuestion(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(req)) {
            //2.创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            //4.从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            //创建一个标记位,标记当前时候有上传文件的操作
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }

            // --处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);


            String picture = questionService.addQuestion(question, flag);

            // --处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5.当前表单是否是文件表单
                if (!item.isFormField()) {
                    //6.从临时存储文件的地方将内容写入到指定位置
                    item.write(new File(this.getServletContext().getRealPath("upload"), picture));
                }
            }
        }
        resp.sendRedirect("/question/getQuestion");
    }

    public void updateQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question question = BeanUtil.fillBean(req, Question.class);
        questionService.updateQuestion(question);
        resp.sendRedirect("/question/getQuestion");
    }

    /**
     * 下载
     */
    public void downloadReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = new String("question.xlsx".getBytes(),"iso8859-1");
        resp.addHeader("Content-Disposition","attachment;fileName="+fileName);
//       生成报告的文件，传递到前端的页面
        ByteArrayOutputStream report = questionService.getReport();
//        产生响应流对象
        ServletOutputStream outputStream = resp.getOutputStream();
//        将数据从原始的字节流对象提取出来写入到servlet中
        report.writeTo(outputStream);
        outputStream.flush();
        report.close();
    }
}
