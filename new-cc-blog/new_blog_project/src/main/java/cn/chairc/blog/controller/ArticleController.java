package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.article.ArticleEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.service.ArticleService;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/9 21:54
 */
@Controller
@RequestMapping("/api/article")
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    private static final String YES = "是";

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 新增文章
     *
     * @param articleAddPrivateId    文章私有ID
     * @param articleAddTitle        文章题目
     * @param articleAddIntroduction 文章简介
     * @param articleAddAuthor       文章作者
     * @param articleAddIsHide       文章是否隐藏
     * @param articleAddType         文章类型
     * @param articleAddLabel1       文章标签1
     * @param articleAddLabel2       文章标签2
     * @param articleAddContext      文章正文
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/insertArticle")
    @ResponseBody
    public ResultSet insertArticle(@RequestParam(value = "articleAddPrivateId") String articleAddPrivateId,
                                   @RequestParam(value = "articleAddTitle") String articleAddTitle,
                                   @RequestParam(value = "articleAddIntroduction") String articleAddIntroduction,
                                   @RequestParam(value = "articleAddAuthor") String articleAddAuthor,
                                   @RequestParam(value = "articleAddIsHide") String articleAddIsHide,
                                   @RequestParam(value = "articleAddType") String articleAddType,
                                   @RequestParam(value = "articleAddLabel1") String articleAddLabel1,
                                   @RequestParam(value = "articleAddLabel2") String articleAddLabel2,
                                   @RequestParam(value = "articleAddContext") String articleAddContext){
        ArticleEntity articleEntity = new ArticleEntity();
        try {
            Date insertTime = TimeUtil.getServerTime();
            articleEntity.setArticlePrivateId(articleAddPrivateId);
            articleEntity.setArticleTitle(articleAddTitle);
            articleEntity.setArticleAuthor(articleAddAuthor);
            articleEntity.setArticleIntroduction(articleAddIntroduction);
            articleEntity.setArticleContent(articleAddContext);
            articleEntity.setArticleType(articleAddType);
            if (YES.equals(articleAddIsHide)) {
                articleEntity.setArticleIsHide(1);
            } else {
                articleEntity.setArticleIsHide(-1);
            }
            articleEntity.setArticleIsDelete(1);
            articleEntity.setArticleClickNum(0);
            articleEntity.setCreateTime(insertTime);
            articleEntity.setUpdateTime(insertTime);
            articleEntity.setArticleLabel1(articleAddLabel1);
            articleEntity.setArticleLabel2(articleAddLabel2);
        }catch (Exception e){
            log.error("封装数据出错，原因：{}", e.toString());
        }
        return articleService.insertArticle(articleEntity);
    }

    /**
     * 更新文章
     *
     * @param articleEditPrivateId    文章私有ID
     * @param articleEditTitle        文章题目
     * @param articleEditIntroduction 文章介绍
     * @param articleEditAuthor       文章作者
     * @param articleEditIsHide       文章是否隐藏
     * @param articleEditType         文章类型
     * @param articleEditLabel1       文章标签类型1
     * @param articleEditLabel2       文章标签类型2
     * @param articleEditContext      文章正文
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/updateArticle")
    @ResponseBody
    public ResultSet updateArticle(@RequestParam(value = "articleEditPrivateId") String articleEditPrivateId,
                                   @RequestParam(value = "articleEditTitle") String articleEditTitle,
                                   @RequestParam(value = "articleEditIntroduction") String articleEditIntroduction,
                                   @RequestParam(value = "articleEditAuthor") String articleEditAuthor,
                                   @RequestParam(value = "articleEditIsHide") String articleEditIsHide,
                                   @RequestParam(value = "articleEditType") String articleEditType,
                                   @RequestParam(value = "articleEditLabel1") String articleEditLabel1,
                                   @RequestParam(value = "articleEditLabel2") String articleEditLabel2,
                                   @RequestParam(value = "articleEditContext") String articleEditContext){
        ArticleEntity articleEntity = new ArticleEntity();
        try {
            Date updateTime = TimeUtil.getServerTime();
            articleEntity.setArticlePrivateId(articleEditPrivateId);
            articleEntity.setArticleTitle(articleEditTitle);
            articleEntity.setArticleAuthor(articleEditAuthor);
            articleEntity.setArticleIntroduction(articleEditIntroduction);
            articleEntity.setArticleContent(articleEditContext);
            articleEntity.setArticleType(articleEditType);
            if (YES.equals(articleEditIsHide)) {
                articleEntity.setArticleIsHide(1);
            } else {
                articleEntity.setArticleIsHide(-1);
            }
            articleEntity.setArticleIsDelete(1);
            articleEntity.setArticleClickNum(0);
            articleEntity.setCreateTime(updateTime);
            articleEntity.setUpdateTime(updateTime);
            articleEntity.setArticleLabel1(articleEditLabel1);
            articleEntity.setArticleLabel2(articleEditLabel2);
        }catch (Exception e){
            log.error("封装数据出错，原因：{}", e.toString());
        }
        return articleService.updateArticle(articleEntity);
    }
}
