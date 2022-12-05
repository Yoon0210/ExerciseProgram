package model;

import java.io.Serializable;

/**
 * REVIEW 테이블과 대응됨
 */

@SuppressWarnings("serial")
public class Reservation implements Serializable {
<<<<<<< HEAD
	private int resId; //예약id
=======
	private int resId;
	private String userId;
	private int exerciseId;
>>>>>>> branch 'develop' of https://github.com/Yoon0210/ExerciseProgram.git
	private int trainerId; //강사번호
	private String exerciseName; //운동 이름 및 종목
	private String resStatus; //운동예약 상태
	
	public Reservation(int resId,int trainerId, String exerciseName,  String resStatus) {
		super();
		this.resId=resId;
		this.trainerId = trainerId;
		this.exerciseName = exerciseName;
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

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	
	
}
