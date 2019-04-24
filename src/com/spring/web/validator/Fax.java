package com.spring.web.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Title: Fax
 * @Description: 传真格式验证
 * @author FL
 * @date 2016年1月13日 下午6:12:13
 * @version V1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FaxValidator.class)
public @interface Fax {
    String message() default "{传真格式错误!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
