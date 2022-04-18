package cn.com.wudskq.expection;

import cn.com.wudskq.model.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class TopException {
 
    private Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
 
        if(e instanceof MyException){
            logger.error("业务日志",e);
            MyException myException = (MyException) e;
            return  Result.error(myException.getCode(),myException.getMessage());
        }else if(e instanceof AccessDeniedException){
            return  Result.error(403,"访问权限不足");
        }
 
        logger.error("系统日志",e);
        return Result.error(1000,"业务繁忙");
    }
}

