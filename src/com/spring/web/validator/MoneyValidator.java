package com.spring.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Title: MoneyValidator
 * @Description: 金额格式验证
 * @author FL
 * @date 2016年1月13日 下午6:15:09
 * @version V1.0
 */
public class MoneyValidator implements ConstraintValidator<Money, Double> {
    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";// 表示金额的正则表达式
    private Pattern moneyPattern = Pattern.compile(moneyReg);

    public void initialize(Money money) {
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
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        return moneyPattern.matcher(value.toString()).matches();
    }
}
