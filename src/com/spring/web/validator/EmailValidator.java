package com.spring.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Title: EmailValidator
 * @Description: 邮箱格式验证
 * @author FL
 * @date 2016年1月13日 下午6:11:00
 * @version V1.0
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
    private String emailReg = "^[a-zA-Z0-9_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";
    private Pattern emialPattern = Pattern.compile(emailReg);

    /**
     * (非 Javadoc)
     * 
     * @param arg0
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(Email arg0) {
    }

    /**
     * (非 Javadoc)
     *
     * @param arg0
     * @param arg1
     * @return
     * @see javax.validation.ConstraintValidator#isValid(Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(String string, ConstraintValidatorContext context) {
        if (string == null) {
            return true;
        }
        return emialPattern.matcher(string).matches();
    }
}
