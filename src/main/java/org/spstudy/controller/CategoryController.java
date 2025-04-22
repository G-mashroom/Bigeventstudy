package org.spstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.spstudy.pojo.Category;
import org.spstudy.pojo.Result;
import org.spstudy.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>>  list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);

    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }


}
