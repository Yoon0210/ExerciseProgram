package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")

public class Trainer implements Serializable {
	private String trainerId;
	private String trainerPwd;
	private String email;
	private String tel;
	private String name;

	public Trainer(String trainerId, String trainerPwd, String email, String tel, String name) {
		super();
		this.trainerId = trainerId;
		this.trainerPwd = trainerPwd;
		this.email = email;
		this.tel = tel;
		this.name = name;
	}

	public Trainer(String tel, String name) {
		super();
		this.tel = tel;
		this.name = name;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getTrainerPwd() {
		return trainerPwd;
	}

	public void setTrainerPwd(String trainerPwd) {
		this.trainerPwd = trainerPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean matchPassword(String password) {
		if (password == null)
			return false;
		return this.trainerPwd.equals(password);
	}

}