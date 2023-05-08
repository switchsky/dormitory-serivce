package com.switchsky.exception_advice;
import com.switchsky.utils.ResultUtils;
import com.switchsky.utils.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
     /**
     * 自定义业务异常拦截
     * BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultVo bussinessexception(BusinessException e) {

        return ResultUtils.error(e.getMessage(),e.getCode(),e.getMessage());
    }
}