package xyz.itclay.heima_mm.servlet.system;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.system.User;
import xyz.itclay.heima_mm.service.impl.system.UserServiceImpl;
import xyz.itclay.heima_mm.service.system.UserService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户的servlet
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 13:11
 **/
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    /**
     * 查询所有用户
     */
    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<User> list = userService.findByPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(req, resp);
    }

    /**
     * 添加用户信息
     */
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = BeanUtil.fillBean(req, User.class);
        userService.addUser(user);
        resp.sendRedirect("/user/getUser");
    }

    /**
     * 修改用户信息
     */
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = BeanUtil.fillBean(req, User.class);
        userService.updateUser(user);
        resp.sendRedirect("/user/getUser");
    }
}
