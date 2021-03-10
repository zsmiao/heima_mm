package xyz.itclay.heima_mm.servlet.system;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.system.Module;
import xyz.itclay.heima_mm.service.impl.system.ModuleServiceImpl;
import xyz.itclay.heima_mm.service.system.ModuleService;
import xyz.itclay.heima_mm.servlet.BaseServlet;
import xyz.itclay.heima_mm.utils.JsonToMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:52
 **/
@WebServlet("/module/*")
public class ModuleServlet extends BaseServlet {
    private final ModuleService moduleService = new ModuleServiceImpl();

    /**
     * 获取所有模块
     */
    public void getModules(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Module> list = moduleService.findByPage(pageNum, pageSize);
        PageInfo<Module> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(req, resp);
    }

    /**
     * 授权模块
     */
    public void author(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("id");
        String name = req.getParameter("name");
        List<Map> maps=moduleService.findAuthorDataByRid(roleId);
//        String json = JsonToMap.map2json();
        String json = JsonToMap.list2json(maps);
        req.setAttribute("roleModuleJson",json);
        req.setAttribute("name",name);
        req.setAttribute("id",roleId);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(req, resp);
    }
}
