package com.cc.blog.controller;

import com.cc.blog.model.Message;
import com.cc.blog.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/message")
    public String showMessage(Model model) {
        int page = 1;
        Page<Message> pages = PageHelper.startPage(1, 10);
        List<Message> message = messageService.getMessageAll();
        List<Message> message_weight = messageService.getMessageAll_index_weight();
        model.addAttribute("message", message);
        model.addAttribute("message_weight", message_weight);
        model.addAttribute("pageNum", page);
        model.addAttribute("pageNumPrev", 1);
        model.addAttribute("pageNumNext", page + 1);
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "message";
    }

    @RequestMapping("/message/{pageNum}")
    public String messageByPage(Model model,
                                @PathVariable int pageNum) {
        Page<Message> pages = PageHelper.startPage(pageNum, 10);
        List<Message> message = messageService.getMessageAll();
        List<Message> message_weight = messageService.getMessageAll_index_weight();
        model.addAttribute("message", message);
        model.addAttribute("message_weight", message_weight);
        if (pageNum == 1) {
            model.addAttribute("pageNumPrev", 1);
        } else {
            model.addAttribute("pageNumPrev", pageNum - 1);
        }
        if (pageNum == pages.getTotal() / 10 + 1) {
            model.addAttribute("pageNumNext", pages.getTotal() / 10 + 1);
        } else {
            model.addAttribute("pageNumNext", pageNum + 1);
        }
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "message";
    }
}
