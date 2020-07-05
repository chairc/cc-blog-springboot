package com.cc.blog.model;

public class Article {

    private int article_id;                 //  文章id
    private String article_private_id;      //  文章私有id
    private String article_title;           //  文章题目
    private String article_author;          //  文章作者
    private String article_main;            //  文章主体
    private String article_time;            //  文章写作时间
    private int article_click_num;          //  文章点击数
    private String article_system;          //  文章写作时用户所用系统
    private String article_browser;         //  文章写作时用户所用浏览器
    private String article_ip;              //  文章写作时用户所用ip

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getArticle_private_id() {
        return article_private_id;
    }

    public void setArticle_private_id(String article_private_id) {
        this.article_private_id = article_private_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_main() {
        return article_main;
    }

    public void setArticle_main(String article_main) {
        this.article_main = article_main;
    }

    public String getArticle_time() {
        return article_time;
    }

    public void setArticle_time(String article_time) {
        this.article_time = article_time;
    }

    public int getArticle_click_num() {
        return article_click_num;
    }

    public void setArticle_click_num(int article_click_num) {
        this.article_click_num = article_click_num;
    }

    public String getArticle_system() {
        return article_system;
    }

    public void setArticle_system(String article_system) {
        this.article_system = article_system;
    }

    public String getArticle_browser() {
        return article_browser;
    }

    public void setArticle_browser(String article_browser) {
        this.article_browser = article_browser;
    }

    public String getArticle_ip() {
        return article_ip;
    }

    public void setArticle_ip(String article_ip) {
        this.article_ip = article_ip;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_private_id='" + article_private_id + '\'' +
                ", article_title='" + article_title + '\'' +
                ", article_author='" + article_author + '\'' +
                ", article_main='" + article_main + '\'' +
                ", article_time='" + article_time + '\'' +
                ", article_click_num=" + article_click_num +
                ", article_system='" + article_system + '\'' +
                ", article_browser='" + article_browser + '\'' +
                ", article_ip='" + article_ip + '\'' +
                '}';
    }
}
