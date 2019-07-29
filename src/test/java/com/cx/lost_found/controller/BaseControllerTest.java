package com.cx.lost_found.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public abstract class BaseControllerTest {
    MockMvc mockMvc;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public static void testAll(String className) throws ClassNotFoundException {
        Class obj = Class.forName(className);
        Result result = JUnitCore.runClasses(obj);
        for (Failure failure : result.getFailures()) {
            System.out.println(String.format("FAILED : %s", failure.toString()));
        }
        System.out.println(String.format("TEST SUCCESS : %s", result.wasSuccessful()));
    }
}
