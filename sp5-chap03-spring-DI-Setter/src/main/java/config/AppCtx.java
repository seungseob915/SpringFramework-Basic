package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {

    @Bean
    public MemberDao memberDao(){
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService pwdSvc=new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    //생성자를 통한 DI
    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), memberPrinter());
    }

    //Setter를 통한 DI
    @Bean
    public MemberInfoPrinter infoPrinter(){
        MemberInfoPrinter infoPrinter=new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
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
