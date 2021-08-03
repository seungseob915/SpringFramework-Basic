package config;

import org.springframework.beans.factory.annotation.Qualifier;
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
//        ChangePasswordService pwdSvc=new ChangePasswordService();
//        pwdSvc.setMemberDao(memberDao());             // ChangePasswordService 클래스에서 @AutuWired(자동 주입) 처리하여 필요 없음.
        return new ChangePasswordService();
    }

    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter(){
        return new MemberListPrinter(memberDao(), memberPrinter());
    }

    @Bean
    public MemberInfoPrinter infoPrinter(){
//        MemberInfoPrinter infoPrinter=new MemberInfoPrinter();
//        infoPrinter.setMemberDao(memberDao());        // memberInfoPrinter 클래스의 Setter 메소드에 @Autowired 적용
//        infoPrinter.setPrinter(memberPrinter());      // memberInfoPrinter 클래스의 Setter 메소드에 @Autowired 적용
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
