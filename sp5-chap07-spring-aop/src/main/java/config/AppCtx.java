package config;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import factorial.Calculator;
import factorial.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)    // proxyTargetClass = true : 인터페이스가 아닌 자바 클래스를 상속받아 프록시 생성. (없을 때에는, Calculator 인터페이스를 상속받은 프록시 객체가 생성되었음.)
public class AppCtx {

    @Bean
    public CacheAspect cacheAspect(){
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect(){
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator(){
        return new RecCalculator();
    }
}
