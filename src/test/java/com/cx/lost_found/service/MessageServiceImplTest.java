package com.cx.lost_found.service;

import com.cx.lost_found.error.UserException;

import com.cx.lost_found.service.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private MessageService messageService;

    @Test(priority = 0)
    public void createMessageTest() throws ParseException {
        MessageModel message = new MessageModel();
        message.setStudentid("sx001");
        message.setTitle("测试消息");
        message.setDescription("测试消息创建功能");
        message.setMessageType(1);
        Date upTime = new Date();
        message.setUpTime(upTime);

        message.setType("书籍");
        message.setArea("超市");
        message.setStatus(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setFindTime(sdf.parse("2019-10-10 10:12:11"));
        message.setPicture("111111111");
        message.setContactName("wwn");
        message.setContactPhone("110");
        message.setStatus(0);
        message.setAdminJudge(0);

        MessageModel messageModel = null;
        try {
            messageModel = messageService.createMessage(message);
        } catch (UserException e) {
            Assert.assertNull(e);
        }
        Assert.assertNotNull(messageModel);
        Assert.assertNotNull(messageModel.getId());
        Assert.assertEquals(messageModel.getDescription(),"测试消息创建功能");
    }

    @Test(priority = 1)
    public void updateMsgByIdTest(){
        MessageModel messageModel = messageService.getMsgById(10);
        Assert.assertNotNull(messageModel);
        Assert.assertEquals(messageModel.getDescription(),"bug");
        messageModel.setDescription("更新测试消息描述");
        MessageModel newMessage = null;
        try {
            newMessage = messageService.updateMessage(messageModel);
        } catch (UserException e) {
            Assert.assertNull(e);
        }
        Assert.assertNotNull(newMessage);
        Assert.assertEquals(newMessage.getId(),Integer.valueOf("10"));
        Assert.assertEquals(newMessage.getDescription(),"更新测试消息描述");
    }

    @Test(priority = 2)
    public void getMsgByIdTest(){
        MessageModel messageModel = messageService.getMsgById(1);
        Assert.assertNotNull(messageModel);
        Assert.assertEquals(messageModel.getTitle(),"丢了一本书");
    }

    @Test(priority = 3)
    public void deleteMsgByIdTest(){
        try {
            messageService.deleteMsgById(10);
        } catch (UserException e) {
            Assert.assertNull(e);
        }
        Assert.assertNull(messageService.getMsgById(10));
    }

    @Test(priority = 4)
    public void listMsgTest(){
        List<MessageModel> messageModelList = messageService.listMsg();
        Assert.assertNotNull(messageModelList);
        for (MessageModel messageModel : messageModelList){
            System.out.println(messageModel);
        }
    }
}
