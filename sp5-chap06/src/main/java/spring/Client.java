package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {

    private String host;

    public void setHost(String host){
        this.host=host;
    }

    @Override   // InitializingBean(빈 초기화) 인터페이스 구현 (메소드 오버라이드)
    public void afterPropertiesSet() throws Exception{
        System.out.println("Client.afterPropertiesSet() 실행");
    }

    public void send(){
        System.out.println("Client.send() to " + host);
    }

    @Override   // DisposableBean(빈 소멸) 인터페이스 메소드 구현 (메소드 오버라이드)
    public void destroy() throws Exception{
        System.out.println("Client.destroy() 실행");
    }

}
