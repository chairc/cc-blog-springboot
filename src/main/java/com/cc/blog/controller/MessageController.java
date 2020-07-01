package com.cc.blog.controller;

import com.cc.blog.model.Message;
import com.cc.blog.model.ResultSet;
import com.cc.blog.service.MessageService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 留言列表显示
     *
     * @param model
     * @return message页面
     */

    @RequestMapping("/message")
    public String showMessagePage(Model model,
                                  HttpServletRequest request) {
        int page = 1;
        //第一页开始，一页十条数据
        Page<Message> pages = PageHelper.startPage(1, 10);
        List<Message> message = messageService.getMessageAll(request);
        List<Message> messageWeight = messageService.getMessageAllByWeight(request);
        model.addAttribute("message", message);
        model.addAttribute("message_weight", messageWeight);
        model.addAttribute("pageNum", page);
        //前一页设为1，下一页设为这一页+1
        model.addAttribute("pageNumPrev", 1);
        model.addAttribute("pageNumNext", page + 1);
        //获取后台总条数，进行计算出页面数
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "message";
    }

    /**
     * 留言列表分页显示
     *
     * @param model
     * @param pageNum
     * @return message页面
     */

    @RequestMapping("/message/{pageNum}")
    public String showMessagePageByPageHelper(Model model,
                                              @PathVariable int pageNum,
                                              HttpServletRequest request) {
        //pageNum传进来页面号
        Page<Message> pages = PageHelper.startPage(pageNum, 10);
        List<Message> message = messageService.getMessageAll(request);
        List<Message> messageWeight = messageService.getMessageAllByWeight(request);
        model.addAttribute("message", message);
        model.addAttribute("message_weight", messageWeight);
        if (pageNum == 1) {
            //如果当前页处于第一页，则上一页设为1
            model.addAttribute("pageNumPrev", 1);
        } else {
            //否则上一页设为当前页-1
            model.addAttribute("pageNumPrev", pageNum - 1);
        }
        if (pageNum == pages.getTotal() / 10 + 1) {
            //如果当前页为最后一页，则下一页一直是最后一页
            model.addAttribute("pageNumNext", pages.getTotal() / 10 + 1);
        } else {
            //否则，下一页为当前页+1
            model.addAttribute("pageNumNext", pageNum + 1);
        }
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "message";
    }

    @RequestMapping("/message/addMessageByAjax")
    @ResponseBody
    public ResultSet addMessageByAjax(@RequestParam(value = "messageText") String messageText,
                                   HttpServletRequest request) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate(request);
        if(username == null){
            //未登录
            resultSet.fail("用户未登录");
        }else {
            try {
                System.out.println(messageText);
                Message message = new Message();
                message.setMessage_private_id(Tools.CreateRandomPrivateId(1));
                message.setMessage_username(username);
                message.setMessage_main(messageText);
                message.setMessage_ip(Tools.getUserIp(request));
                message.setMessage_time(Tools.getServerTime());
                message.setMessage_browser(Tools.getBrowserVersion(request));
                message.setMessage_system(Tools.getSystemVersion(request));
                messageService.insertMessage(message,request);
                resultSet.success("存取成功");
            }catch (Exception e){
                resultSet.error();
            }
        }
        return resultSet;
    }
}
