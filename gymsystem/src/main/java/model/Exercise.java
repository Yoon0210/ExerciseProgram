package model;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
//운동종목, 운동 카테고리, 운동 트레이너id, 운동 번호, 운동 가격, 운동 시작, 끝날짜, 운동 강도
public class Exercise implements Serializable{
	private int exerciseId;
	private String name;
	private int price;
	private String strength;
	private Date startTime;
	private Date endTime;
	private String trainerId;
	private String category;
	
	private String trainerName;
	
	public Exercise(int exerciseId, String name, int price, String strength,Date startTime, Date endTime, String trainerId, String category) {
		super();
		this.exerciseId = exerciseId;
		this.name = name;
		this.price = price;
		this.strength=strength;
		this.startTime = startTime;
		this.endTime = endTime;
		this.trainerId = trainerId;
		this.category = category;
	}

	public Exercise(int exerciseId, String name, String trainerId, String trainerName) {
		super();
		this.exerciseId = exerciseId;
		this.name = name;
		this.trainerId = trainerId;
		this.trainerName = trainerName;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	@Override
	public String toString() {
		return name + " - " + trainerName;
	}
	
}