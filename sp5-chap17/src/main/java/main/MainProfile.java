package main;

import config.DsDevConfig;
import config.DsRealConfig;
import config.MemberConfig;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.Member;
import spring.MemberDao;

import java.util.List;

// 프로필 설정 방법 1
public class MainProfile {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");      //Profile 설정
        context.register(MemberConfig.class, DsDevConfig.class, DsRealConfig.class);      
        //context.register(MemberConfigWithProfile.class);
        context.refresh();

        MemberDao dao = context.getBean(MemberDao.class);
        List<Member> members = dao.selectAll();
        members.forEach(m -> System.out.println(m.getEmail()));

        context.close();
        }
}
