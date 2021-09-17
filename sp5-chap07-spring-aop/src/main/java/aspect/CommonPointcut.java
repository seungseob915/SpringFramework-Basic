package aspect;

import org.aspectj.lang.annotation.Pointcut;

// 공동 Pointcut 관리 - Bean 등록할 필요 없음
public class CommonPointcut {

    @Pointcut("execution(public * factorial..*(..))")
    public void commonTarget(){
    }

}
