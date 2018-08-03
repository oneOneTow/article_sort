package com.redis.demo.service;

import com.redis.demo.enums.ArticleEnum;
import com.redis.demo.enums.CommonParams;
import com.redis.demo.enums.RedisKeys;
import com.redis.demo.redisHelper.RedisClientTemplate;
import com.redis.demo.vo.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/7/12
 */
@Component
public class ArticleServiceImpl implements ArticleService{
    Logger logger=LoggerFactory.getLogger(ArticleServiceImpl.class);
    private static final String ARTICLE_TIME="time";
    private static final String ARTICLE_SCORE="score";
    @Autowired
    RedisClientTemplate redisClientTemplate;

    public String vote(Integer articleId) {
        String artIdentity=RedisKeys.ARTICLE +articleId;
        String res = redisClientTemplate.hincrBy(artIdentity,ArticleEnum.VOTES,1).toString();
        addScore(articleId);
        return res;
    }

    public String addArticle(Article article){
        String articleInfo="";
        Set articles=redisClientTemplate.zrevrange("article_time",0,0);
        for(Object art : articles) {
            articleInfo=art.toString();
        }
        String [] info=articleInfo.split(":");
        logger.info("文章号{}",Integer.valueOf(info[1]));
        Integer artIdentity=Integer.valueOf(info[1])+1;
        Map<String,String> map=null;
        try{
            map=objectToMap(article);
        }catch(Exception e){
            logger.info("{}",e.getMessage());
        }
        redisClientTemplate.hmset(artIdentity.toString(),map);
        redisClientTemplate.zadd(ARTICLE_TIME,0,artIdentity.toString());
        redisClientTemplate.zadd(ARTICLE_SCORE,0,artIdentity.toString());
        return null;
    }
    public List<String> getNewArticle(){
        Set<String> articles=redisClientTemplate.zrange(ARTICLE_TIME,0,-1);
        return new ArrayList<String>(articles);
    }
    public List<String> getHighScoreArt(){
        Set<String> articles=redisClientTemplate.zrange(ARTICLE_SCORE,0,-1);
        return new ArrayList<String>(articles);
    }


    public boolean exist(String Key) {
        return false;
    }

    public Map<String ,String> objectToMap(Object obj) throws Exception{
        Class clazz=obj.getClass();
        Field [] fields=clazz.getFields();
        Map<String ,String> fieldMap=new HashMap<String, String>();
        for(Field field:fields){
            String fieldName=field.getName();
            String methodName=getMethod(field);
            Method method=clazz.getMethod(methodName);
            Object value=method.invoke(obj);
            fieldMap.put(fieldName,value.toString());
        }
        return fieldMap;

    }
    public String getMethod(Field field){
        String preName=field.getName().substring(0,1).toUpperCase();
        String name=field.getName().substring(1);
        return "get"+preName+name;
    }
    public List<Article> getArticles(String flag){
        return null;
    }

    private double addScore(int articleId){
        if(!exist(RedisKeys.ARTICLE_SCORE)){
            String time=redisClientTemplate.hget(RedisKeys.ARTICLE+articleId,ArticleEnum.TIME);
            double score=Double.valueOf(time)+ CommonParams.SCORE_BASE;
            return redisClientTemplate.zadd(RedisKeys.ARTICLE_SCORE,score,String.valueOf(articleId));
        }
       return redisClientTemplate.zincrby(RedisKeys.ARTICLE_SCORE,CommonParams.SCORE_BASE,String.valueOf(articleId));
    }
}
