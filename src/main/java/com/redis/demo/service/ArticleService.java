package com.redis.demo.service;

import com.redis.demo.vo.Article;

/**
 * @author
 * @date 2018-07-11 22:57
 **/
public interface ArticleService {
    public String vote(Integer articleId);
    public String addArticle(Article article);
}
