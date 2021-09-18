package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.ChangePasswordService;
import spring.MemberDao;

@Configuration
@EnableTransactionManagement        // @Transactional이 붙은 메서드를 트랜잭션 범위내에서 실행하는 기능 활성화
public class AppCtx {

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource ds= new DataSource();

        ds.setDriverClassName("com.mysql.jdbc.Driver");                             //  JDBC 드라이버 클래스 지정
        ds.setUrl("jdbc:mysql://localhost:3306/sp5basic?characterEncoding=utf8");        //  URL 지정
        ds.setUsername("spring5");                                                  //  DB 계정 ID
        ds.setPassword("spring5");                                                  //  DB 계정 Password
        ds.setInitialSize(2);                                                       //  커넥션 풀 초기화 간, 생성할 초기 커넥션 갯수 (default는 10)
        ds.setMaxActive(10);                                                        //  커넥션 풀에서 가져올 수 있는 최대 커넥션 갯수 지정 (default는 100)
        
        ds.setTestWhileIdle(true);                                                  // 유휴 커넥션 검사
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3);                            // 최소 유휴 시간 3분
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10);                             // 10초 주기로 커넥션 검사

        return ds;
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        ChangePasswordService pwdSvc=new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }

    // PlatformTransactionManager : 스프링이 제공하는 트랜잭션 매니저 I/F
    // JDBC에서는 DataSourceTransactionManager를 PlatformTransactionManager로 사용
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm=new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}
