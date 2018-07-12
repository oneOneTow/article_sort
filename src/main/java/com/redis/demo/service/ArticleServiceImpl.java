package com.redis.demo.service;

import com.redis.demo.redisHelper.RedisClientTemplate;
import com.redis.demo.vo.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/7/12
 */
public class ArticleServiceImpl implements ArticleService{
    Logger logger=LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    RedisClientTemplate redisClientTemplate;

    public String vote(Integer articleId) {
        String artIdentity="article:"+articleId;
        return redisClientTemplate.hincrBy(artIdentity,"votes",1).toString();

    }

    public String addArticle(Article article){
        String articleInfo="";
        Set articles=redisClientTemplate.zrevrange("article_time",0,0);
        for(Object articale : articles) {
            articleInfo=articale.toString();
        }
        String [] info=articleInfo.split(":");
        logger.info("ÎÄÕÂ±àºÅ£º{}",Integer.valueOf(info[1]));
        Integer artIdentity=Integer.valueOf(info[1])+1;
        Map<String,String> map=null;
        try{
            map=objectToMap(article);
        }catch(Exception e){
            logger.info("{}",e.getMessage());
        }
        redisClientTemplate.hmset(artIdentity.toString(),map);
        return null;
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
}
