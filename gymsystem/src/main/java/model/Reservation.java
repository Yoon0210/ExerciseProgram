package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Reservation implements Serializable {
	private int resId;
	private String userId;
	private int exerciseId;

	private String reservationDate;
	
	private String status; // 운동예약 상태
	private String name; // 이름(강사 or 유저)
	private String exerciseName; // 운동 이름 및 종목
	private String exerciseType; // 운동 타입

	public Reservation(int resId, String userId, int exerciseId, String reservationDate, String status,
			String trainerName, String exerciseName) {
		super();
		this.resId = resId;
		this.userId = userId;
		this.exerciseId = exerciseId;
		this.reservationDate = reservationDate;
		this.status = status;
		this.name = trainerName;
		this.exerciseName = exerciseName;
	}

	public Reservation(String userId, int exerciseId, String reservationDate, String status, String trainerName,
			String exerciseName) {
		super();
		this.userId = userId;
		this.exerciseId = exerciseId;
		this.reservationDate = reservationDate;
		this.status = status;
		this.name = trainerName;
		this.exerciseName = exerciseName;
	}

	public Reservation(int resId, String userId, int exerciseId, String reservationDate, String status,
			String name, String exerciseName, String exerciseType) {
		super();
		this.resId = resId;
		this.userId = userId;
		this.exerciseId = exerciseId;
		this.reservationDate = reservationDate;
		this.status = status;
		this.name = name;
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getUserId() {
		return userId;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getStatus() {
		return status;
	}

	public String getTrainerName() {
		return name;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

}
