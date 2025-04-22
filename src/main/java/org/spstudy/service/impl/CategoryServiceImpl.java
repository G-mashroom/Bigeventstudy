package org.spstudy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spstudy.mapper.CategoryMapper;
import org.spstudy.pojo.Category;
import org.spstudy.service.CategoryService;
import org.spstudy.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // 补充属性值 传入数据没有创建时间和更新时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map  = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);

    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        return categoryMapper.list(id);
    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }

    @Override
    public void update(Category category) {
        // 补充属性值
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
