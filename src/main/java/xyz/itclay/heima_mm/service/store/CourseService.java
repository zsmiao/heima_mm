package xyz.itclay.heima_mm.service.store;


import xyz.itclay.heima_mm.domain.store.Course;

import java.util.List;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/6 16:57
 **/
public interface CourseService {
    /**
     * 查询所有公司
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 公司名称集合
     */
    List<Course> findByPage(String pageNum, String pageSize);

    /**
     * 添加学科
     *
     * @param course 新闻对象
     */
    void addCourse(Course course);

    /**
     * 根据id查询学科信息
     *
     * @param id id
     * @return 学科信息
     */
    Course findById(String id);

    /**
     * 修改学科信息
     *
     * @param course 学科信息
     */
    void updateCourse(Course course);

    /**
     * 根据id删除学科信息
     *
     * @param id id
     */
    void deleteCourse(String id);

    /**
     *
     * @return 集合
     */
    List<Course> findAll();
}
