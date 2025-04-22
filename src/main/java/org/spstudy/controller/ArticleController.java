package org.spstudy.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.spstudy.pojo.Article;
import org.spstudy.pojo.PageBean;
import org.spstudy.pojo.Result;
import org.spstudy.service.ArticleService;
import org.spstudy.utils.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){ //实体参数校验要加Validated才能生效
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam (required = false) Integer categoryId,
            @RequestParam (required = false) String state
    ){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);

    }
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article article = articleService.detail(id);
        return Result.success(article);
    }
    @PutMapping
    public Result update(@RequestBody @Validated Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
