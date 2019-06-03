package com.spring.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Title: MobilePhoneValidator
 * @Description: 手机号码验证
 * @author FL
 * @date 2016年1月13日 下午6:13:58
 * @version V1.0
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone, String> {
    private String mobileReg = "^1[3|4|5|8][0-9]\\d{4,8}$";// 表示手机号码的正则表达式
    private Pattern mobilePattern = Pattern.compile(mobileReg);

    /**
     * (非 Javadoc)
     * 
     * @param arg0
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(MobilePhone arg0) {
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
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (value == null)
            return true;
        return mobilePattern.matcher(value).matches();
    }
}
