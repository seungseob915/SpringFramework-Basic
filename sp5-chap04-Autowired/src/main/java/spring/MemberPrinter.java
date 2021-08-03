package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if(dateTimeFormatter==null){
            System.out.printf("회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
        }else{
            System.out.printf("회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    // ##### 자동주입 필수 여부 지정 #####
    // 방법 1. required=false
    @Autowired(required=false)
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    // 방법 2. @Nullable
/*    @Autowired
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }*/

    // 방법 3. Java8의 Optional 사용
/*    @Autowired
    public void setDateTimeFormatter(Optional<DateTimeFormatter> dateTimeFormatter) {
        if(dateTimeFormatter.isPresent()){
            this.dateTimeFormatter = dateTimeFormatter.get();
        }else{
            this.dateTimeFormatter=null;
        }
    }*/

}
