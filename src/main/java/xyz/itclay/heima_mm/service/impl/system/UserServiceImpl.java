package xyz.itclay.heima_mm.service.impl.system;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import xyz.itclay.heima_mm.domain.system.User;
import xyz.itclay.heima_mm.mapper.system.UserMapper;
import xyz.itclay.heima_mm.service.system.UserService;
import xyz.itclay.heima_mm.utils.GuidUtil;
import xyz.itclay.heima_mm.utils.MD5;
import xyz.itclay.heima_mm.utils.MyBatisUtils;

import java.util.List;

/**
 * @author ZhangSenmiao
 * @date 2021/3/6 13:15
 **/
public class UserServiceImpl implements UserService {
    @Override
    public List<User> findByPage(String pageNum, String pageSize) {
        UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
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
    public User findById(String id) {
        UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
        return mapper.findById(id);
    }

    @Override
    public void addUser(User user) {
//        UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
        SqlSession sqlSession = MyBatisUtils.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String deptName = mapper.findDeptById(user.getDeptId());
        user.setUserId(GuidUtil.getUuid());
        user.setPassword(MD5.md5(user.getPassword()));
        user.setDeptName(deptName);
        mapper.addUser(user);
        MyBatisUtils.close(sqlSession);
    }

    @Override
    public void updateUser(User user) {
        UserMapper mapper = MyBatisUtils.getMapper(UserMapper.class);
        user.setPassword(MD5.md5(user.getPassword()));
        mapper.updateUser(user);
    }
}
