package com.cx.lost_found.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    //实现校验方法
    public ValidationResult validator(Object bean){
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationsSet = validator.validate(bean);
        if (constraintViolationsSet.size() > 0){
            validationResult.setHasErrors(true);
            constraintViolationsSet.forEach(objectConstraintViolation -> {
                String errMsg = objectConstraintViolation.getMessage();
                String propertyName = objectConstraintViolation.getPropertyPath().toString();
                validationResult.getErrMsgMap().put(propertyName, errMsg);
            });
        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
