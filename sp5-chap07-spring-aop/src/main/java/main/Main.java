package main;


import config.AppCtx;
import factorial.Calculator;
import factorial.RecCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args)  {
        AnnotationConfigApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppCtx.class);

        //Calculator cal=ctx.getBean("calculator", Calculator.class);
        RecCalculator cal=ctx.getBean("calculator", RecCalculator.class);     // 설정 클래스 내, proxyTargetClass = true 가 있을 때만 사용가능. 없는 경우,RecCalculator로 타입변환이 안 이뤄짐.

        long fiveFactor=cal.factorial(5);
        System.out.println("cal.factorial(5)= "+fiveFactor);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
