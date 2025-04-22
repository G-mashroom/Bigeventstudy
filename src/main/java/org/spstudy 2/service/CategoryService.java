package org.spstudy.service;

import org.spstudy.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增分类
    void add(Category category);
    //查看文章列表
    List<Category> list();
    // 根据id 查询 文章
    Category findById(Integer id);
    // 修改文章
    void update(Category category);
    // 删除文章
    void deleteById(Integer id);
}
