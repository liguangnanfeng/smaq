package com.spring.web.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @Title: Email
 * @Description: 邮箱格式验证
 * @author FL
 * @date 2016年1月13日 下午6:09:30
 * @version V1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {
    String message() default "{邮箱格式错误!}";

    Class<?>[] groups() default {};
}
