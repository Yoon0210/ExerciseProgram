package model;

import java.io.Serializable;

/**
 * REVIEW 테이블과 대응됨
 */

@SuppressWarnings("serial")
public class Review implements Serializable {

	private int reviewId;
	private String userId;
	private int workoutId;
	private String title;
	private String content;
	private int score;
	private int likeCount;
	private String postedDate;
	
	private String workoutName;
	private String trainerName;

	public Review(int reviewId, String userId, int workoutId, String title, String content, int score, int likeCount,
			String postedDate, String workoutName, String trainerName) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.workoutId = workoutId;
		this.title = title;
		this.content = content;
		this.score = score;
		this.likeCount = likeCount;
		this.postedDate = postedDate;
		this.workoutName = workoutName;
		this.trainerName = trainerName;
	}

	public Review(int reviewId, String userId, int workoutId, String title, String content, int score, int likeCount,
			String postedDate) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.workoutId = workoutId;
		this.title = title;
		this.content = content;
		this.score = score;
		this.likeCount = likeCount;
		this.postedDate = postedDate;
	}

	public Review(String userId, int workoutId, String title, String content, int score, int likeCount) {
		super();
		this.userId = userId;
		this.workoutId = workoutId;
		this.title = title;
		this.content = content;
		this.score = score;
		this.likeCount = likeCount;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String reviewTitle) {
		this.title = reviewTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String reviewContent) {
		this.content = reviewContent;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", workoutId=" + workoutId + ", title=" + title
				+ ", content=" + content + ", score=" + score + ", likeCount=" + likeCount + ", postedDate="
				+ postedDate + ", trainerName=" + trainerName + ", workoutName=" + workoutName + "]";
	}

	
	
	

}
