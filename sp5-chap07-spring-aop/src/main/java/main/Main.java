package main;


import config.AppCtx;
import factorial.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args)  {
        AnnotationConfigApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal=ctx.getBean(Calculator.class);
        long fiveFactor=cal.factorial(5);
        System.out.println("cal.factorial(5)= "+fiveFactor);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
