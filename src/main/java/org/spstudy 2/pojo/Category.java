package org.spstudy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    // id属于Update 分组，则不属于Defaut分组
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
//    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryName;//分类名称
//    @NotEmpty(groups = {Add.class,Update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
    //如果说某个校验项没有指定分组，默认属于Default分组
    //分组之间可以继承 AextendsB 那么A中拥有B中的所有校验项

    // 增加和更新校验分组 Add - Update
    public interface Add extends Default {

    }
    public interface Update extends Default {

    }
}
