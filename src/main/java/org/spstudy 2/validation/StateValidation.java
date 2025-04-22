package org.spstudy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.spstudy.anno.State;

public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        /**
         * @param value 将来要校验的数据
         * @param context 校验的上下文
         *  @return              返回true表示校验通过，false表示校验不通过
         */
        // 提供校验规则
        if(value == null){
            return false;
        }

        if ("已发布".equals(value) || "草稿".equals(value)){
            return true;
        }
        return false;
    }
}
