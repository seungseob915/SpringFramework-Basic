package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

public class MemberRegisterService {
    @Autowired
    @Qualifier("memberDao2")
    private MemberDao memberDao;

    public MemberRegisterService() {
    }

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req){
        Member member=memberDao.selectByEmail(req.getEmail());
        if(member!=null){
            throw new DuplicateMemberException("Duplicate email"+ req.getEmail());
        }
        Member newMember=new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
