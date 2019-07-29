package com.cx.lost_found;

import com.cx.lost_found.controller.UserControllerTest;
import com.cx.lost_found.service.UserServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceImplTest.class,
        UserControllerTest.class
        // TestNG方法
        // MessageServiceImplTest.class
})
@SpringBootTest
public class LostFoundApplicationTests {
}
