package com.example.interfaceextension.sample;

import com.example.interfaceextension.sample.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author huangguang
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
public class LocalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        log.error(e.getMessage(), e);
        return new R(e.getMessage(), null, 500);
    }

}
