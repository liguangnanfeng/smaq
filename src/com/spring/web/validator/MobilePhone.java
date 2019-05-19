package com.spring.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Title: MobilePhone
 * @Description: 手机号码验证
 * @author FL
 * @date 2016年1月13日 下午6:13:18
 * @version V1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobilePhoneValidator.class)
@Documented
public @interface MobilePhone {
    String message() default "{必须为手机号码}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
