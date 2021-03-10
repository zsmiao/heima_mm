package xyz.itclay.heima_mm.service.impl.store;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.store.Course;
import xyz.itclay.heima_mm.mapper.store.CourseMapper;
import xyz.itclay.heima_mm.service.store.CourseService;
import xyz.itclay.heima_mm.utils.DateTimeUtil;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @author ZhangSenmiao
 * @date   2021/3/6 16:57
 **/
public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> findByPage(String pageNum, String pageSize) {
        CourseMapper mapper = MyBatisUtils.getMapper(CourseMapper.class);
        Integer pageN = 1;
        Integer pageS = 10;
        if (StringUtils.isNotBlank(pageNum)) {
            pageN = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(pageSize)) {
            pageS = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(pageN, pageS);
        return mapper.findAll();
    }

    @Override
    public void addCourse(Course course) {
        CourseMapper mapper = MyBatisUtils.getMapper(CourseMapper.class);
        try {
            course.setCreateTime(DateTimeUtil.getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        course.setId(GuidUtil.getUuid());
        mapper.addCourse(course);
    }

    @Override
    public Course findById(String id) {
        CourseMapper mapper = MyBatisUtils.getMapper(CourseMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void updateCourse(Course course) {
        CourseMapper mapper = MyBatisUtils.getMapper(CourseMapper.class);
        try {
            course.setUpdateTime(DateTimeUtil.getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mapper.updateCourse(course);

    }

    @Override
    public void deleteCourse(String id) {

    }

    @Override
    public List<Course> findAll() {
        CourseMapper mapper = MyBatisUtils.getMapper(CourseMapper.class);
        return mapper.findAll();
    }
}
