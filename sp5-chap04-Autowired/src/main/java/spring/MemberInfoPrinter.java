package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {

    private MemberDao memDao;
    private MemberPrinter printer;

    public void printMemberInfo(String email){
        Member member=memDao.selectByEmail(email);

        if(member==null){
            System.out.println("해당 유저 정보 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }

    // 세터 메서드에 자동주입 어노테이션 적용
    @Autowired
    public void setMemberDao(MemberDao memberDao){
        this.memDao=memberDao;
    }

    // 세터 메서드에 자동주입 어노테이션 적용
    @Autowired
    public void setPrinter(MemberPrinter printer){
        this.printer=printer;
    }
}
