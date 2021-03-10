package xyz.itclay.heima_mm.service.impl.system;

import com.github.pagehelper.PageHelper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import xyz.itclay.heima_mm.domain.system.Dept;
import xyz.itclay.heima_mm.domain.system.Role;
import xyz.itclay.heima_mm.mapper.system.RoleMapper;
import xyz.itclay.heima_mm.service.system.RoleService;
import xyz.itclay.heima_mm.utils.DateTimeUtil;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.text.ParseException;
import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/9 15:27
 **/
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findByPage(String pageNum, String pageSize) {
        RoleMapper mapper = MyBatisUtils.getMapper(RoleMapper.class);
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
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = MyBatisUtils.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        //解除现有的所有关系
        mapper.deleteRoleModule(roleId);
        String[] module = moduleIds.split(",");
        for (String moduleId : module) {
            mapper.updateRoleModule(roleId, moduleId);
        }
        MyBatisUtils.close(sqlSession);
    }

    @Override
    public Role findById(String id) {
        RoleMapper mapper = MyBatisUtils.getMapper(RoleMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void updateRole(Role role) {
        RoleMapper mapper = MyBatisUtils.getMapper(RoleMapper.class);
        mapper.updateRole(role);
    }


    @Override
    public void addRole(Role role) {
        RoleMapper mapper = MyBatisUtils.getMapper(RoleMapper.class);
        role.setRoleId(GuidUtil.getUuid());
        try {
            role.setCreateTime(DateTimeUtil.getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mapper.addRole(role);
    }

    @Override
    public void deleteRole(String id) {
        RoleMapper mapper = MyBatisUtils.getMapper(RoleMapper.class);
        mapper.deleteRole(id);
    }
}
