package xyz.itclay.heima_mm.servlet.system;

import com.github.pagehelper.PageInfo;
import xyz.itclay.heima_mm.domain.system.Role;
import xyz.itclay.heima_mm.service.impl.system.RoleServiceImpl;
import xyz.itclay.heima_mm.service.system.RoleService;
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
 * @date 2021/3/9 15:28
 **/
@WebServlet("/role/*")
public class RoleServlet extends BaseServlet {
    private final RoleService roleService = new RoleServiceImpl();

    /**
     * 获取所有角色
     */
    public void getRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        List<Role> list = roleService.findByPage(pageNum, pageSize);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        req.setAttribute("page", pageInfo);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req, resp);
    }


    public void updateRoleModule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("roleId");
        String moduleIds = req.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        req.getRequestDispatcher("/role/getRole").forward(req, resp);
    }

    public void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Role role = BeanUtil.fillBean(req, Role.class);
        role.setRoleId(id);
        roleService.updateRole(role);
        req.getRequestDispatcher("/role/getRole").forward(req, resp);
    }

    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role role = BeanUtil.fillBean(req, Role.class);
        roleService.addRole(role);
        req.getRequestDispatcher("/role/getRole").forward(req, resp);
    }


    public void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        roleService.deleteRole(id);
        req.getRequestDispatcher("/role/getRole").forward(req, resp);
    }
}
