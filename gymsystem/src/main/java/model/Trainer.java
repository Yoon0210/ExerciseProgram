package model;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")

public class Trainer implements Serializable{
	private String trainerId;
	private String trainerPwd;
	private String name;
	private String tel;
	private int peride;
	
	public Trainer(String trainerId, String trainerPwd, String name, String tel, int peride) {
		super();
		this.trainerId = trainerId;
		this.trainerPwd = trainerPwd;
		this.name = name;
		this.tel = tel;
		this.peride = peride;
	}

	public String gettrainerId() {
		return trainerId;
	}

	public void setGuideId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String gettrainerPwd() {
		return trainerPwd;
	}

	public void settrainerPwd(String trainerPwd) {
		this.trainerPwd = trainerPwd;
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

	public int getPeride() {
		return peride;
	}

	public void setPeride(int peride) {
		this.peride = peride;
	}
	
	public boolean matchPassword(String password) {
		if(password == null)
			return false;
		return this.trainerPwd.equals(password);
	}

}