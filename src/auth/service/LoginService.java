package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

// MemberDao 이용해서 아이디에 해당하는 회원 데이터 존재하는지 확인
// 암호불일치 혹은 데이터 없을시 LoginFailException 발생
public class LoginService {
	private MemberDao memberDao = new MemberDao();
	
	//id와 암호 파라미터 전달 받음
	public User login(String id, String password) {
		// try-with-resources 검색하면 예제 많이 나온다.
		try ( Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if(member == null) {
				throw new LoginFailException();
			}
			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			} 
			
			return new User(member.getId(), member.getName());
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
