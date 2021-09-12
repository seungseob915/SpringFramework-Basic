package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtxWithExplicit {

	@Bean
	//public MemberDao memberDao() {	//빈 이름이 동일하여 @Component MemberDao 클래스보다 우선순위 (수동등록)
	public MemberDao memberDao2() {		//빈 이름은 다르나 빈 타입이 동일하여, @Component MemberDao 클래스와 구분될 수 있도록, @Qualifier를 통해 구분 주입해야함.
		MemberDao memberDao = new MemberDao();
		System.out.println("explicit : " + memberDao);
		return memberDao;
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
