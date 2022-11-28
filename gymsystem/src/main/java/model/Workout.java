package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Workout implements Serializable {
	
	private int workoutId;
	private String name;
	
	public Workout(int workoutId, String name) {
		super();
		this.workoutId = workoutId;
		this.name = name;
	}
	
	public int getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Workout [workoutId=" + workoutId + ", name=" + name + "]";
	}
}
