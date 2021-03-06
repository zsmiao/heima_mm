package xyz.itclay.heima_mm.mapper.system;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.system.User;

import java.util.List;

/**
 * 用户持久层
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 13:15
 **/
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return 所有用户
     */
    @Select("select * from ss_user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "manger_id", property = "managerId"),
            @Result(column = "join_date", property = "joinDate"),
            @Result(column = "order_no", property = "orderNo"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "dept_name", property = "deptName"),
            @Result(column = "create_by", property = "createBy"),
            @Result(column = "create_dept", property = "createDept"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_by", property = "updateBy"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<User> findAll();

    /**
     * 跟据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Select("select * from ss_user where user_id=#{id}")
    @ResultMap("userMap")
    User findById(String id);

    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    @Insert("insert into db_heima_mm.ss_user(user_id,email, user_name, station, password, state, manager_id, gender, telephone,\n" +
            "                                        birthday, degree, salary, join_date, order_no, remark, dept_id, dept_name,\n" +
            "                                        create_by, create_dept, create_time, update_by, update_time)\n" +
            "        values (#{userId},#{email}, #{userName}, #{station}, #{password}, #{state}, #{managerId}, #{gender}, #{telephone},\n" +
            "                #{birthday}, #{degree}, #{salary}, #{joinDate}, #{orderNo}, #{remark}, #{deptId}, #{deptName},\n" +
            "                #{createBy}, #{createDept}, #{createTime}, #{updateBy}, #{updateTime})")
    void addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @Update("<script>" +
            "update db_heima_mm.ss_user\n" +
            "        <set>\n" +
            "            <if test=\"email != null and email != ''\">\n" +
            "                email = #{email},\n" +
            "            </if>\n" +
            "            <if test=\"userName != null and userName != ''\">\n" +
            "                user_name = #{userName},\n" +
            "            </if>\n" +
            "            <if test=\"station != null and station != ''\">\n" +
            "                station = #{station},\n" +
            "            </if>\n" +
            "            <if test=\"password != null and password != ''\">\n" +
            "                password = #{password},\n" +
            "            </if>\n" +
            "            <if test=\"state != null\">\n" +
            "                state = #{state},\n" +
            "            </if>\n" +
            "            <if test=\"managerId != null and managerId != ''\">\n" +
            "                manager_id = #{managerId},\n" +
            "            </if>\n" +
            "            <if test=\"gender != null and gender != ''\">\n" +
            "                gender = #{gender},\n" +
            "            </if>\n" +
            "            <if test=\"telephone != null and telephone != ''\">\n" +
            "                telephone = #{telephone},\n" +
            "            </if>\n" +
            "            <if test=\"birthday != null and birthday != ''\">\n" +
            "                birthday = #{birthday},\n" +
            "            </if>\n" +
            "            <if test=\"degree != null\">\n" +
            "                degree = #{degree},\n" +
            "            </if>\n" +
            "            <if test=\"salary != null\">\n" +
            "                salary = #{salary},\n" +
            "            </if>\n" +
            "            <if test=\"joinDate != null and joinDate != ''\">\n" +
            "                join_date = #{joinDate},\n" +
            "            </if>\n" +
            "            <if test=\"orderNo != null\">\n" +
            "                order_no = #{orderNo},\n" +
            "            </if>\n" +
            "            <if test=\"remark != null and remark != ''\">\n" +
            "                remark = #{remark},\n" +
            "            </if>\n" +
            "            <if test=\"deptId != null and deptId != ''\">\n" +
            "                dept_id = #{deptId},\n" +
            "            </if>\n" +
            "            <if test=\"deptName != null and deptName != ''\">\n" +
            "                dept_name = #{deptName},\n" +
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
            "        where user_id = #{userId}" +
            "</script>")
    void updateUser(User user);

    /**
     * 根据部门id查询部门名称
     * @param id id
     * @return 部门名称
     */
    @Select("select dept_name from ss_dept where dept_id=#{id}")
    String findDeptById(String id);
}
