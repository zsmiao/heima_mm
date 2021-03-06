package xyz.itclay.heima_mm.service.system;

import xyz.itclay.heima_mm.domain.system.User;

import java.util.List;

/**
 * 用户
 *
 * @author ZhangSenmiao
 * @date 2021/3/6 13:13
 **/
public interface UserService {
    /**
     * 查询所有用户
     *
     * @param pageNum  页数
     * @param pageSize 条数
     * @return 所有用户
     */
    List<User> findByPage(String pageNum, String pageSize);

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findById(String id);

    /**
     * 添加用户信息
     *
     * @param user 用户信息
     */
    void addUser(User user);

    /**
     * 修给用户信息
     *
     * @param user 用户信息
     */
    void updateUser(User user);
}
