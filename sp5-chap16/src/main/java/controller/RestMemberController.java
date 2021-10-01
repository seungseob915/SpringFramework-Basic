package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController // 요청에 대해 응답 데이터 반환.  스프링 4부터 추가됨. 그 이전에는 @ResponseBody를 사용.
public class RestMemberController {
    private MemberDao memberDao;
    private MemberRegisterService registerService;

    @GetMapping("/api/members")
    public List<Member> members(){
        return memberDao.selectAll();
    }

    @GetMapping("/api/members/{id}")
    //public ResponseEntity<Object> member(@PathVariable Long id) {
    public Member member(@PathVariable Long id) {   // 예외 처리 코드 중복 방지를 위한, @ExceptionHandler 적용
        Member member=memberDao.selectById(id);
        if(member==null){
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
            throw new MemberNotFoundException();
        }
        //return ResponseEntity.status(HttpStatus.OK).body(member);
        return member;
    }

    @PostMapping("api/members")
    //public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {    // 요청 객체 검증, 검증 실패시 400(Bad Request) 코드 응답.
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq, Errors errors) {    // 요청 객체 검증, 검증 실패시 400(Bad Request) 코드 응답.
        if(errors.hasErrors()){
            String errorCodes = errors.getAllErrors()
                    .stream()
                    .map(error -> error.getCodes()[0])
                    .collect(Collectors.joining(","));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("errorCodes = " + errorCodes));
        }
        try{
            Long newMemberId=registerService.regist(regReq);
            //response.setHeader("Location", "/api/members/"+newMemberId);
            //response.setStatus(HttpServletResponse.SC_CREATED);
            URI uri=URI.create("/api/members/"+newMemberId);

            return ResponseEntity.created(uri).build();
        }
        catch (DuplicateMemberException dupEx){
            //response.sendError(HttpServletResponse.SC_CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setRegisterService(MemberRegisterService registerService) {
        this.registerService = registerService;
    }
}
