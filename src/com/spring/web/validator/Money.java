package com.spring.web.validator;

import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Title: Money
 * @Description: 金额格式验证
 * @author FL
 * @date 2016年1月13日 下午6:14:35
 * @version V1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyValidator.class)
@Documented
public @interface Money {
    String message() default "{不是金额形式}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
