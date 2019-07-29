package com.cx.lost_found.controller;

import com.cx.lost_found.service.UserService;
import com.cx.lost_found.service.model.UserModel;;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseControllerTest{

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private UserController userController;

    @Override
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void isAdmin() throws Exception {
        String isAdminUrl = "/user/isadmin";

        when(httpServletRequest.getSession()).thenReturn(spy(HttpSession.class));
        when(httpServletRequest.getSession().getAttribute("IS_ADMIN")).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(isAdminUrl);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(httpServletRequest, times(2)).getSession();
        JSONObject jsonObject = new JSONObject(result);
        Assert.assertEquals(jsonObject.getString("data"), "true");
    }

    @Test
    public void loginOut() throws Exception {
        String loginOutUrl = "/user/loginout";

        HttpSession session = spy(HttpSession.class);
        session.setAttribute("IS_LOGIN", true);
        session.setAttribute("LOGIN_USER", "test");
        session.setAttribute("IS_ADMIN", false);

        when(httpServletRequest.getSession()).thenReturn(session);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(loginOutUrl);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        verify(session).removeAttribute("IS_LOGIN");
        verify(session).removeAttribute("LOGIN_USER");
        verify(session).removeAttribute("IS_ADMIN");

        Assert.assertNull(session.getAttribute("IS_LOGIN"));
        Assert.assertNull(session.getAttribute("LOGIN_USER"));
        Assert.assertNull(session.getAttribute("IS_ADMIN"));
    }

    @Test
    public void login() throws Exception {
        String loginUrl = "/user/login";
        String studentId = "sxTest";
        String realName = "testUser";
        String password = "test";
        String telephone = "1234567890";
        String email = "testUser@test.mail.com";

        UserModel userModel = new UserModel();
        userModel.setStudentId(studentId);
        userModel.setPassword(password);
        userModel.setTelephone(telephone);
        userModel.setEmail(email);
        userModel.setRealname(realName);
        userModel.setIsadmin(0);

        HttpSession session = spy(HttpSession.class);
        when(userService.login(studentId, password)).thenReturn(userModel);
        when(httpServletRequest.getSession()).thenReturn(session);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(loginUrl)
                .param("studentid", studentId)
                .param("password", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
        verify(session).setAttribute("IS_LOGIN", true);
        verify(session).setAttribute("LOGIN_USER", userModel);
        verify(session).setAttribute("IS_ADMIN", false);
    }

    @Test
    public void register() throws Exception {
        String registerUrl = "/user/register";
        // 测试数据
        String studentId = "sx111";
        String realName = "testUser";
        String password = "test";
        String telephone = "1234567890";
        String email = "testUser@test.mail.com";
        String optCode = "1111";

        UserModel userModel = new UserModel();
        userModel.setStudentId(studentId);
        userModel.setPassword(password);
        userModel.setTelephone(telephone);
        userModel.setEmail(email);
        userModel.setRealname(realName);
        userModel.setIsadmin(0);

        // stub
        /* 会话中的记录的验证码 */
        when(httpServletRequest.getSession()).thenReturn(spy(HttpSession.class));
        when(httpServletRequest.getSession().getAttribute(telephone)).thenReturn("1111");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(registerUrl)
                .sessionAttr(telephone, "1111")
                .param("studentid", studentId)
                .param("realname", realName)
                .param("password", password)
                .param("telephone", telephone)
                .param("email", email)
                .param("optCode", optCode)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
        verify(userService, times(1)).register(userModel);

    }

    @Test
    public void getOpt() throws Exception {
        String getOptUrl = "/user/getopt";
        String telephone = "18795996968";

        HttpSession session = spy(HttpSession.class);
        when(httpServletRequest.getSession()).thenReturn(session);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(getOptUrl)
                .param("telephone", telephone)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
        verify(httpServletRequest, times(1)).getSession();
    }

    @Test
    public void getUser() throws Exception {
        String getUrl = "/user/get";

        String studentId = "sx001";
        String realName = "testUser";
        String password = "test";
        String telephone = "1234567890";
        String email = "testUser@test.mail.com";

        UserModel userModel = new UserModel();
        userModel.setStudentId(studentId);
        userModel.setPassword(password);
        userModel.setTelephone(telephone);
        userModel.setEmail(email);
        userModel.setRealname(realName);
        userModel.setIsadmin(0);

        // stub
        when(userService.getUserById("sx001")).thenReturn(userModel);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getUrl)
                .param("studentid", studentId)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        String result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
        verify(userService, times(1)).getUserById("sx001");
        JSONObject jsonObject = new JSONObject(result);
        Assert.assertEquals(jsonObject.getJSONObject("data").getString("telephone"), telephone);
    }
}