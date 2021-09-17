package main;

import config.AppCtx;
import factorial.Calculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspectWithCache {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal=ctx.getBean(Calculator.class);
        System.out.println(cal.factorial(7));
        System.out.println(cal.factorial(7));
        System.out.println(cal.factorial(6));
        System.out.println(cal.factorial(6));

        ctx.close();
    }

}
