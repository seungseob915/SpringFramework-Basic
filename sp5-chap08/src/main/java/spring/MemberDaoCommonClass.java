package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MemberDaoCommonClass {

    // JdbcTemplate 사용
    private JdbcTemplate jdbcTemplate;

    // 생성자를 통한 Datasource 주입
    public MemberDaoCommonClass(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER where EMAIL=?",
                new MemberRowMapper(),  // 기존 MemberDao에서 MemberRowMapper 공통 기능 클래스 구현으로 코드 간소화
                email);
        
        return results.isEmpty() ? null : results.get(0);
    }

}
