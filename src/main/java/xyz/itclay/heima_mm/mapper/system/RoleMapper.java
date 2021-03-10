package xyz.itclay.heima_mm.mapper.system;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.domain.system.Role;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:26
 **/
public interface RoleMapper {
    /**
     * 查询所有角色信息
     *
     * @return 所有角色信息
     */
    @Select("select * from ss_role")
    List<Role> findAll();

    /**
     * 根据角色id删除所有的角色模块关系
     *
     * @param roleId 角色id
     */
    @Delete(" delete from ss_role_module where role_id = #{roleId}")
    void deleteRoleModule(String roleId);

    /**
     * 重写写入角色对应的模块信息
     *
     * @param roleId   角色id
     * @param moduleId 模块id
     */
    @Insert("insert into ss_role_module(role_id,module_id) values (#{roleId},#{moduleId})")
    void updateRoleModule(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

    /**
     * 根据id查询角色信息
     *
     * @param id 角色id
     * @return 角色
     */
    @Select("select * from ss_role where role_id=#{id}")
    Role findById(String id);

    /**
     * 更新角色信息
     *
     * @param role 角色信息
     */
    @Update("<script>" +
            "update db_heima_mm.ss_role\n" +
            "        <set>\n" +
            "            <if test=\"name != null and name != ''\">\n" +
            "                name = #{name},\n" +
            "            </if>\n" +
            "            <if test=\"remark != null and remark != ''\">\n" +
            "                remark = #{remark},\n" +
            "            </if>\n" +
            "            <if test=\"orderNo != null\">\n" +
            "                order_no = #{orderNo},\n" +
            "            </if>\n" +
            "            <if test=\"createBy != null and createBy != ''\">\n" +
            "                create_by = #{createBy},\n" +
            "            </if>\n" +
            "            <if test=\"createDept != null and createDept != ''\">\n" +
            "                create_dept = #{createDept},\n" +
            "            </if>\n" +
            "            <if test=\"createTime != null\">\n" +
            "                create_time = #{createTime},\n" +
            "            </if>\n" +
            "            <if test=\"updateBy != null and updateBy != ''\">\n" +
            "                update_by = #{updateBy},\n" +
            "            </if>\n" +
            "            <if test=\"updateTime != null\">\n" +
            "                update_time = #{updateTime},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where role_id = #{roleId}" +
            "</script>")
    void updateRole(Role role);

    /**
     * 添加角色信息
     *
     * @param role 角色信息
     */
    @Insert("insert into db_heima_mm.ss_role(role_id,name, remark, order_no, create_by, create_dept, create_time, update_by,\n" +
            "                                        update_time)\n" +
            "        values (#{roleId},#{name}, #{remark}, #{orderNo}, #{createBy}, #{createDept}, #{createTime}, #{updateBy}, #{updateTime})")
    void addRole(Role role);

    /**
     * 删除用户角色
     *
     * @param id id
     */
    @Delete(" delete\n" +
            "        from db_heima_mm.ss_role\n" +
            "        where role_id = #{id}")
    void deleteRole(String id);
}
