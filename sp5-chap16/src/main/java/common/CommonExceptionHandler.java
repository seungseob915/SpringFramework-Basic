package common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("spring") // spring 및 하위 패키지 내, 컨트롤러에 대한 직접 예외처리
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(){
        return "error/commonException";
    }

}
