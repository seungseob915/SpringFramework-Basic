package main;

import config.DsDevConfig;
import config.DsRealConfig;
import config.MemberConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Member;
import spring.MemberDao;

import java.util.List;

// 프로필 설정 방법 2
public class MainSystemProperty {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "real");        // 프로퍼티 설정 : spring.profiles.active 시스템 프로퍼티 값을 real 선택
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                MemberConfig.class, DsDevConfig.class, DsRealConfig.class);

        MemberDao dao = context.getBean(MemberDao.class);
        List<Member> members = dao.selectAll();
        members.forEach(m -> System.out.println(m.getEmail()));

        context.close();
    }
}
