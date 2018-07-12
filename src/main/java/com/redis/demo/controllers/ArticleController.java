package com.redis.demo.controllers;

import com.redis.demo.vo.Article;

import java.util.List;

/**
 * @author
 * @date 2018-07-11 22:56
 **/
public interface ArticleController {
    /**
     * ������ͶƱ
     * @return
     */
    public String vote(Integer articleId);

    /**
     * �������
     * @return
     */
    public String addArticle(Article article);
    public List<Article> getNewArticles();
    public List<Article> getHighScoreArt();
}
