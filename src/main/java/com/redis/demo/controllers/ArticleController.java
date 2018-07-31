package com.redis.demo.controllers;

import com.redis.demo.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author
 * @date 2018-07-11 22:56
 **/
@Controller
public interface ArticleController {
    /**
     * 对文章投票
     * @return
     */
    @RequestMapping(value = "/article/{id}/vote.do",method = RequestMethod.GET)
    String vote(@PathVariable("id") Integer articleId);

    /**
     * 添加文章
     * @return
     */
    @RequestMapping(value = "/article/add.do",method = RequestMethod.POST)
    String addArticle(Article article);

    /**
     * 获取按时间对文章排序的文章
     * @return
     */
    @RequestMapping(value="/articles/new.do",method = RequestMethod.GET)
    List<Article> getNewArticles();

    /**
     * 获取按分数对文章排序的文章
     * @return
     */
    @RequestMapping(value = "/articles/score.do",method = RequestMethod.GET)
    List<Article> getHighScoreArticles();
}
