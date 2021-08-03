package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class MemberListPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter){
        this.memberDao=memberDao;
        this.printer=memberPrinter;
    }

    public void printAll(){
        Collection<Member> members=memberDao.selectAll();
        members.forEach(m->printer.print(m));
    }

    @Autowired
    @Qualifier("memberDao2")
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    public void setMemberPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}
