package cn.chairc.blog.controller;

import cn.chairc.blog.model.*;
import cn.chairc.blog.service.*;
import cn.chairc.blog.util.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private HeadImageService headImageService;

    @Value("${head-image.default-head-image}")
    private String DEFAULT_HEAD_IMAGE;

    @Value("${head-image.default-head-image-man}")
    private String DEFAULT_HEAD_IMAGE_MAN;

    @Value("${head-image.default-head-image-woman}")
    private String DEFAULT_HEAD_IMAGE_WOMAN;

    //登录相关

    /**
     * 管理界面显示
     *
     * @param request
     * @return
     */

    @RequestMapping("/admin")
    public String showSuperAdmin(HttpServletRequest request,
                                 Model model) {
        try {
            HttpSession session = request.getSession();
            String usernameIsAdmin = (String) session.getAttribute("username");
            User user = userService.getUserByUsername(usernameIsAdmin);
            //管理员登录需要验证角色，以防止空指针异常
            if (Tools.usernameSessionIsAdminValidate(user.getUser_safe_role())) {
                String userHeadImage, userFriendLink;
                model.addAttribute("privateId", user.getUser_common_private_id());
                model.addAttribute("openId", user.getUser_common_open_id());
                model.addAttribute("username", user.getUser_common_username());
                model.addAttribute("nickname", user.getUser_common_nickname());
                if (user.getUser_common_head_image_id() != 0) {
                    HeadImage headImage = headImageService.getUserHeadImage(user.getUser_common_head_image_id());
                    userHeadImage = headImage.getHead_image_url();
                } else {
                    if (user.getUser_secret_sex().equals("男")) {
                        userHeadImage = DEFAULT_HEAD_IMAGE_MAN;
                    } else if (user.getUser_secret_sex().equals("女")) {
                        userHeadImage = DEFAULT_HEAD_IMAGE_WOMAN;
                    } else {
                        userHeadImage = DEFAULT_HEAD_IMAGE;
                    }
                }
                model.addAttribute("headImage", userHeadImage);
                if (user.getUser_common_friend_link_id() != 0) {
                    FriendLink friendLink = friendLinkService.getUserFriendLink(user.getUser_common_friend_link_id());
                    userFriendLink = friendLink.getFriend_link_url();
                } else {
                    userFriendLink = "暂无友链";
                }
                model.addAttribute("friendLink", userFriendLink);
                model.addAttribute("realName", user.getUser_secret_real_name());
                model.addAttribute("phone", user.getUser_secret_phone());
                model.addAttribute("email", user.getUser_secret_email());
                model.addAttribute("birthday", user.getUser_secret_birthday());
                model.addAttribute("sex", user.getUser_secret_sex());
                model.addAttribute("age", user.getUser_secret_age());
                model.addAttribute("wechat", user.getUser_secret_wechat());
                model.addAttribute("qq", user.getUser_secret_qq());
                model.addAttribute("weibo", user.getUser_secret_weibo());
                model.addAttribute("address", user.getUser_secret_address());
                model.addAttribute("logtime", user.getUser_safe_logtime());
                return "admin";
            }
        } catch (Exception e) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    /**
     * 超级管理员登出
     *
     * @return 重定向：主页
     */

    @RequestMapping("/logout")
    public String superAdminLogout() {
        System.out.println("登出人员：" + Tools.usernameSessionValidate());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }


    //管理模块

    //用户操作

    /**
     * 更新用户
     *
     * @return resultSet
     */

    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultSet updateUserInfoByAjax(@RequestParam(value = "id") int id) {
        ResultSet resultSet = new ResultSet();
        resultSet.success("删除成功");
        return resultSet;
    }

    /**
     * 删除用户
     *
     * @param deleteId
     * @return resultSet
     */

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultSet deleteUserInfoByAjax(@RequestParam(value = "deleteId") int deleteId) {
        ResultSet resultSet = new ResultSet();
        superAdminService.deleteUserById(deleteId);
        resultSet.success("删除成功");
        return resultSet;
    }

    /**
     * 展示用户数据
     *
     * @param userId
     * @return resultSet
     */

    @RequestMapping("/showUser")
    @ResponseBody
    public ResultSet showUser(@RequestParam(value = "userId") String userId) {

        System.out.println("待查询用户ID：" + userId);
        System.out.println("总条数：" + userService.getUserCount());
        ResultSet resultSet = superAdminService.superAdminShowUser(userId);
        System.out.println("返回结果：" + resultSet.getData());
        return resultSet;
    }

    /**
     * 将json展示到userList
     *
     * @param pageNum
     * @return DataResultSet
     */

    @RequestMapping("/showUserList")
    @ResponseBody
    public DataResultSet showUserList(@RequestParam(value = "pageNum") int pageNum) {
        return userService.getUserAllByAdmin(pageNum);
    }


    //文章操作


    /**
     * 展示文章数据
     *
     * @param articlePrivateId
     * @return resultSet
     */

    @RequestMapping("/showArticle")
    @ResponseBody
    public ResultSet showArticle(@RequestParam(value = "articlePrivateId") String articlePrivateId) {

        ResultSet resultSet = new ResultSet();
        System.out.println("待查询用户ID：" + articlePrivateId);
        Article article = articleService.getArticleByPrivateId(articlePrivateId);
        resultSet.success("获取文章" + articlePrivateId + "成功");
        resultSet.setData(article);
        return resultSet;
    }

    /**
     * 将json展示到articleList
     *
     * @param pageNum
     * @return DataResultSet
     */

    @RequestMapping("/showArticleList")
    @ResponseBody
    public DataResultSet showArticleList(@RequestParam(value = "pageNum") int pageNum) {
        return articleService.getArticleAllByAdmin(pageNum);
    }

    /**
     * 更新文章基本信息（不编辑文章内容）
     *
     * @param id
     * @param privateId
     * @param title
     * @param author
     * @param time
     * @param click
     * @param ip
     * @param system
     * @param browser
     * @return
     */

    @RequestMapping("/updateArticle")
    public ResultSet updateArticle(@RequestParam(value = "id") int id, @RequestParam(value = "privateId") String privateId,
                                   @RequestParam(value = "title") String title, @RequestParam(value = "author") String author,
                                   @RequestParam(value = "time") String time, @RequestParam(value = "click") int click,
                                   @RequestParam(value = "ip") String ip, @RequestParam(value = "system") String system,
                                   @RequestParam(value = "browser") String browser) {
        Article article = new Article();
        article.setArticle_id(id);
        article.setArticle_private_id(privateId);
        article.setArticle_title(title);
        article.setArticle_author(author);
        article.setArticle_time(time);
        article.setArticle_click_num(click);
        article.setArticle_ip(ip);
        article.setArticle_system(system);
        article.setArticle_browser(browser);
        return articleService.updateArticle(article);
    }

    /**
     * 删除文章
     *
     * @param deletePrivateId
     * @return
     */

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public ResultSet deleteArticle(@RequestParam(value = "deletePrivateId") String deletePrivateId) {
        return articleService.deleteArticleByPrivateId(deletePrivateId);
    }

    /**
     * 展示编辑文章界面
     *
     * @param model
     * @param articlePrivateId
     * @return article_edit.html
     */

    @RequestMapping("/editArticle/{articlePrivateId}")
    public String showEditArticle(Model model,
                                  @PathVariable String articlePrivateId) {
        Article article = articleService.getArticleByPrivateId(articlePrivateId);
        model.addAttribute("articlePrivateId", articlePrivateId);
        model.addAttribute("articleTitle", article.getArticle_title());
        model.addAttribute("articleAuthor", article.getArticle_author());
        model.addAttribute("articleMain", article.getArticle_main());
        return "article_edit";
    }

    /**
     * 编辑文章内容
     *
     * @param articlePrivateId
     * @param articleTitle
     * @param articleAuthor
     * @param articleText
     * @return
     */

    @RequestMapping("/editArticleByAjax")
    @ResponseBody
    public ResultSet editArticle(@RequestParam(value = "articlePrivateId") String articlePrivateId,
                                 @RequestParam(value = "articleTitle") String articleTitle,
                                 @RequestParam(value = "articleAuthor") String articleAuthor,
                                 @RequestParam(value = "articleText") String articleText) {
        Article article = new Article();
        article.setArticle_private_id(articlePrivateId);
        article.setArticle_title(articleTitle);
        article.setArticle_author(articleAuthor);
        article.setArticle_main(articleText);
        return articleService.editArticleByPrivateId(article);
    }

    //留言操作

    /**
     * 将json展示到messageList
     *
     * @param pageNum
     * @return DataResultSet
     */

    @RequestMapping("/showMessageList")
    @ResponseBody
    public DataResultSet showMessageList(@RequestParam(value = "pageNum") int pageNum,
                                         HttpServletRequest request) {
        return messageService.getMessageAllByAdmin(pageNum);
    }

    /**
     * 展示留言数据
     *
     * @param messagePrivateId
     * @return
     */

    @RequestMapping("/showMessage")
    @ResponseBody
    public ResultSet showMessage(@RequestParam(value = "messagePrivateId") String messagePrivateId) {
        return messageService.getMessageByPrivateId(messagePrivateId);
    }

    /**
     * 删除留言操作
     *
     * @param deletePrivateId
     * @return
     */

    @RequestMapping("/deleteMessage")
    @ResponseBody
    public ResultSet deleteMessage(@RequestParam(value = "deletePrivateId") String deletePrivateId) {
        return messageService.deleteMessage(deletePrivateId);
    }


    //友人账操作

    /**
     * 将json展示到friendLinkList
     *
     * @param pageNum
     * @return DataResultSet
     */

    @RequestMapping("/showFriendLinkList")
    @ResponseBody
    public DataResultSet showFriendLinkPageByAdmin(@RequestParam(value = "pageNum") int pageNum) {
        return friendLinkService.getFriendLinkAllByAdmin(pageNum);
    }
}
