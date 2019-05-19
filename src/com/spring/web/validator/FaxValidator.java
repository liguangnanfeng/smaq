package com.spring.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Title: FaxValidator
 * @Description: 传真格式验证
 * @author FL
 * @date 2016年1月13日 下午6:12:47
 * @version V1.0
 */
public class FaxValidator implements ConstraintValidator<Fax, String> {
    private String faxReg = "(^[0-9]{3,4}\\-[0-9]{7,8}\\-[0-9]{3,4}$)|(^[0-9]{3,4}\\-[0-9]{7,8}$)|(^[0-9]{7,8}\\-[0-9]{3,4}$)|(^[0-9]{7,15}$)";
    private Pattern pattern = Pattern.compile(faxReg);

    /**
     * (非 Javadoc)
     * 
     * @param arg0
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(Fax fax) {
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
        return pattern.matcher(string).matches();
    }
}
