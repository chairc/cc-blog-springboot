package com.cc.blog.controller;

import com.cc.blog.model.Entertainment;
import com.cc.blog.model.ResultSet;
import com.cc.blog.service.EntertainmentService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EntertainmentController {
    @Autowired
    private EntertainmentService entertainmentService;

    @RequestMapping("/entertainment/{pageNum}")
    public String showEntertainmentPage(Model model,
                                        @PathVariable int pageNum){
        Page<Entertainment> pages = PageHelper.startPage(pageNum,20);
        List<Entertainment> list = entertainmentService.getEntertainmentAll();
        model.addAttribute("entertainment", list);
        Tools.indexPageHelperJudge(model,pageNum,pages,20);
        return "entertainment";
    }

    @RequestMapping("/entertainment/wps")
    public String wpsLogin(){
        return "wps_invite";
    }

    @RequestMapping("/entertainment/wps/wpsAutoInviteByAjax")
    @ResponseBody
    public ResultSet wpsAutoInviteByAjax(@RequestParam(value = "uid", required = false) String uid){

        return entertainmentService.wpsAutoInvite(uid);
    }
}
