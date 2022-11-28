package model;

import java.io.Serializable;

/**
 * REVIEW 테이블과 대응됨
 */

@SuppressWarnings("serial")
public class Reservation implements Serializable {
	private int resId; 
	private int trainerId; //강사번호
	private String exerciseName; //운동 이름 및 종목
	private int exercisePrice; //운동가격
	private String payStatus; //결제상태
	private String resStatus; //운동예약 상태
	
	public Reservation(int resId,int trainerId, int exercisePrice, String exerciseName, String payStatus, String resStatus) {
		super();
		this.resId=resId;
		this.trainerId = trainerId;
		this.exercisePrice=exercisePrice;
		this.exerciseName = exerciseName;
		this.payStatus = payStatus;
		this.resStatus = resStatus;
	}


	public int getresId() {
		return resId;
	}

	public void setresId(int resId) {
		this.resId = resId;
	}

	
	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getExercisePrice() {
		return exercisePrice;
	}

	public void setExercisePrice(int exercisePrice) {
		this.exercisePrice = exercisePrice;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	
	
}
