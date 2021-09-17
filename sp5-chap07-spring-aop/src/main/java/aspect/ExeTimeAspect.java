package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(2)   // Aspect 적용 순서 (낮을 수록 우선순위 높음)
public class ExeTimeAspect {

/*
    @Pointcut("execution(public * factorial..*(..))")
    private void publicTarget(){
    }
*/

    // 다수의 Advice가 동일한 Pointcut을 사용할 때, 접근제어자 부분을 public으로 변경하여 각 Advice 내 @Around 어노테이션에서 위 경로 패키지의 메서드를 사용하면 됨.
    @Pointcut("execution(public * factorial..*(..))")
    public void publicTarget(){
    }

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.nanoTime();

        try {
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finish=System.nanoTime();
            Signature sig=joinPoint.getSignature();
            System.out.printf("%s.%s.(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    (finish-start));
        }
    }
}
