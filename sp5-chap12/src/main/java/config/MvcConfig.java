package config;

import controller.RegisterRequestValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/WEB-INF/view/",".jsp");
    }

    // 컨트롤러 구현 없이, 요청 경로와 뷰 이름을 매핑(경로와 뷰이름만 연결하는 간단한 컨트롤러의 경우)
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/main").setViewName("main");
    }

    // 메세지 출력을 위한 빈 등록. 항상 빈 이름은 messageSource여야 함.
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource ms=new ResourceBundleMessageSource();
        ms.setBasenames("message.label");       // resources - message - label 프로퍼티 로드.  setBasenames 내 파라미터는 가변 인자로 여러 프로퍼티 파일 로드시 콤마(,)로 구분
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    // 글로벌 범위 Validator 설정 : WebMvcConfigurer에 정의된 Validator 메소드 구현
    @Override
    public Validator getValidator(){
        return new RegisterRequestValidator();
    }
}
