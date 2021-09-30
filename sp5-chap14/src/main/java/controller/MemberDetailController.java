package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.Member;
import spring.MemberDao;
import spring.MemberNotFoundException;

@Controller
public class MemberDetailController {

    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id") Long memId, Model model){
        Member member=memberDao.selectById(memId);

        if(member==null){
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(){
        return "member/invalidId";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    // 로그 확인하고 싶을 시, 메소드 파라미터에 변수를 할당하여 메소드 블록 내에서 로그 처리 가능.
    public String handleNotFoundException(MemberNotFoundException ex){
        return "member/noMember";
    }
}
