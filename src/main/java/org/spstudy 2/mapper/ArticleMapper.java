package org.spstudy.mapper;


import org.apache.ibatis.annotations.*;
import org.spstudy.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //使用映射配置文件写SQL

    List<Article> list(Integer userId, Integer categoryId, String state);
    @Select("select * from article where id = #{id}")
    Article detail(Integer id);

    @Update("UPDATE article " +
            "SET title=#{title}, " +
            "    content=#{content}, " +
            "    cover_img=#{coverImg}, " +
            "    state=#{state}, " +
            "    category_id=#{categoryId}, " +
            "    update_time=#{updateTime} " +
            "WHERE id=#{id}")
    void update(Article article);
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
