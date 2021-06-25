package cn.chairc.blog.service.impl;

import cn.chairc.blog.mapper.ArticleDao;
import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.service.ArticleService;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import cn.chairc.blog.model.Article;
import cn.chairc.blog.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserService userService;

    /**
     * 主页获取文章
     *
     * @return List<Article>
     */

    @Override
    public List<Article> getArticleAllToIndex() {
        return articleDao.getArticleAllToIndex();
    }

    /**
     * 获取文章（降序）
     *
     * @return List<Article>
     */

    @Override
    public List<Article> getArticleAll() {
        return articleDao.getArticleAll();
    }

    /**
     * 获取文章（升序）
     *
     * @return List<Article>
     */

    @Override
    public List<Article> getArticleAllByAscendingOrder() {
        return articleDao.getArticleAllByAscendingOrder();
    }

    /**
     * 文章私有ID获取文章
     *
     * @param articlePrivateId
     * @return Article
     */

    @Override
    public Article getArticleByPrivateId(String articlePrivateId) {
        return articleDao.getArticleByPrivateId(articlePrivateId);
    }

    /**
     * 管理员获取文章
     *
     * @return DataResultSet
     */

    @Override
    public DataResultSet getArticleAllByAdmin(int pageNum) {
        DataResultSet dataResultSet = new DataResultSet();
        try {
            String username = Tools.usernameSessionValidate();
            User admin = userService.getUserByUsername(username);
            if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
                Page<Article> articlePages = PageHelper.startPage(pageNum, 10);
                List<Article> articleList = articleDao.getArticleAllByAdmin();
                dataResultSet.success("超级管理员文章列表获取成功");
                dataResultSet.setData(articleList);
                dataResultSet.setPage_num(pageNum);
                dataResultSet.setPage_count((int) articlePages.getTotal());
                dataResultSet.setPage_total((int) ((articlePages.getTotal() - 1) / 10 + 1));
            } else {
                dataResultSet.fail("超级管理员文章列表获取失败");
            }
        } catch (Exception e) {
            dataResultSet.error();
        }
        return dataResultSet;
    }

    /**
     * 新增文章
     *
     * @param article
     */

    @Override
    public ResultSet insertArticle(Article article) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                articleDao.insertArticle(article);
                resultSet.success("新增文章《" + article.getArticle_title() + "》成功");
            }catch (Exception e){
                resultSet.error();
            }
        }else if (username == null) {
            //未登录
            resultSet.fail("用户未登录，新增文章失败");
        }
        return resultSet;
    }

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */

    @Override
    public ResultSet deleteArticleByPrivateId(String privateId) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                articleDao.deleteArticleByPrivateId(privateId);
                resultSet.success("删除文章" + privateId + "成功");
            }catch (Exception e){
                resultSet.error();
            }
        }else {
            resultSet.fail("删除文章" + privateId + "失败");
        }
        return resultSet;
    }

    /**
     * 更新文章基本信息（不编辑文章）
     *
     * @param article
     */

    @Override
    public ResultSet updateArticle(Article article) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                articleDao.updateArticle(article);
                resultSet.success("更新文章" + article.getArticle_private_id() + "基本信息成功");
            } catch (Exception e) {
                resultSet.error();
            }
        } else {
            resultSet.fail("更新文章" + article.getArticle_private_id() + "基本信息失败");
        }
        return resultSet;
    }

    /**
     * 编辑文章内容
     *
     * @param article
     * @return
     */

    @Override
    public ResultSet editArticleByPrivateId(Article article) {
        ResultSet resultSet = new ResultSet();
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
            try {
                articleDao.editArticleByPrivateId(article);
                resultSet.success("更新文章" + article.getArticle_private_id() + "成功");
            } catch (Exception e) {
                resultSet.error();
            }
        } else {
            resultSet.fail("更新文章" + article.getArticle_private_id() + "失败");
        }
        return resultSet;
    }

    /**
     * 获取文章条数
     *
     * @return 文章条数
     */

    @Override
    public Integer getArticleCount() {
        return articleDao.getArticleCount();
    }
}
