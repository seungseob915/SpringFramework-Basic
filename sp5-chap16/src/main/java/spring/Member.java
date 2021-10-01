package spring;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Member {
    private Long id;
    private String email;
    @JsonIgnore         // 비밀번호 속성은 RestController에서 제외하고 응답.
    private String password;
    private String name;
    //@JsonFormat(shape = JsonFormat.Shape.STRING)    // ISO-8601형식으로 변환. 문자열로 표시.
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // 원하는 패턴으로 포맷팅 가능. HttpMessageConverter 관련 메소드 오버라이딩을 통해 날짜 포맷에 대해 공통으로 일괄처리하여 생략.
    private LocalDateTime registerDateTime;

    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public void changePassword(String oldPassword, String newPassword){
        if(!password.equals(oldPassword))
            throw new WrongIdPasswordException();
        this.password=newPassword;
    }

    public boolean matchPassword(String password){
        return this.password.equals(password);
    }
}
