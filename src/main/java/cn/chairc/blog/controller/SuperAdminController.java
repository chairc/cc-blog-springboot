package cn.chairc.blog.controller;

import cn.chairc.blog.model.Article;
import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.User;
import cn.chairc.blog.service.*;
import cn.chairc.blog.util.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    //登录相关

    /**
     * 管理界面显示
     *
     * @param request
     * @return
     */

    @RequestMapping("/admin")
    public String showSuperAdmin(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String usernameIsAdmin = (String) session.getAttribute("username");
            User user = userService.getUserByUsername(usernameIsAdmin);
            //管理员登录需要验证角色，以防止空指针异常
            if (Tools.usernameSessionIsAdminValidate(user.getUser_safe_role())) {
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
     * @throws JsonProcessingException
     */

    @RequestMapping("/showUserList")
    @ResponseBody
    public DataResultSet showUserList(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException {
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

    /**
     * 将json展示到articleList
     *
     * @param pageNum
     * @return DataResultSet
     * @throws JsonProcessingException
     */

    @RequestMapping("/showArticleList")
    @ResponseBody
    public DataResultSet showArticleList(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException {
        return articleService.getArticleAllByAdmin(pageNum);
    }

    //留言操作

    /**
     * 将json展示到messageList
     *
     * @param pageNum
     * @return DataResultSet
     * @throws JsonProcessingException
     */

    @RequestMapping("/showMessageList")
    @ResponseBody
    public DataResultSet showMessageList(@RequestParam(value = "pageNum") int pageNum,
                                         HttpServletRequest request) throws JsonProcessingException {
        return messageService.getMessageAllByAdmin(pageNum);
    }

    //友人账操作

    /**
     * 将json展示到friendLinkList
     *
     * @param pageNum
     * @return DataResultSet
     * @throws JsonProcessingException
     */

    @RequestMapping("/showFriendLinkList")
    @ResponseBody
    public DataResultSet showFriendLinkPageByAdmin(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException {
        return friendLinkService.getFriendLinkAllByAdmin(pageNum);
    }
}
