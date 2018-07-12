package com.redis.demo.controllers;

import com.redis.demo.vo.Article;

import java.util.List;

/**
 * @author
 * @date 2018-07-11 22:56
 **/
public interface ArticleController {
    /**
     * 给文章投票
     * @return
     */
    public String vote(Integer articleId);

    /**
     * 添加文章
     * @return
     */
    public String addArticle(Article article);
    public List<Article> getNewArticles();
    public List<Article> getHighScoreArt();
}
