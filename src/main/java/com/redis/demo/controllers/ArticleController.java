package com.redis.demo.controllers;

import com.redis.demo.vo.Article;

import java.util.List;

/**
 * @author
 * @date 2018-07-11 22:56
 **/
public interface ArticleController {
    /**
     * 对文章投票
     * @return
     */
    String vote(Integer articleId);

    /**
     * 添加文章
     * @return
     */
    String addArticle(Article article);

    /**
     * 获取按时间对文章排序的文章
     * @return
     */
    List<Article> getNewArticles();

    /**
     * 获取按分数对文章排序的文章
     * @return
     */
    List<Article> getHighScoreArticles();
}
