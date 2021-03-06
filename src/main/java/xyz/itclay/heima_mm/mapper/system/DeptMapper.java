package xyz.itclay.heima_mm.mapper.system;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.system.Dept;

import java.util.List;

/**
 * 部门管理持久层
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 11:33
 **/
public interface DeptMapper {
    /**
     * 查询所有部门的信息
     *
     * @return 所有部门信息
     */
    @Select("select * from ss_dept")
    @Results(id = "deptMap", value = {
            @Result(id = true, column = "dept_id", property = "deptId"),
            @Result(column = "dept_name", property = "deptName"),
            @Result(column = "parent_id", property = "parentId"),
            @Result(property = "parent", column = "parent_id", javaType = Dept.class,
                    one = @One(select = "xyz.itclay.heima_mm.mapper.system.DeptMapper.findByPid"))
    })
    List<Dept> findAll();

    /**
     * 根据id查询
     *
     * @param id id
     * @return 单条部门
     */
    @Select("select * from ss_dept where dept_id=#{id}")
    @ResultMap("deptMap")
    Dept findByPid(String id);

    /**
     * 添加部门信息
     *
     * @param dept 添加部门信息
     */
    @Insert("insert into db_heima_mm.ss_dept(dept_id,dept_name, parent_id, state)\n" +
            "        values (#{deptId},#{deptName}, #{parentId}, #{state})")
    void addDept(Dept dept);

    /**
     * 根据id修改部门信息
     *
     * @param dept 部门信息
     */
    @Update("<script> " +
            "update db_heima_mm.ss_dept\n" +
            "        <set>\n" +
            "            <if test=\"deptName != null and deptName != ''\">\n" +
            "                dept_name = #{deptName},\n" +
            "            </if>\n" +
            "            <if test=\"parentId != null and parentId != ''\">\n" +
            "                parent_id = #{parentId},\n" +
            "            </if>\n" +
            "            <if test=\"state != null\">\n" +
            "                state = #{state},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where dept_id = #{deptId}" +
            "</script>")
    void updateDept(Dept dept);
}
