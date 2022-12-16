package model;

import java.io.Serializable;

import java.util.Date;
@SuppressWarnings("serial")
public class Schedule implements Serializable{
	private String userId;
	private int exerciseId;
	
	public Schedule(String userId, int exerciseId) {
		super();
		this.userId = userId;
		this.exerciseId = exerciseId;
	}
	
	public String getUserId() {
		return userId;
	}
	public int getExerciseId() {
		return exerciseId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	
	
	
}