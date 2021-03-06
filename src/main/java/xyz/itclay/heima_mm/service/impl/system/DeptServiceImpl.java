package xyz.itclay.heima_mm.service.impl.system;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.mapper.system.DeptMapper;
import xyz.itclay.heima_mm.service.system.DeptService;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.util.List;

/**
 * 部门管理实现类
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 11:32
 **/
public class DeptServiceImpl implements DeptService {
    @Override
    public List<Dept> findByPage(String pageNum, String pageSize) {
        DeptMapper mapper = MyBatisUtils.getMapper(DeptMapper.class);
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
    public List<Dept> findByAll() {
        DeptMapper mapper = MyBatisUtils.getMapper(DeptMapper.class);
        return mapper.findAll();
    }

    @Override
    public Dept findById(String id) {
        DeptMapper mapper = MyBatisUtils.getMapper(DeptMapper.class);
        return mapper.findByPid(id);
    }

    @Override
    public void addDept(Dept dept) {
        DeptMapper mapper = MyBatisUtils.getMapper(DeptMapper.class);
        dept.setDeptId(GuidUtil.getUuid());
        mapper.addDept(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        DeptMapper mapper = MyBatisUtils.getMapper(DeptMapper.class);
        mapper.updateDept(dept);
    }
}
