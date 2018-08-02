package com.redis.demo.controllers;

import com.redis.demo.service.ArticleService;
import com.redis.demo.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/7/12
 */
@Controller
public class ArticleControllerImpl  implements ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping(value = "/article/{id}/vote.do",method = RequestMethod.GET)
    @ResponseBody
    public String vote(@PathVariable("id")Integer articleId) {
        return articleService.vote(articleId);
    }
    @RequestMapping(value = "/article/add.do",method = RequestMethod.POST)
    @ResponseBody
    public String addArticle(@RequestBody  Article article) {
        return null;
    }
    @RequestMapping(value="/articles/new.do",method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getNewArticles() {
        return null;
    }
    @RequestMapping(value = "/articles/score.do",method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getHighScoreArticles() {
        return null;
    }
}
