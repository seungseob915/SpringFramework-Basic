package main;

import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForTransaction {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=
                new AnnotationConfigApplicationContext(AppCtx.class);

        ChangePasswordService cps=
                ctx.getBean(ChangePasswordService.class);

        try{
            cps.changePassword("seungseob915@gmail.com", "1234", "1111");
            System.out.println("암호 변경 완료");
        }catch (MemberNotFoundException e){
            System.out.println("회원 데이터 미존재");
        }catch (WrongIdPasswordException e){
            System.out.println("기존 암호 미일치");
        }

        ctx.close();

    }
}
