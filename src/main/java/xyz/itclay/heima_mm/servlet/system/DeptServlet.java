package xyz.itclay.heima_mm.servlet.system;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.store.Company;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.service.impl.system.DeptServiceImpl;
import xyz.itclay.heima_mm.service.system.DeptService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 部门管理
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 11:30
 **/
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {

    private final DeptService deptService = new DeptServiceImpl();

    /**
     * 获取所有企业
     */
    public void getDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Dept> list = deptService.findByPage(pageNum, pageSize);
        PageInfo<Dept> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req, resp);
    }

    /**
     * 添加部门信息
     */
    public void addDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dept dept = BeanUtil.fillBean(req, Dept.class);
        deptService.addDept(dept);
        resp.sendRedirect("/dept/getDept");
    }

    /**
     * 修改企业信息
     */
    public void updateDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dept dept = BeanUtil.fillBean(req, Dept.class);
        deptService.updateDept(dept);
        resp.sendRedirect("/dept/getDept");
    }
}
