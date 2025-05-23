package org.spstudy.service;

import org.spstudy.pojo.Article;
import org.spstudy.pojo.PageBean;

public interface ArticleService {

    // 添加文章
    void add(Article article);
    // 分页查询文章
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    // 查询文章详情
    Article detail(Integer id);
    // 修改文章
    void update(Article article);
    // 删除文章
    void delete(Integer id);
}
