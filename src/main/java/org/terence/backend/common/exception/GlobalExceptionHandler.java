package org.terence.backend.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.terence.backend.common.constant.ExceptionConstant;
import org.terence.backend.service.vo.base.BaseResponse;

/**
 * @author terence
 * @since 2019/2/19 11:24
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(BaseException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(BaseException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ExceptionConstant.OTHER_CODE, ex.getMessage());
    }
}
