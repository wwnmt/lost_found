package com.cx.lost_found;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceImplTest.class,
        // TestNG方法
        // MessageServiceImplTest.class
})
@SpringBootTest
public class LostFoundApplicationTests {
}
