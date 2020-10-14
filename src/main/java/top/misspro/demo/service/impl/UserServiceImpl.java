package top.misspro.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.misspro.demo.dao.entity.User;
import top.misspro.demo.dao.mapper.UserMapper;
import top.misspro.demo.service.UserService;

import javax.annotation.Resource;

/**
 * @author Evan
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 用户save操作，如果save的用户已存在将自动转为update操作
     *
     * @param user user entity
     * @return result
     */
    @Override
    public boolean save(User user) {
        if (null != user) {
            if (user.getUserId() == null) {
                if (userMapper.selectByPrimaryKey(user.getUserId()) != null) {
                    return update(user);
                }
            }
            return userMapper.insertSelective(user) >= 0;
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        if (null != user && user.getUserId() != null) {
            return userMapper.updateByPrimaryKeySelective(user) >= 0;
        }
        return false;
    }
}
