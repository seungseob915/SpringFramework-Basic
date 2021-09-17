package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Order(1)   // Aspect 적용 순서 (낮을 수록 우선순위 높음)
public class CacheAspect {

    private Map<Long, Object> cache=new HashMap<>();

    @Pointcut("execution(public * factorial*(long))")
    private void cacheTarget(){
    }

    //@Around("cacheTarget()")
    //@Around("aspect.ExeTimeAspect.publicTarget()")  // 여러개의 Advice가 동일한 Pointcut을 사용할 때, 타 Advice의 Pointcut 메소드(public)를 공유 가능
    @Around("aspect.CommonPointcut.commonTarget()")  // 여러개의 Advice가 동일한 Pointcut을 사용할 때, 공용 Pointcut 클래스를 별도로 생성하여 사용 가능
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num=(Long)joinPoint.getArgs()[0];

        if(cache.containsKey(num)){
            System.out.printf("CacheAspect : Cache에서 구함 [%d]\n", num);
            return cache.get(num);
        }

        Object result=joinPoint.proceed();
        cache.put(num,result);
        System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
        return result;
    }
}
