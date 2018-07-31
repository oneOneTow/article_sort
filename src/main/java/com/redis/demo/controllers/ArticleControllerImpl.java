package com.redis.demo.controllers;

import com.redis.demo.vo.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/7/12
 */
@Controller
public class ArticleControllerImpl  implements ArticleController {
    public String vote(Integer articleId) {
        return "ok you are success!";
    }
    public String addArticle(Article article) {
        return null;
    }

    @RequestMapping(value="/articles/new.do",method = RequestMethod.GET)
    public List<Article> getNewArticles() {
        return null;
    }

    @RequestMapping(value = "/articles/score.do",method = RequestMethod.GET)
    public List<Article> getHighScoreArticles() {
        return null;
    }
}
