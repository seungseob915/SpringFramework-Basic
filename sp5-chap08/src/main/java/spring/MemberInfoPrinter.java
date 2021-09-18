package spring;

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

    // Setter를 통한 DI 시 사용
    public void setMemberDao(MemberDao memberDao){
        this.memDao=memberDao;
    }

    // Setter를 통한 DI 시 사용
    public void setPrinter(MemberPrinter printer){
        this.printer=printer;
    }
}
