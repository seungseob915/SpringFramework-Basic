package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    // MvcConfig의 WebMvcConfigurer I/F 내 addViewControllers 메소드의 오버라이딩을 통해 생략 가능
    /*
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    */

}
