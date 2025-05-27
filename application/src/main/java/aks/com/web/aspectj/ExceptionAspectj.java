package aks.com.web.aspectj;

import cn.dev33.satoken.exception.NotLoginException;
import aks.com.sdk.exception.ServiceException;
import aks.com.sdk.resp.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对异常的统一返回
 * @author xxl
 * @since 2023/9/16
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAspectj {

    /**
     * 专门处理@Valid注解验证失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return RespEntity.fail("请求参数验证失败", errors);
    }

    /**
     * 处理表单绑定异常
     */
    @ExceptionHandler(BindException.class)
    public RespEntity<?> handleBindExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return RespEntity.fail("表单数据验证失败", errors);
    }

    /**
     * 处理约束违反异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RespEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (error1, error2) -> error1
                ));
        return RespEntity.fail("参数验证失败", errors);
    }

    /**
     * 捕捉spring boot容器所有的其他异常
     */
    @ExceptionHandler(Exception.class)
    public RespEntity<?> exception(Exception exception) {
        log.error("系统异常信息: ", exception);
        if (exception instanceof ServiceException com) {
            return RespEntity.fail(com.getCode(), com.getMsg());
        } else if (exception instanceof NotLoginException loginException) {
            return RespEntity.fail(loginException.getMessage());
        } else if (exception instanceof HttpRequestMethodNotSupportedException methodException) {
            return RespEntity.fail(methodException.getMessage());
        }
        return RespEntity.fail("系统异常,请稍后再试");
    }
}
