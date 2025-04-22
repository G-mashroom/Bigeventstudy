package org.spstudy.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.spstudy.validation.StateValidation;

import java.lang.annotation.*;
import java.util.List;

@Documented //元注解
@Target({ElementType.FIELD})
// 元注解 包含可以硬硬的类型 分别是 方法 字段 注解 构造器 参数 类型使用
//ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE
@Retention(RetentionPolicy.RUNTIME) // 运行时注解 在什么阶段保留
@Constraint(
        validatedBy = { StateValidation.class } //指定校验规则提供的类
)


public @interface State {
    // 这三个属性分别用来 指定错误信息 错误信息提示的key 错误信息提示的key
    String message() default "{State参数的值只能是已发布或草稿}";
    // 分组
    Class<?>[] groups() default {};
    // 负载 获取State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
