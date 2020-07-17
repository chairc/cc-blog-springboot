package cn.chairc.blog.controller;

import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.service.ArticleService;
import cn.chairc.blog.util.Tools;
import cn.chairc.blog.model.Article;
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
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表分页显示
     * <p>
     * {@link MessageController}
     * 注解参考see中MessageController#showMessageByPage(Model, int)中方法
     *
     * @param model
     * @param pageNum
     * @return article页面
     */

    @RequestMapping("/article/{pageNum}")
    public String showArticlePageByPageHelper(Model model,
                                              @PathVariable int pageNum) {
        Page<Article> pages = PageHelper.startPage(pageNum, 8);
        List<Article> list = articleService.getArticleAll();
        model.addAttribute("article", list);
        Tools.indexPageHelperJudge(model,pageNum,pages,8);
        return "article";
    }

    /**
     * 文章显示
     *
     * @param model
     * @param privateId
     * @return show_article页面
     */

    @RequestMapping("/article/title/{privateId}")
    public String showArticleDetailByPrivateId(Model model,
                                               @PathVariable String privateId) {
        Article article = articleService.getArticleByPrivateId(privateId);
        model.addAttribute("articleTitle", article.getArticle_title());
        model.addAttribute("articleAuthor",article.getArticle_author());
        model.addAttribute("articleTime",article.getArticle_time());
        model.addAttribute("articleMain",article.getArticle_main());
        model.addAttribute("articleClickNum",article.getArticle_click_num());
        return "article_show";
    }

    @RequestMapping("/articleEdit")
    public String articleTest(Model model) {
        Article article = articleService.getArticleByPrivateId("article_mCHAvnbTBpaazfd");
        model.addAttribute("articleMain", article.getArticle_main());
        return "article_test";
    }

    @RequestMapping("/article/addArticleByAjax")
    @ResponseBody
    public ResultSet addArticleByAjax(@RequestParam("articleTitle") String articleTitle,
                                      @RequestParam("articleText") String articleText,
                                      HttpServletRequest request) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate();
        if (username == null) {
            //未登录
            resultSet.fail("用户未登录");
        } else {
            try {
                Article article = new Article();
                article.setArticle_private_id(Tools.CreateRandomPrivateId(0));
                article.setArticle_author(username);
                article.setArticle_title(articleTitle);
                article.setArticle_main(articleText);
                article.setArticle_time(Tools.getServerTime());
                article.setArticle_browser(Tools.getBrowserVersion(request));
                article.setArticle_system(Tools.getSystemVersion(request));
                article.setArticle_click_num(0);
                article.setArticle_ip(Tools.getUserIp(request));
                articleService.insertArticle(article);
                resultSet.success("存取成功");
            } catch (Exception e) {
                resultSet.error();
            }
        }
        return resultSet;
    }
}
