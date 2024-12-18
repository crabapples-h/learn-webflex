package cn.crabapples.learnwebflex.base;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * TODO 基础Controller，其他controller请继承此类
 *
 * @author Mr.He
 * 2019/9/21 18:28
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class BaseController<T> {
    @Resource
    protected T service;

    @Resource
    private Validator validator;

    /**
     * 属性校验的方法
     *
     * @param object 需要验证的对象
     */
    protected final void validator(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            throw new ApplicationException(constraintViolation.getMessageTemplate());
        }
    }
}
