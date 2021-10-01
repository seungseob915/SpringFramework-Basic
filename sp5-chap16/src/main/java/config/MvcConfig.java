package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import interceptor.AuthCheckInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
@EnableWebMvc   // OptionalValidatorFactoryBean을 글로벌 범위 Validator로 등록. 아래에 별도 글로벌 Validator 설정이 있으면, OptionalValidatorFactoryBean은 채택되지 않음.
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
    
    // HandlerInterceptor 적용을 위한 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**").excludePathPatterns("/edit/help/**");
    }

    @Bean
    public AuthCheckInterceptor authCheckInterceptor(){
        return new AuthCheckInterceptor();
    }

    // 자바 객체가 HTTP 응답으로 변환될 때, 데이터 변환을 담당
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*
        // 이미 등록된 Converter 중, Jackson이 날짜 형식을 출력할 때, 유닉스 타임 스탬프로 출력하는 기존 기능을 비활성화
        ObjectMapper objectMapper= Jackson2ObjectMapperBuilder
                .json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        
        // 새로운 날짜 형식(ISO-8601)을 위한 설정 등록
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
        */

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper= Jackson2ObjectMapperBuilder
                .json()
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                //.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter))    // Json -> LocalDateTime 타입으로 변환 시, 사용할 패턴 지정.
                //.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .build();
    }
}
