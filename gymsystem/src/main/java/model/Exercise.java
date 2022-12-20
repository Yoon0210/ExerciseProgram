package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
//운동id, 트레이너id, 강좌명, 요일, 시간, 난이도, 운동타입, 트레이너이름
public class Exercise implements Serializable {
	private int exerciseId;
	private String trainerId;
	private String exerciseName;
	private String exerciseDay;
	private String exerciseTime;
	private String difficulty;
	private String exerciseType;
	private String trainerName;

	public Exercise() {
		super();
	}
	
	// 리뷰할 때 가져갈 데이터
	public Exercise(int exerciseId, String exerciseName, String trainerId, String trainerName) {
		super();
		this.exerciseId = exerciseId;
		this.exerciseName = exerciseName;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
	}

	public Exercise(String trainerId, String exerciseName, String exerciseDay, String exerciseTime, String difficulty,
			String exerciseType) {
		this.trainerId = trainerId;
		this.exerciseName = exerciseName;
		this.exerciseDay = exerciseDay;
		this.exerciseTime = exerciseTime;
		this.difficulty = difficulty;
		this.exerciseType = exerciseType;
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
	
	static String[] weekday = {"일","월","화","수","목","금","토"};

	//요일 시간 매핑 메소드!
	public static int getWeekday(String week) {
		
    	//key, value
    	Map<String,Integer> map1 = new HashMap<>();

    	for(int i = 0; i < weekday.length; i++) {
    		map1.put(weekday[i],i);
    	}
    	return map1.get(week);
	}

	//시간 매핑 메소드 
	public static int getTime(String time) {
    	//시간 매핑(map2)
		return Integer.parseInt(time)-9;
	}

}
