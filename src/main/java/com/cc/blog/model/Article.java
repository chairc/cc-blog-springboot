package com.cc.blog.model;

public class Article {

    private int article_id;
    private String article_private_id;
    private String article_title;
    private String article_author;
    private String article_main;
    private String article_time;
    private int article_click_num;

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
                '}';
    }
}
