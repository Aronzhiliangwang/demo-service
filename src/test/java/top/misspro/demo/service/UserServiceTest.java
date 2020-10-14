package top.misspro.demo.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import top.misspro.demo.dao.entity.User;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * userService 测试用例
 * @Transactional
 * @Rollback
 * 每个@Test完成都将回滚，其实这里@Order排序已经没有意义了
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@Rollback
class UserServiceTest {

    private final UserService userService;
    private User simulateUser;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    /**
     * 测试方法运行之前，注意每一个@Test方法执行之前这个方法都会执行一次
     */
    @BeforeEach
    void initTest() {
        User user = new User();
        user.setUserId(0);
        user.setUserName("junit");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setEmail("junit@xx.com");
        this.simulateUser = user;
    }


    @Test
    @Order(2)
    void getUser() {
        save();
        User resultUser = userService.getUser(this.simulateUser.getUserId());
        assertNotNull(resultUser);
        assertEquals(this.simulateUser.getUserName(), resultUser.getUserName());
    }


    @Test
    @Order(1)
    void save() {
        //id取消，不然会变为update操作，不过id为0默认是在表中不存在的，其实也没必要，为了下面的update测试，就注释了
//        this.simulateUser.setUserId(null);
        boolean save = userService.save(this.simulateUser);
        assertTrue(save);
    }

    @Test
    @Order(3)
    void update() {
        save();
        this.simulateUser.setUserName("Modify");
        assertTrue(userService.update(this.simulateUser));
        User resultUser = userService.getUser(this.simulateUser.getUserId());
        assertNotNull(resultUser);
        assertEquals(this.simulateUser.getUserName(), resultUser.getUserName());
    }
}