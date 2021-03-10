package xyz.itclay.heima_mm.service.system;

import org.apache.ibatis.annotations.Update;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.domain.system.Role;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:30
 **/
public interface RoleService {
    /**
     * 查询所有角色
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 所有角色
     */
    List<Role> findByPage(String pageNum, String pageSize);

    /**
     * 更新权限
     *
     * @param roleId    角色id
     * @param moduleIds 模块id
     */
    void updateRoleModule(String roleId, String moduleIds);

    /**
     * 根绝角色id查询角色
     *
     * @param id 角色id
     * @return 角色
     */
    Role findById(String id);

    /**
     * 更新用户角色
     *
     * @param role 用户角色
     */
    void updateRole(Role role);

    /**
     * 添加用户角色
     *
     * @param role 用户信息
     */
    void addRole(Role role);

    /**
     * 根据id删除角色
     * @param id id
     */
    void deleteRole(String id);
}
