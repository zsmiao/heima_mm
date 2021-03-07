package xyz.itclay.heima_mm.mapper.store;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.store.Course;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/6 16:57
 **/
public interface CourseMapper {
    /**
     * 查询所有学科信息
     *
     * @return 所有学科信息
     */
    @Select("select * from st_course")
    List<Course> findAll();

    /**
     * 添加学科信息
     *
     * @param course 学科信息
     */
    @Insert("insert into db_heima_mm.st_course(id,name, state, remark, order_no, create_by, create_dept, create_time, update_by,\n" +
            "                                          update_time)\n" +
            "        values (#{id},#{name}, #{state}, #{remark}, #{orderNo}, #{createBy}, #{createDept}, #{createTime}, #{updateBy},\n" +
            "                #{updateTime})")
    void addCourse(Course course);

    /**
     * 根据id查询学科信息
     *
     * @param id id
     * @return 学科信息
     */
    @Select("select * from st_course where id = #{id}")
    Course findById(String id);

    /**
     * 修改学科信息
     *
     * @param course 学科信息
     */
    @Update("<script>" +
            "update db_heima_mm.st_course\n" +
            "        <set>\n" +
            "            <if test=\"name != null and name != ''\">\n" +
            "                name = #{name},\n" +
            "            </if>\n" +
            "            <if test=\"state != null and state != ''\">\n" +
            "                state = #{state},\n" +
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
            "            <if test=\"updateBy != null and updateBy != ''\">\n" +
            "                update_by = #{updateBy},\n" +
            "            </if>\n" +
            "            <if test=\"updateTime != null\">\n" +
            "                update_time = #{updateTime},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where id = #{id}" +
            "</script>")
    void updateCourse(Course course);
}
