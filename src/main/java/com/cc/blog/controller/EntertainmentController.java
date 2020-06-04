package com.cc.blog.controller;

import com.cc.blog.model.ResultSet;
import com.cc.blog.service.EntertainmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EntertainmentController {
    @Autowired
    private EntertainmentService entertainmentService;

    @RequestMapping("/wps")
    public String wpsLogin(){
        return "wps_invite";
    }

    @RequestMapping("/wpsAutoInviteByAjax")
    @ResponseBody
    public ResultSet wpsAutoInviteByAjax(@RequestParam(value = "uid", required = false) String uid){
        ResultSet resultSet = new ResultSet();
        if(entertainmentService.wpsAutoInvite(uid).equals("success")){
            resultSet.setCode("1");
            resultSet.setMsg("wps自动邀请成功");
        }else {
            resultSet.setCode("0");
            resultSet.setMsg("wps自动邀请成功");
        }
        return resultSet;
    }
}
