package model;

import java.io.Serializable;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
@SuppressWarnings("serial")
public class User implements Serializable {	
	private String userId;
	private String password;
	private String email;
	private String phone;
	private String name;
	private String userType;
	
	public User(String name) {
		super();
		this.name = name;
	}

	public User(String userId, String password, String email, String phone, String name, String userType) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.setUserType(userType);
	}

	public User(String userId, String password, String email, String phone, String name) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.name = name;
	}

	public User() { }		// 기본 생성자
	
	public User(String userId, String password, String email) {
		this.userId = userId;
		this.password = password;
		this.email = email;
	}

	
	/*public void update(User updateUser) {
        this.password = updateUser.password;
        this.email = updateUser.email;
    }*/
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + "]";
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}	
}
