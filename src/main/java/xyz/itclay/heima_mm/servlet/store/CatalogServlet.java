package xyz.itclay.heima_mm.servlet.store;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.domain.store.Course;
import xyz.itclay.heima_mm.service.impl.store.CatalogServiceImpl;
import xyz.itclay.heima_mm.service.store.CatalogService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/6 19:36
 **/
@WebServlet("/catalog/*")
public class CatalogServlet extends BaseServlet {
    private final CatalogService catalogService = new CatalogServiceImpl();

    /**
     * 获取题目类型信息
     */
    public void getCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Catalog> list = catalogService.findByPage(pageNum, pageSize);
        PageInfo<Catalog> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(req, resp);
    }

    /**
     * 保存题目类型
     */
    public void addCatalog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class);
        catalogService.addCatalog(catalog);
        resp.sendRedirect("/catalog/getCatalog");
    }
}
