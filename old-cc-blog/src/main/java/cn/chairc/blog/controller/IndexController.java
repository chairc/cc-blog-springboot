package cn.chairc.blog.controller;

import cn.chairc.blog.model.Entertainment;
import cn.chairc.blog.model.FriendLink;
import cn.chairc.blog.model.Message;
import cn.chairc.blog.service.ArticleService;
import cn.chairc.blog.service.EntertainmentService;
import cn.chairc.blog.service.FriendLinkService;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private EntertainmentService entertainmentService;

    /**
     * 主页显示
     *
     * @return mav
     */

    @RequestMapping("/")
    public ModelAndView showIndexPage() {
        ModelAndView mav = new ModelAndView("index");
        List<Article> article = articleService.getArticleAllToIndex();
        List<Message> message = messageService.getMessageAllToIndex();
        List<Message> messageWeight = messageService.getMessageAllToIndexByWeight();
        List<FriendLink> friendLinkList = friendLinkService.getFriendLinkAllToIndex();
        List<Entertainment> entertainmentList = entertainmentService.getEntertainmentAllToIndex();
        mav.addObject("article", article);
        mav.addObject("message", message);
        mav.addObject("message_weight", messageWeight);
        mav.addObject("friendLink",friendLinkList);
        mav.addObject("entertainment",entertainmentList);
        return mav;
    }

    /**
     * 随机跳转页面
     *
     * @return randomPage
     */

    @RequestMapping("/randomJump")
    public String randomJumpPage() {
        String[] randomPages = new String[]{"article/1", "message/1", "friendLink/1"};
        String randomPage = "";
        int randomNum = (int) (Math.random() * 3);
        randomPage = "redirect:/" + randomPages[randomNum];
        return randomPage;
    }
}
