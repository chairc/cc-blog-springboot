package cn.chairc.blog.service.impl;

import cn.chairc.blog.mapper.EntertainmentDao;
import cn.chairc.blog.model.Entertainment;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.service.EntertainmentService;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import cn.chairc.blog.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntertainmentServiceImpl implements EntertainmentService {
    @Autowired
    private EntertainmentDao entertainmentDao;

    @Autowired
    private UserService userService;

    @Value("${wps.wps-invite-url}")
    private String WPS_INVITE_URL;

    /**
     * 分主页获取娱乐功能
     *
     * @return List<Entertainment>
     */

    @Override
    public List<Entertainment> getEntertainmentAllToIndex() {
        return entertainmentDao.getEntertainmentAllToIndex();
    }

    /**
     * 主页获取所有娱乐功能
     *
     * @return List<Entertainment>
     */

    @Override
    public List<Entertainment> getEntertainmentAll() {
        return entertainmentDao.getEntertainmentAll();
    }

    /**
     * 管理员获取所有娱乐功能
     *
     * @return resultSet
     */

    @Override
    public ResultSet getEntertainmentAllByAdmin() {
        ResultSet resultSet = new ResultSet();
        try {
            String username = Tools.usernameSessionValidate();
            User admin = userService.getUserByUsername(username);
            if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
                resultSet.success("超级管理员娱乐列表获取成功");
                resultSet.setData(entertainmentDao.getEntertainmentAll());
            }else {
                resultSet.fail("超级管理员娱乐列表获取失败");
            }
        }catch (Exception e){
            resultSet.error();
        }
        return null;
    }

    /**
     * wps自动邀请
     *
     * @param uid
     * @return resultSet
     */

    @Override
    public ResultSet wpsAutoInvite(String uid) {
        ResultSet resultSet = new ResultSet();
        try {
            //从数据库中读取wps的sid
            List<Entertainment> list = entertainmentDao.getWpsSidAll();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = new HashMap<>();
            map.put("invite_userid", uid);
            //转成json格式
            String mapToJson = mapper.writeValueAsString(map);
            StringBuilder sb = new StringBuilder();
            URL urlObj = new URL(WPS_INVITE_URL);

            //循环n次由sid进行邀请
            for (Entertainment entertainment : list) {
                System.out.println("当前sid为:" + entertainment.getWps_sid());
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                //post请求不能使用缓存
                conn.setUseCaches(false);
                // 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此设为true, 默认情况下是false;
                conn.setDoOutput(true);
                // 设置是否从httpUrlConnection读入，默认情况下是true;
                conn.setDoInput(true);
                // 设定请求的方法为"POST"，默认是GET
                conn.setRequestMethod("POST");
                //添加请求头header
                conn.setRequestProperty("Host", "zt.wps.cn");
                conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                conn.setRequestProperty("sid", entertainment.getWps_sid());
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"))) {
                    writer.write(mapToJson);
                    System.out.println(mapToJson);
                    writer.flush();
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                    /*String tmpLine;
                    while ((tmpLine = reader.readLine()) != null) {
                        sb.append(tmpLine);
                    }
                    System.out.println("sb:" + sb);*/
                    String tmpLine  = reader.readLine();
                    try{
                        ObjectMapper objectMapper = new ObjectMapper();
                        HashMap hashMap = objectMapper.readValue(tmpLine, HashMap.class);
                        String result = (String) hashMap.get("result");
                        if(result.equals("ok")){
                            System.out.println("当前返回值:" + tmpLine);
                        }
                    }catch (Exception e){
                        System.out.println("当前sid:" + entertainment.getWps_sid() + "已失效");
                    }
                }
                conn.disconnect();
            }
            resultSet.success("wps自动邀请成功");
        } catch (IOException e) {
            e.printStackTrace();
            resultSet.fail("wps自动邀请失败");
        }
        return resultSet;
    }
}
