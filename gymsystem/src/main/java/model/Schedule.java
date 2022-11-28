package model;

import java.io.Serializable;

import java.util.Date;
@SuppressWarnings("serial")
public class Schedule implements Serializable{
	private int scheId;
	private String name;
	private Date time;
	private String description;
	private int exerciseId;
	
	public Schedule(int scheId, String name, Date time, String description, int exerciseId) {
		super();
		this.scheId = scheId;
		this.name = name;
		this.time = time;
		this.description = description;
		this.exerciseId = exerciseId;
	}

	public int getScheId() {
		return scheId;
	}

	public void setScheId(int scheId) {
		this.scheId = scheId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getexerciseId() {
		return exerciseId;
	}

	public void setexerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	
	
	
}