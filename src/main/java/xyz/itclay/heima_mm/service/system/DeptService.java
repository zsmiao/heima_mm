package xyz.itclay.heima_mm.service.system;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import xyz.itclay.heima_mm.domain.system.Dept;

import java.util.List;

/**
 * 部门管理service
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 11:31
 **/
public interface DeptService {
    /**
     * 查询所有部门信息
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 部门集合信息
     */
    List<Dept> findByPage(String pageNum, String pageSize);

    /**
     * 查询所有部门信息
     *
     * @return 部门集合信息
     */
    List<Dept> findByAll();

    /**
     * 根据id查询
     *
     * @param id id
     * @return 部门信息
     */
    Dept findById(String id);

    /**
     * 添加部门信息
     *
     * @param dept 部门信息
     */
    void addDept(Dept dept);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     */
    void updateDept(Dept dept);
}
