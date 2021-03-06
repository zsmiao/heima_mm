package xyz.itclay.heima_mm.servlet.store;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.store.Company;
import xyz.itclay.heima_mm.service.impl.store.CompanyServiceImpl;
import xyz.itclay.heima_mm.service.store.CompanyService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;
import xyz.itclay.heima_mm.utils.DateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * 企业管理
 *
 * @author ZhangSenmiao
 * @date 2021/3/5 13:14
 **/
@WebServlet("/company/*")
public class CompanyServlet extends BaseServlet {

    private final CompanyService companyService = new CompanyServiceImpl();

    /**
     * 获取企业信息
     */
    public void getCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Company> list = companyService.findByPage(pageNum, pageSize);
        PageInfo<Company> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req, resp);
    }

    /**
     * 添加企业信息
     */
    public void addCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.addCompany(company);
        resp.sendRedirect("/company/getCompany");
    }

    /**
     * 修改企业信息
     */
    public void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        companyService.updateCompany(company);
        resp.sendRedirect("/company/getCompany");
    }

    /**
     * 根据id删除企业信息
     */
    public void deleteCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        companyService.deleteCompany(id);
        resp.sendRedirect("/company/getCompany");
    }
}
