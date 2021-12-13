package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.article.ArticleEntity;
import cn.chairc.blog.entity.article.ArticleLabelEntity;
import cn.chairc.blog.entity.article.ArticleTypeEntity;
import cn.chairc.blog.entity.comment.CommentMessageEntity;
import cn.chairc.blog.entity.friend.FriendEntity;
import cn.chairc.blog.entity.message.MessageEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.entity.user.UserIndexLoginInfoEntity;
import cn.chairc.blog.service.*;
import cn.chairc.blog.utils.CommonUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 通用页面跳转控制层
 *
 * @author chairc
 * @date 2021/5/4 19:15
 */
@Controller
public class IndexCommonController {

    private static final Logger log = LoggerFactory.getLogger(IndexCommonController.class);

    private static final String PERMISSION_ADMIN = "user:admin";

    private static final String PERMISSION_USER = "user:user";

    private ArticleService articleService;

    private MessageService messageService;

    private FriendService friendService;

    private UserService userService;

    private CommentService commentService;

    @Autowired
    public IndexCommonController(ArticleService articleService, MessageService messageService,
                                 FriendService friendService, UserService userService,
                                 CommentService commentService) {
        this.articleService = articleService;
        this.messageService = messageService;
        this.friendService = friendService;
        this.userService = userService;
        this.commentService = commentService;
    }

    /**
     * 主页面登录信息返回方法
     *
     * @return userIndexLoginInfoEntity
     */

    private UserIndexLoginInfoEntity getUserIndexLoginInfo() {
        UserIndexLoginInfoEntity userIndexLoginInfoEntity = new UserIndexLoginInfoEntity();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        String userHeadUrl = "/static/images/picture/login/login-ico.svg";
        if (userPrivateId != null) {
            userIndexLoginInfoEntity.setUserIsLogin(1);
            userIndexLoginInfoEntity.setUsername(CommonUtil.sessionValidate("username"));
            userIndexLoginInfoEntity.setUserEmail(CommonUtil.sessionValidate("userEmail"));
            userIndexLoginInfoEntity.setUserPrivateId(userPrivateId);
            UserHeadPictureEntity userHeadPictureEntity = userService.getUserHeadPic(userPrivateId);
            if (userHeadPictureEntity != null) {
                userHeadUrl = userHeadPictureEntity.getUserHeadMappingThumbnailUrl();
            }
            userIndexLoginInfoEntity.setUserHeadUrl(userHeadUrl);
        }
        return userIndexLoginInfoEntity;
    }

    /**
     * 显示主页
     *
     * @param model 模型
     * @return index.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/")
    public String showIndexPage(Model model) {
        Page<ArticleEntity> articleEntityPage = PageHelper.startPage(1, 10);
        List<ArticleEntity> articleEntityList = articleService.listArticleByArticleType("index", "all");
        model.addAttribute("indexArticleEntityList", articleEntityList);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        return "index/index";
    }

    /**
     * 这是第一个彩蛋
     *
     * @return hello_world.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping({"/helloWorld", "/helloworld"})
    public String showHelloWorldPage() {
        return "surprise/hello_world";
    }

    /**
     * 这是第二个彩蛋
     *
     * @return hello_world.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/love")
    public String showLovePage() {
        return "surprise/love";
    }

    /**
     * 根据权限跳转管理界面
     *
     * @return 管理界面
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/home")
    public String showHomePage() {
        String permission = CommonUtil.sessionValidate("permission");
        if (PERMISSION_ADMIN.equals(permission)) {
            log.info("当前跳转为管理员界面");
            return "redirect:/admin/index";
        } else if (PERMISSION_USER.equals(permission)) {
            log.info("当前跳转为用户界面");
            return "redirect:/user/index";
        } else {
            log.info("当前跳转为登录界面");
            return "redirect:/login";
        }
    }

    /**
     * 显示登录页面
     *
     * @return login.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/login")
    public String showLoginPage() {
        return "index/login";
    }

    /**
     * 显示注册页面
     *
     * @return registered.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/registered")
    public String showRegisteredPage() {
        return "index/registered";
    }

    /**
     * 显示找回密码页面
     *
     * @return forgot_password.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/forgotPassword")
    public String showForgotPasswordPage() {
        return "index/forgot_password";
    }

    /**
     * 主页显示文章列表
     *
     * @param page       当前页
     * @param searchType 搜索类型
     * @param type       分类
     * @param model      模型
     * @return article.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/article")
    public String showArticleAndArticleTypePage(@RequestParam(value = "page") int page,
                                                @RequestParam(value = "searchType") String searchType,
                                                @RequestParam(value = "type") String type,
                                                Model model) {
        List<ArticleEntity> articleEntityAllList = articleService.listArticleByArticleType("index", "all");
        Page<ArticleEntity> articleEntityPage = PageHelper.startPage(page, 10);
        List<ArticleEntity> articleEntityList = null;
        //  判断文章大分类，大分类包括类型分类和标签分类
        switch (type) {
            case "all":
                articleEntityList = articleService.listArticleByArticleType("index", "all");
                break;
            //  类型分类
            case "articleType":
                articleEntityList = articleService.listArticleByArticleType("index", searchType);
                break;
            //  标签分类
            case "labelType":
                articleEntityList = articleService.listArticleByArticleLabel("index", searchType);
                break;
            default:
        }
        List<ArticleLabelEntity> articleLabelEntityList = articleService.listArticleLabel();
        List<ArticleTypeEntity> articleTypeEntityList = articleService.listArticleType();
        model.addAttribute("articleEntityList", articleEntityList);
        model.addAttribute("articleEntityTimeLineList", articleEntityAllList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((articleEntityPage.getTotal() - 1) / 10 + 1));
        model.addAttribute("currentType", type);
        model.addAttribute("currentSearchType", searchType);
        model.addAttribute("articleLabel", articleLabelEntityList);
        model.addAttribute("articleType", articleTypeEntityList);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        return "index/article";
    }

    /**
     * 显示文章详细页面
     *
     * @param model            模型
     * @param articlePrivateId 文章私有ID
     * @param page             当前页
     * @return article_show.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/article/title/{articlePrivateId}")
    public String showArticleDetailPage(@PathVariable String articlePrivateId,
                                        @RequestParam(value = "page") int page,
                                        Model model) {
        //  文章回复列表...


        ArticleEntity articleEntity = articleService.getArticleByPrivateId(articlePrivateId, "index");
        List<ArticleLabelEntity> articleLabelEntityList = articleService.listArticleLabel();
        List<ArticleTypeEntity> articleTypeEntityList = articleService.listArticleType();
        model.addAttribute("articleDetail", articleEntity);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        model.addAttribute("articleLabel", articleLabelEntityList);
        model.addAttribute("articleType", articleTypeEntityList);
        return "index/article_show";
    }

    /**
     * 显示留言列表
     *
     * @param model 模型
     * @param page  当前页
     * @return message.html
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/message")
    public String showMessagePage(Model model, @RequestParam(value = "page") int page) {
        Page<MessageEntity> messageEntityPage = PageHelper.startPage(page, 10);
        List<MessageEntity> messageEntityList = messageService.listMessage();
        UserHeadPictureEntity userHeadPictureEntity = userService.getUserHeadPic(CommonUtil.sessionValidate("userPrivateId"));
        model.addAttribute("messageEntityList", messageEntityList);
        model.addAttribute("userHeadPic", userHeadPictureEntity);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((messageEntityPage.getTotal() - 1) / 10 + 1));
        return "index/message";
    }

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/message/title/{messagePrivateId}")
    public String showMessageDetailPage(@PathVariable String messagePrivateId,
                                        @RequestParam(value = "page") int page,
                                        Model model) {
        //  留言回复列表...
        MessageEntity messageEntity = messageService.getMessage(messagePrivateId);
        Page<CommentMessageEntity> commentMessageEntityPage = PageHelper.startPage(page, 10);
        List<CommentMessageEntity> commentMessageEntityList = commentService.listCommentMessage("index", messagePrivateId);
        model.addAttribute("messageEntity", messageEntity);
        model.addAttribute("commentMessageEntityList", commentMessageEntityList);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((commentMessageEntityPage.getTotal() - 1) / 10 + 1));
        return "index/message_show";
    }

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/friend")
    public String showFriendPage(Model model) {
        List<FriendEntity> friendEntityList = friendService.listFriend("index", "all", "all");
        model.addAttribute("friendList", friendEntityList);
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        return "index/friend";
    }

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/data")
    public String showDataPage(Model model) {
        model.addAttribute("userIndexLoginInfo", getUserIndexLoginInfo());
        return "index/data";
    }

}
