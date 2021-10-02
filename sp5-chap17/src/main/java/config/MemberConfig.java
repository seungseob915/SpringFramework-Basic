package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class MemberConfig {

    @Autowired
    private DataSource dataSource;

//    @Bean(destroyMethod = "close")
//    public DataSource dataSource(){
//        DataSource ds=new DataSource();
//
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/sp5basic?characterEncoding=utf8");
//        ds.setUsername("spring5");
//        ds.setPassword("spring5");
//        ds.setInitialSize(2);
//        ds.setMaxActive(10);
//
//        ds.setTestWhileIdle(true);
//        ds.setMinEvictableIdleTimeMillis(60*1000*3);
//        ds.setTimeBetweenEvictionRunsMillis(10*1000);
//
//        return ds;
//    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm=new DataSourceTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource);
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
    public AuthService authService(){
        AuthService authService=new AuthService();
        authService.setMemberDao(memberDao());
        return authService;
    }

}
