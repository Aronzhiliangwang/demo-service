package top.misspro.demo.service;

import top.misspro.demo.dao.entity.User;

/**
 * @author Evan
 */
public interface UserService {

    /**
     * 获取user
     *
     * @param userId 用户id
     * @return user entity
     */
    User getUser(int userId);

    /**
     * 保存一个用户
     *
     * @param user user entity
     * @return result
     */
    boolean save(User user);


    /**
     * 修改一个用户数据
     *
     * @param user user entity
     * @return result
     */
    boolean update(User user);
}
