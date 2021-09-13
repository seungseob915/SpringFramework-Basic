package main;


import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spring.Client;
import spring.Client2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppCtx.class);

        Client client1=ctx.getBean(Client.class);       // 프로토타입
        Client client2=ctx.getBean(Client.class);       // 프로토타입
        Client2 client3=ctx.getBean(Client2.class);      // 싱글톤

        //client1.send();

        System.out.println("----- 컨테이너 종료 -----");
        ctx.close();
        client1.destroy();  // 프로토타입은 소멸처리를 직접 해야함. (컨테이너 종료시에는 싱글톤만 소멸 처리됨.)
        client2.destroy();  // 프로토타입은 소멸처리를 직접 해야함. (컨테이너 종료시에는 싱글톤만 소멸 처리됨.)

    }
}
