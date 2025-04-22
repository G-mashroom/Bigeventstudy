package org.spstudy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spstudy.mapper.ArticleMapper;
import org.spstudy.pojo.Article;
import org.spstudy.pojo.PageBean;
import org.spstudy.service.ArticleService;
import org.spstudy.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    // 需要使用Mapper层的方法添加
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pb = new PageBean<>();
        //开启分页查询 PageHelper mybatis插件
        PageHelper.startPage(pageNum,pageSize);

        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);
        // Page 中提供了方法，可以获取PageHelper 分页查询后的数据
        Page<Article> p = (Page<Article>) as;

        // 把数据填充到PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article detail(Integer id) {
        Article article = articleMapper.detail(id);
        return article;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);

    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);

    }
}
