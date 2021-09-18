package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MemberDaoLamda {

    // JdbcTemplate 사용
    private JdbcTemplate jdbcTemplate;

    // 생성자를 통한 Datasource 주입
    public MemberDaoLamda(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER where EMAIL=?",
                // MemberDao 내 동일한 코드를 람다식으로 변경
                (ResultSet rs, int rowNum) -> {
                        Member member=new Member(
                                rs.getString("EMAIL"),
                                rs.getString("PASSWORD"),
                                rs.getString("NAME"),
                                rs.getTimestamp("REGDATE").toLocalDateTime()
                        );

                        member.setId(rs.getLong("ID"));
                        return member;
                },
                email);
        
        return results.isEmpty() ? null : results.get(0);
    }

}
