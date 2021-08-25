package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.article.ArticleEntity;
import cn.chairc.blog.entity.article.ArticleLabelEntity;
import cn.chairc.blog.entity.article.ArticleTypeEntity;
import cn.chairc.blog.entity.log.LogVisitorEntity;
import cn.chairc.blog.entity.permission.PermissionEntity;
import cn.chairc.blog.entity.role.RoleEntity;
import cn.chairc.blog.entity.statistics.StatisticsAdminEntity;
import cn.chairc.blog.entity.statistics.StatisticsDataResultSet;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.entity.user.UserPermissionEntity;
import cn.chairc.blog.entity.user.UserRoleEntity;
import cn.chairc.blog.service.*;
import cn.chairc.blog.utils.CommonUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
 * 管理员所属页面跳转控制层
 *
 * @author chairc
 * @date 2021/5/9 14:34
 */
@Controller
@RequestMapping("/admin")
public class IndexAdminController {

    private UserService userService;

    private UserPermissionService userPermissionService;

    private PermissionService permissionService;

    private UserRoleService userRoleService;

    private RoleService roleService;

    private LogService logService;

    private ArticleService articleService;

    private StatisticsService statisticsService;

    @Autowired
    public IndexAdminController(UserService userService, UserPermissionService userPermissionService,
                                PermissionService permissionService, UserRoleService userRoleService,
                                RoleService roleService, LogService logService,
                                ArticleService articleService, StatisticsService statisticsService) {
        this.userService = userService;
        this.userPermissionService = userPermissionService;
        this.permissionService = permissionService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.logService = logService;
        this.articleService = articleService;
        this.statisticsService = statisticsService;
    }

    /**
     * 跳转管理员页面
     *
     * @return admin.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/index")
    public String showAdminPage(Model model) {
        UserHeadPictureEntity userHeadPictureEntity = userService.getUserHeadPic(CommonUtil.sessionValidate("userPrivateId"));
        model.addAttribute("userHeadPic", userHeadPictureEntity);
        return "admin/admin";
    }

    /**
     * 跳转仪表板页面
     *
     * @return admin_dashboard.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/dashboard")
    public String showAdminDashboardPage(Model model) {
        StatisticsAdminEntity statisticsAdminEntity = statisticsService.getAdminStatistics();
        model.addAttribute("adminDashBoardStatistics", statisticsAdminEntity);
        return "admin/dashboard/admin_dashboard";
    }

    /**
     * 跳转数据统计页面
     *
     * @param model 模型
     * @return admin_data.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/data")
    public String showAdminDataPage(Model model) {
        List<StatisticsDataResultSet> currentVisitorData = statisticsService.listCurrentVisitorNumber(7);
        List<StatisticsDataResultSet> currentActiveData = statisticsService.listCurrentActiveData(7);
        model.addAttribute("currentVisitorData", currentVisitorData);
        model.addAttribute("currentActiveData",currentActiveData);
        return "admin/data/admin_data";
    }

    /**
     * 跳转用户管理页面
     *
     * @param page  当前页
     * @param model html接收模型
     * @return admin_users.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/userData/{page}")
    public String showAdminUserDataPage(@PathVariable int page,
                                        Model model) {
        Page<UserEntity> userPage = PageHelper.startPage(page, 5);
        List<UserEntity> userList = userService.listUser();
        model.addAttribute("adminUserList", userList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((userPage.getTotal() - 1) / 5 + 1));
        return "admin/users/admin_users";
    }

    /**
     * 跳转用户权限页面
     *
     * @param page  当前页
     * @param model html接收模型
     * @return admin_permissions.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/userPermissionsData/{page}")
    public String showAdminUserPermissionsDataPage(@PathVariable int page,
                                                   Model model) {
        Page<UserPermissionEntity> userPermissionPage = PageHelper.startPage(page, 5);
        List<UserPermissionEntity> userPermissionList = userPermissionService.listUserPermission();
        List<PermissionEntity> permissionList = permissionService.listPermissions();
        model.addAttribute("userPermissionList", userPermissionList);
        model.addAttribute("permissionList", permissionList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((userPermissionPage.getTotal() - 1) / 5 + 1));
        return "admin/permissions/admin_permissions";
    }

    /**
     * 显示用户角色页面
     *
     * @param page  当前页
     * @param model 模型
     * @return admin_roles.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/userRolesData/{page}")
    public String showAdminUserRolesDataPage(@PathVariable int page,
                                             Model model) {
        Page<UserRoleEntity> userRoleEntityPage = PageHelper.startPage(page, 5);
        List<UserRoleEntity> userRoleEntityList = userRoleService.listUserRole();
        List<RoleEntity> roleEntityList = roleService.listRole();
        model.addAttribute("roleList", roleEntityList);
        model.addAttribute("userRoleList", userRoleEntityList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((userRoleEntityPage.getTotal() - 1) / 5 + 1));
        return "admin/roles/admin_roles";
    }

    /**
     * 显示访问日志页面
     *
     * @param page  当前页
     * @param model 模型
     * @return admin_visitor_log.html
     */

    @RequestMapping("/logVisitorData/{page}")
    public String showAdminVisitorLogDataPage(@PathVariable int page,
                                              Model model) {
        Page<LogVisitorEntity> logVisitorEntityPage = PageHelper.startPage(page, 10);
        List<LogVisitorEntity> logVisitorEntityList = logService.listLogVisitor();
        model.addAttribute("logVisitorList", logVisitorEntityList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((logVisitorEntityPage.getTotal() - 1) / 10 + 1));
        return "admin/logs/admin_log_visitor";
    }

    /**
     * 显示文章列表页面
     *
     * @param page  当前页
     * @param model 模型
     * @return admin_article.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/articleData")
    public String showAdminArticleDataPage(@RequestParam(value = "page") int page,
                                           @RequestParam(value = "searchType") String searchType,
                                           @RequestParam(value = "type") String type,
                                           Model model) {
        Page<ArticleEntity> articleEntityPage = PageHelper.startPage(page, 9);
        List<ArticleEntity> articleEntityList = null;
        switch (type) {
            case "all":
                articleEntityList = articleService.listArticleByArticleType("admin", "all");
                break;
            //  类型分类
            case "articleType":
                articleEntityList = articleService.listArticleByArticleType("admin", searchType);
                break;
            //  标签分类
            case "labelType":
                articleEntityList = articleService.listArticleByArticleLabel("admin", searchType);
                break;
            default:
                //  还没想到写什么
        }
        List<ArticleLabelEntity> articleLabelEntityList = articleService.listArticleLabel();
        List<ArticleTypeEntity> articleTypeEntityList = articleService.listArticleType();
        model.addAttribute("articleList", articleEntityList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", (int) ((articleEntityPage.getTotal() - 1) / 9 + 1));
        model.addAttribute("currentType", type);
        model.addAttribute("currentSearchType", searchType);
        model.addAttribute("articleLabel", articleLabelEntityList);
        model.addAttribute("articleType", articleTypeEntityList);
        return "admin/article/admin_article";
    }

    /**
     * 显示新建归档页面
     *
     * @param model 模型
     * @return admin_article_add.html
     * @throws ParseException 解析异常
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/articleAdd")
    public String showAdminArticleAddPage(Model model) throws ParseException {
        List<ArticleLabelEntity> articleLabelEntityList = articleService.listArticleLabel();
        List<ArticleTypeEntity> articleTypeEntityList = articleService.listArticleType();
        model.addAttribute("articlePrivateId", CommonUtil.createRandomPrivateId("article"));
        model.addAttribute("articleLabel", articleLabelEntityList);
        model.addAttribute("articleType", articleTypeEntityList);
        return "admin/article/admin_article_add";
    }

    /**
     * 显示编辑归档页面
     *
     * @param articlePrivateId 文章私有ID
     * @param model            模型
     * @return admin_article_edit.html
     */

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/articleEdit/{articlePrivateId}")
    public String showAdminArticleEditPage(@PathVariable String articlePrivateId,
                                           Model model) {
        ArticleEntity articleEntity = articleService.getArticleByPrivateId(articlePrivateId, "admin");
        List<ArticleLabelEntity> articleLabelEntityList = articleService.listArticleLabel();
        List<ArticleTypeEntity> articleTypeEntityList = articleService.listArticleType();
        model.addAttribute("articleEditDetail", articleEntity);
        model.addAttribute("articleLabel", articleLabelEntityList);
        model.addAttribute("articleType", articleTypeEntityList);
        return "admin/article/admin_article_edit";
    }

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/emailEdit")
    public String showAdminEmailEditPage() {
        return "admin/email/admin_email_edit";
    }

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/adminSetting")
    public String showAdminSettingPage() {
        return "common/setting";
    }
}
