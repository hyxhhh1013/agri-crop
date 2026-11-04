package com.hyx.agri.exception;

import com.hyx.agri.common.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValid(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError() == null
                ? "参数校验失败"
                : ex.getBindingResult().getFieldError().getDefaultMessage();
        log.error("参数校验失败: {}", msg, ex);
        return Result.fail(400, msg);
    }

    @ExceptionHandler(BindException.class)
    public Result<Void> handleBind(BindException ex) {
        String msg = ex.getFieldError() == null ? "参数校验失败" : ex.getFieldError().getDefaultMessage();
        log.error("参数绑定失败: {}", msg, ex);
        return Result.fail(400, msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraint(ConstraintViolationException ex) {
        log.error("约束校验失败", ex);
        return Result.fail(400, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusiness(BusinessException ex) {
        log.error("业务异常: {}", ex.getMessage(), ex);
        return Result.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleUnknown(Exception ex) {
        log.error("未知异常", ex);
        return Result.fail(500, "系统异常，请稍后重试");
    }
}
