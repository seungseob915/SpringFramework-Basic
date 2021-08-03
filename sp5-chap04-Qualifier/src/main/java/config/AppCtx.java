package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    @Qualifier("memberDao2")    // 자동주입 빈 타입(MemberDao) 가 2개 이상인 경우 => @Qualifier 어노테이션을 통해 구분. 생략하게되도 빈설정 메소드명인 memberDao2로 한정자가 설정됨.
    public MemberDao memberDao2(){
        return new MemberDao();
    }

    @Bean
    @Qualifier("memberDaoTest")     // 자동주입 빈 타입(MemberDao) 가 2개 이상인 경우 => @Qualifier 어노테이션을 통해 구분. 한정자는 memberDaoTest
    public MemberDao memberDao3(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao2());
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        return new ChangePasswordService();
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao2(), memberPrinter());
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){
        return new MemberInfoPrinter();
    }

    //기본 값 설정
    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter=new VersionPrinter();

        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
