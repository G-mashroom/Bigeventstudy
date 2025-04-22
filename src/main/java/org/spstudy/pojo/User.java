package org.spstudy.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
//import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;
//lombok 在编译阶段自动为实体生成setter getter toString方法
// 在pom文件中引入依赖 在实体类上添加注解
@Data
public class User {
    // 设置参数格式
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore // 当前对象转化为json字符串时忽略掉password
    private String password;//密码

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}")
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间  下划线命名和驼峰命名存在冲突 需要配置一下
    private LocalDateTime updateTime;//更新时间
}
