package com.soojoe.common.web.controller.json;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;
import com.soojoe.common.dto.ResponseDTO;
import com.soojoe.common.exception.AbstactCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * 统一处理Controller
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/27 21:02
 */
@ControllerAdvice
public class CommonControllerAdvice {

  private static final Logger log = LoggerFactory.getLogger(CommonControllerAdvice.class);

  @ExceptionHandler(AbstactCommonException.class)
  @ResponseBody
  public ResponseDTO handle(AbstactCommonException exception) {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    log.error(uuid, exception);
    return new ResponseDTO(exception.getCode(), exception.getAction(), exception.getMessage(),
        uuid);
  }

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public ResponseDTO handle(Throwable throwable) {
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    log.error(uuid, throwable);
    return new ResponseDTO(ResponseCodeConstants.SERVER_ERROR, ResponseActionConstants.ALERT,
        throwable.getMessage(), uuid);
  }
}
