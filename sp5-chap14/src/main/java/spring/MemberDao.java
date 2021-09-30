package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDao {
    
    // JdbcTemplate 사용
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Member> memberRowMapper=
            new RowMapper<Member>(){
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member=new Member(
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
            );

            member.setId(rs.getLong("ID"));
            return member;
        }
    };

    
    // 생성자를 통한 Datasource 주입
    public MemberDao(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER where EMAIL=?",
                memberRowMapper,
                email);
        
        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(final Member member){
        KeyHolder keyHolder=new GeneratedKeyHolder();   // 자동 생성된 키값을 구하기 위해 keyHolder 객체 생성

        jdbcTemplate.update(new PreparedStatementCreator() {    // PreparedStatement 및 KeyHolder 객체를 파라미터로 가짐
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt=connection.prepareStatement(
                "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
                        "VALUES (?, ?, ?, ?)",
                new String[]{"ID"});    // 자동으로 생성된(Auto Increment 속성) 키 값(Member의 ID)을 구할 수 있음.

                pstmt.setString(1,member.getEmail());
                pstmt.setString(2,member.getPassword());
                pstmt.setString(3,member.getName());
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                return pstmt;
            }
        }, keyHolder);  // PreparedStatement 실행 후, 자동 생성된 키값을 keyHolder에 보관

        Number keyValue=keyHolder.getKey(); 
        member.setId(keyValue.longValue());         // long 타입으로 변환
    }

    public void update(Member member){
        jdbcTemplate.update(
                "update MEMBER set NAME=?, PASSWORD=? where EMAIL=?",
                member.getName(), member.getPassword(), member.getEmail()
        );
    }

    public List<Member> selectAll(){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER",
                memberRowMapper);
        return results;
    }

    public int count(){
        Integer count=jdbcTemplate.queryForObject(
                "select count(*) from MEMBER",
                Integer.class);

        return count;
    }
    
    // 특정 기간 사이에 가입한 회원 검색
    public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER where REGDATE between ? and ? " +
                        "order by REGDATE desc",
                memberRowMapper,
                from, to);
        return results;
    }

    public Member selectById(Long memId){
        List<Member> results=jdbcTemplate.query(
                "select * from MEMBER where ID = ?",
                memberRowMapper,
                memId);
        return results.isEmpty() ? null : results.get(0);
    }
}
