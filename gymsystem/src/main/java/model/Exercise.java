package model;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
//운동종목, 운동 카테고리, 운동 트레이너id, 운동 번호, 운동 시작, 끝날짜, 운동 강도
public class Exercise implements Serializable{
	private int exerciseId;
	private String trainerId;
	private String exerciseName;
	private String exerciseDay;
	private String exerciseTime;
	private String difficulty;
	private String exerciseType;
	private String trainerName;
	
	//리뷰할 때 가져갈 데이터 
	public Exercise(int exerciseId, String exerciseName, String trainerId, String trainerName) {
		super();
		this.exerciseId = exerciseId;
		this.exerciseName = exerciseName;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
	}

	public Exercise(int exerciseId, String trainerId, String exerciseName, String exerciseDay, String exerciseTime,
			String difficulty, String exerciseType, String trainerName) {
		super();
		this.exerciseId = exerciseId;
		this.trainerId = trainerId;
		this.exerciseName = exerciseName;
		this.exerciseDay = exerciseDay;
		this.exerciseTime = exerciseTime;
		this.difficulty = difficulty;
		this.exerciseType = exerciseType;
		this.trainerName = trainerName;
	}


	public Exercise(String trainerId, String exerciseName, String exerciseDay, String exerciseTime, String difficulty,
			String exerciseType, String trainerName) {
		super();
		this.trainerId = trainerId;
		this.exerciseName = exerciseName;
		this.exerciseDay = exerciseDay;
		this.exerciseTime = exerciseTime;
		this.difficulty = difficulty;
		this.exerciseType = exerciseType;
		this.trainerName = trainerName;
	}

	public int getExerciseId() {
		return exerciseId;
	}




	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}




	public String getTrainerId() {
		return trainerId;
	}




	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}




	public String getExerciseName() {
		return exerciseName;
	}




	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}




	public String getExerciseDay() {
		return exerciseDay;
	}




	public void setExerciseDay(String exerciseDay) {
		this.exerciseDay = exerciseDay;
	}




	public String getExerciseTime() {
		return exerciseTime;
	}




	public void setExerciseTime(String exerciseTime) {
		this.exerciseTime = exerciseTime;
	}




	public String getDifficulty() {
		return difficulty;
	}




	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}




	public String getExerciseType() {
		return exerciseType;
	}




	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}




	public String getTrainerName() {
		return trainerName;
	}




	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}




	@Override
	public String toString() {
		return exerciseName + " - " + trainerName;
	}
	
}
