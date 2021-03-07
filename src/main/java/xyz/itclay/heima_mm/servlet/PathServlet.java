package xyz.itclay.heima_mm.servlet;

import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.domain.store.Company;
import xyz.itclay.heima_mm.domain.store.Course;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.domain.system.User;
import xyz.itclay.heima_mm.service.impl.store.CatalogServiceImpl;
import xyz.itclay.heima_mm.service.impl.store.CompanyServiceImpl;
import xyz.itclay.heima_mm.service.impl.store.CourseServiceImpl;
import xyz.itclay.heima_mm.service.impl.system.DeptServiceImpl;
import xyz.itclay.heima_mm.service.impl.system.UserServiceImpl;
import xyz.itclay.heima_mm.service.store.CatalogService;
import xyz.itclay.heima_mm.service.store.CompanyService;
import xyz.itclay.heima_mm.service.store.CourseService;
import xyz.itclay.heima_mm.service.system.DeptService;
import xyz.itclay.heima_mm.service.system.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 路径跳转
 *
 * @author ZhangSenmiao
 * @date 2021/3/5 10:41
 **/
@WebServlet("/path/*")
public class PathServlet extends BaseServlet {
    private final CompanyService companyService = new CompanyServiceImpl();
    private final DeptService deptService = new DeptServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final CatalogService catalogService = new CatalogServiceImpl();

    /**
     * 登录按钮跳转到主界面
     */
    public void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(req, resp);
    }

    /**
     * 跳转企业添加页面
     */
    public void addCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req, resp);
    }

    /**
     * 跳转企业编辑页面数据回显
     */
    public void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Company company = companyService.findById(id);
        req.setAttribute("company", company);
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req, resp);
    }

    /**
     * 跳转添加部门页面并数据回显
     */
    public void addDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> deptList = deptService.findByAll();
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(req, resp);
    }

    /**
     * 跳转编辑部门数据回显
     */
    public void updateDept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Dept dept = deptService.findById(id);
//        h获取所有部门的信息，设置回显到修改页面
        List<Dept> deptList = deptService.findByAll();
        req.setAttribute("deptList", deptList);
        req.setAttribute("dept", dept);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(req, resp);
    }

    /**
     * 跳转添加用户界面部门数据的回显
     */
    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> deptList = deptService.findByAll();
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(req, resp);
    }

    /**
     * 跳转修改用户界面部门数据的回显
     */
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = userService.findById(id);
        List<Dept> deptList = deptService.findByAll();
        req.setAttribute("user", user);
        req.setAttribute("deptList", deptList);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(req, resp);
    }

    /**
     * 跳转到添加学科列表
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(req, resp);
    }

    /**
     * 跳转到修改学科界面
     */
    public void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Course course = courseService.findById(id);
        req.setAttribute("course", course);
        req.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(req, resp);


    }

    /**
     * 跳转到添加题目类型列表
     */
    public void addCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Course> courseList = courseService.findAll();
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(req, resp);
    }

    /**
     * 跳转到修改题目类型界面
     */
    public void updateCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Catalog catalog=catalogService.findById(id);
        req.setAttribute("catalog",catalog);
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(req, resp);
    }
}
