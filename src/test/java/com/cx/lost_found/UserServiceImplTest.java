package com.cx.lost_found;

import com.cx.lost_found.error.UserException;
import com.cx.lost_found.service.UserService;
import com.cx.lost_found.service.model.UserModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LostFoundApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService = null;

    /*
    mysql> select * from user;
    +-----------+-------------+----------+-----------+---------------------+---------+
    | studentid | telephone   | password | realname  | email               | isadmin |
    +-----------+-------------+----------+-----------+---------------------+---------+
    | sx001     | 13319557866 | admin    | 管理员    | 13319557866@163.com |       1 |
    | sx002     | 18795996968 | test     | wwn       | 18795996968@163.com |       1 |
    | sx003     | 12345678    | test     | Weinan Wu | 573877858@qq.com    |       0 |
    +-----------+-------------+----------+-----------+---------------------+---------+
     */
    @Test
    @Transactional
    public void userServiceProcessTest(){
        // register
        UserModel newUser = new UserModel();
        newUser.setStudentId("sx111");
        newUser.setPassword("test1");
        newUser.setRealname("test_user1");
        newUser.setEmail("test1@test.mail.com");
        newUser.setTelephone("1234567890");
        newUser.setIsadmin(0);
        try {
            userService.register(newUser);
        } catch (UserException e) {
            Assert.assertNotNull(e);
        }
        // login
        UserModel user = null;
        try {
            user = userService.login("sx111", "test1");
        } catch (UserException e) {
            System.out.println(e.gerErrMsg());
        }
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getRealname(),"test_user1");

        // getUserById
        UserModel userTest = userService.getUserById("sx111");
        Assert.assertEquals(userTest.getTelephone(),"1234567890");
    }

    @Test
    public void getUserByIdTest(){
        UserModel user_001 = userService.getUserById("sx001");
        UserModel user_002 = userService.getUserById("sx002");
        UserModel user_003 = userService.getUserById("sx003");
        Assert.assertEquals(user_001.getTelephone(),"13319557866");
        Assert.assertEquals(user_002.getRealname(),"wwn");
        Assert.assertEquals(user_003.getEmail(),"573877858@qq.com");
    }

    @Test
    public void loginTest(){
        // 登陆成功
        UserModel user = null;
        try {
            user = userService.login("sx002", "test");
        } catch (UserException e) {
            System.out.println(e.gerErrMsg());
        }
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getRealname(),"wwn");
        // 登陆失败
        UserModel user1 = null;
        try {
            user1 = userService.login("sx002", "1111");
        } catch (UserException e) {
            System.out.println(e.gerErrMsg());
        }
        Assert.assertNull(user1);
    }

    @Test
    @Transactional
    public void registerTest(){
        UserModel newUser = new UserModel();
        newUser.setStudentId("sx222");
        newUser.setPassword("test2");
        newUser.setRealname("test_user2");
        newUser.setEmail("test2@test.mail.com");
        newUser.setTelephone("2234567890");
        newUser.setIsadmin(0);
        try {
            userService.register(newUser);
        } catch (UserException e) {
            Assert.assertNotNull(e);
        }
        UserModel user = userService.getUserById("sx222");
        Assert.assertEquals(user.getRealname(),"test_user2");
    }
}
