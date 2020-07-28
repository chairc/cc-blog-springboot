package cn.chairc.blog.service.impl;

import cn.chairc.blog.mapper.MessageDao;
import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.Message;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import cn.chairc.blog.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    /**
     * 主页获取留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllToIndex() {
        List<Message> messageList = messageDao.getMessageAllToIndex();
        //对每个对象进行循环
        for (Message message : messageList) {
            //如果查找的用户privateId不为空值，那么就通过私有ID使用cc_blog_user中的nickname
            if (message.getMessage_user_private_id() != null){
                User user = userService.getUserByPrivateId(message.getMessage_user_private_id());
                if (user.getUser_common_nickname() != null){
                    message.setMessage_username(user.getUser_common_nickname());
                }
                //后期自动分配（挖坑！）
            }
        }
        return messageList;
    }

    /**
     * 主页获取精选留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllToIndexByWeight() {
        List<Message> messageList = messageDao.getMessageAllToIndexByWeight();
        //对每个对象进行循环
        for (Message message : messageList) {
            //如果查找的用户privateId不为空值，那么就通过私有ID使用cc_blog_user中的nickname
            if (message.getMessage_user_private_id() != null){
                User user = userService.getUserByPrivateId(message.getMessage_user_private_id());
                if (user.getUser_common_nickname() != null){
                    message.setMessage_username(user.getUser_common_nickname());
                }
                //后期自动分配（挖坑！）
            }
        }
        return messageList;
    }

    /**
     * 获取留言（降序）
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllByAscendingOrder() {
        List<Message> messageList = messageDao.getMessageAllByAscendingOrder();
        //对每个对象进行循环
        for (Message message : messageList) {
            //如果查找的用户privateId不为空值，那么就通过私有ID使用cc_blog_user中的nickname
            if (message.getMessage_user_private_id() != null){
                User user = userService.getUserByPrivateId(message.getMessage_user_private_id());
                if (user.getUser_common_nickname() != null){
                    message.setMessage_username(user.getUser_common_nickname());
                }
                //后期自动分配（挖坑！）
            }
        }
        return messageList;
    }

    /**
     * 获取留言（升序）
     *
     * @return
     */

    @Override
    public List<Message> getMessageAll() {
        List<Message> messageList = messageDao.getMessageAll();
        //对每个对象进行循环
        for (Message message : messageList) {
            //如果查找的用户privateId不为空值，那么就通过私有ID使用cc_blog_user中的nickname
            if (message.getMessage_user_private_id() != null){
                User user = userService.getUserByPrivateId(message.getMessage_user_private_id());
                if (user.getUser_common_nickname() != null){
                    message.setMessage_username(user.getUser_common_nickname());
                }
                //后期自动分配（挖坑！）
            }
        }
        return messageList;
    }

    /**
     * 获取精选留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllByWeight() {
        return messageDao.getMessageAllByWeight();
    }

    /**
     * 管理员获取留言
     *
     * @return
     */

    @Override
    public DataResultSet getMessageAllByAdmin(int pageNum) {
        DataResultSet dataResultSet = new DataResultSet();
        try {
            String privateIdVal = Tools.sessionValidate("privateId");
            User admin = userService.getUserByPrivateId(privateIdVal);
            if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
                Page<Message> messagePages = PageHelper.startPage(pageNum, 10);
                List<Message> messageList = messageDao.getMessageAllByAdmin();
                dataResultSet.success("超级管理员留言列表获取成功");
                dataResultSet.setData(messageList);
                dataResultSet.setPage_num(pageNum);
                dataResultSet.setPage_count((int) messagePages.getTotal());
                dataResultSet.setPage_total((int) ((messagePages.getTotal() - 1) / 10 + 1));
            } else {
                dataResultSet.fail("超级管理员用户列表获取失败");
            }
        } catch (NullPointerException e) {
            dataResultSet.error();
        }
        return dataResultSet;
    }

    /**
     * 新增留言
     *
     * @param message
     */

    @Override
    public ResultSet insertMessage(Message message) {
        ResultSet resultSet = new ResultSet();
        if (message.getMessage_username() == null) {
            //未登录
            resultSet.fail("用户未登录");
        } else {
            try {
                messageDao.insertMessage(message);
                resultSet.success("存取成功");
            } catch (Exception e) {
                resultSet.error();
            }
        }
        return resultSet;
    }

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */

    @Override
    public ResultSet deleteMessage(String privateId) {
        ResultSet resultSet = new ResultSet();
        String privateIdVal = Tools.sessionValidate("privateId");
        User admin = userService.getUserByPrivateId(privateIdVal);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                messageDao.deleteMessageByPrivateId(privateId);
                resultSet.success("删除留言" + privateId + "成功");
            }catch (Exception e){
                resultSet.error();
            }
        }else {
            resultSet.fail("删除留言" + privateId + "失败");
        }
        return resultSet;
    }

    /**
     * 更新留言
     *
     * @param message
     */

    @Override
    public ResultSet updateMessage(Message message) {
        ResultSet resultSet = new ResultSet();
        String privateIdVal = Tools.sessionValidate("privateId");
        User admin = userService.getUserByPrivateId(privateIdVal);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                messageDao.updateMessage(message);
                resultSet.success("更新文章" + message.getMessage_private_id() + "基本信息成功");
            } catch (Exception e) {
                resultSet.error();
            }
        } else {
            resultSet.fail("更新文章" + message.getMessage_private_id() + "基本信息失败");
        }
        return resultSet;
    }

    /**
     * 获取留言数
     *
     * @return
     */

    @Override
    public Integer getMessageCount() {
        return messageDao.getMessageCount();
    }

    /**
     * 通过私有Id获取留言
     *
     * @param privateId
     * @return
     */

    @Override
    public ResultSet getMessageByPrivateId(String privateId) {
        ResultSet resultSet = new ResultSet();
        try {
            String privateIdVal = Tools.sessionValidate("privateId");
            User admin = userService.getUserByPrivateId(privateIdVal);
            if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
                resultSet.success("获取留言信息成功");
                resultSet.setData(messageDao.getMessageByPrivateId(privateId));
            } else {
                resultSet.fail("操作用户无权限");
            }
        } catch (Exception e) {
            resultSet.error();
        }
        return resultSet;
    }
}
