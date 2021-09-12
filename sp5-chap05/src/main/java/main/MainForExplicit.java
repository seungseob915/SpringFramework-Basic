package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxWithExplicit;
import spring.MemberDao;

public class MainForExplicit {

	private static AbstractApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(
				AppCtxWithExplicit.class);

		MemberDao memberDao=ctx.getBean(MemberDao.class);	// memberDao가 AppCtx 내 수동 빈주입 및 별도 클래스에 @Component 붙어있는 경우 -> 이름이 같으면 수동이 우선 / 이름이 다르면, 아래처럼 명시하여 빈 주입
		//MemberDao memberDao1=ctx.getBean("memberDao", MemberDao.class);
		//MemberDao memberDao2=ctx.getBean("memberDao2", MemberDao.class);

		ctx.close();
	}

}