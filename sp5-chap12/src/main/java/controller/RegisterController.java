package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(MemberRegisterService memberRegisterService){
        this.memberRegisterService=memberRegisterService;
    }

    @RequestMapping("/register/step1")
    public String handleStep1(){
        return "register/step1";
    }

    // 요청 파라미터 접근 방식
    // 방법 1. HttpServletRequest I/F 이용
    /*
    @PostMapping("/register/step2")
    public String handleStep2(HttpServletRequest request){
        String agreeParam=request.getParameter("agree");

        if(agreeParam==null || !agreeParam.equals("true")){
            return "register/step1";
        }
        return "register/step2";
    }
    */

    // 방법 2. @RequestParam 이용
    @PostMapping("/register/step2")
    //@RequestMapping(value = "/register/step2", method = RequestMethod.POST)
    public String handleStep2(@RequestParam(value="agree", defaultValue = "false") Boolean agree, Model model){
        if(!agree){
            return "redirect:/register/step1";
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/step2";
    }
    
    // handleStep2에 대한 리다이렉트 처리
    @GetMapping("/register/step2")
    //@RequestMapping(value = "/register/step2", method = RequestMethod.GET)
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }

    @PostMapping("/register/step3")
    // 글로벌 Validator로 인해 생략
    //public String handleStep3(RegisterRequest regReq, Errors errors){
    //    new RegisterRequestValidator().validate(regReq, errors);    // 커맨드 객체의 값이 올바른지 검사하고, 결과를 Errors 객체에 담음.  Errors 객체는 커맨드 객체의 특정 프로퍼티 값을 구할 수 있는  getFieldValue() 메소드를 제공.
    public String handleStep3(@Valid RegisterRequest regReq, Errors errors){
        if(errors.hasErrors())
            return "register/step2";

        try{
            memberRegisterService.regist(regReq);
            return "register/step3";
        }
        catch(DuplicateMemberException ex){
            errors.rejectValue("email","duplicate");
            return "register/step2";
        }
    }

    // 컨트롤러 범위 Validator 설정
    // 어떤 Validator가 커맨드 객체를 검증할 지 결정. 요청 처리 메소드가 실행되기 전에 매번 실행됨. -> handleStep1~3가 실행되기 전 매번 호출되서 WebDataBinder를 초기화 함.
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new RegisterRequestValidator());    // setValidator() 메소드를 통해 컨트롤러 범위에 적용할 Validator 설정
    }
}
