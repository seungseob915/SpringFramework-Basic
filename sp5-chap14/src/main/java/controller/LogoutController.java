package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();   // 로그아웃 시, 인증 정보 삭제
        return "redirect:/main";
    }
}
