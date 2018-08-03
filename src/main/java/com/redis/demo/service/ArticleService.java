package com.redis.demo.service;

import com.redis.demo.vo.Article;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author
 * @date 2018-07-11 22:57
 **/
@Component
public interface ArticleService {
     String vote(Integer articleId);
     String addArticle(Article article);
     List<String> getNewArticle();
     List<String> getHighScoreArt();

     /**
      * 判断一篇文章是否存在
      * @param articleId
      * @return
      */
     boolean exist(String articleId);
}
