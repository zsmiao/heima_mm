package xyz.itclay.heima_mm.mapper.store;

import org.apache.ibatis.annotations.*;
import xyz.itclay.heima_mm.domain.store.Catalog;
import xyz.itclay.heima_mm.domain.store.Course;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/6 19:40
 **/
public interface CatalogMapper {
    /**
     * 查询所有课程信息
     */
    @Select("select * from st_catalog")
    @Results(id = "catalogMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "courseId", column = "course_id", javaType = Catalog.class,
                    one = @One(select = "xyz.itclay.heima_mm.mapper.store.CatalogMapper.findByCid"))
    })
    List<Catalog> findAll();

    /**
     * c
     *
     * @param id id
     * @return kec
     */
    @Select("select * from ss_course where id=#{id}")
    @ResultMap("catalogMap")
    Course findByCid(String id);

    @Select(" select * from db_heima_mm.st_catalog where id = #{id}")
    Catalog findById(String id);

    @Insert(" insert into db_heima_mm.st_catalog(id,name, state, remark, order_no, create_by, create_dept, create_time,\n" +
            "                                           update_by, update_time, course_id, course_name)\n" +
            "        values (#{id},#{name}, #{state}, #{remark}, #{orderNo}, #{createBy}, #{createDept}, #{createTime}, #{updateBy},\n" +
            "                #{updateTime}, #{courseId}, #{courseName})")
    void addCatalog(Catalog catalog);
}
