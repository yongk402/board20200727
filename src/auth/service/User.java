package auth.service;

// LoginService에서 사용자 입력 아이디와 암호 일치하면
// 로그인한 사용자 정보 담을 User 객체 리턴
public class User {
	private String id;
	private String name;
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;	}
	public String getName() {
		return name;	}
	
}
