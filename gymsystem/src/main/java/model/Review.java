package model;

import java.io.Serializable;

/**
 * REVIEW 테이블과 대응됨
 */

@SuppressWarnings("serial")
public class Review implements Serializable {

	int reviewId;
	String userId;
	int workoutId;
	int trainerId;
	String title;
	String content;
	int score;
	int likeCount;
	String postedDate;

	public Review() {
	}

	public Review(int reviewId, String userId, int workoutId, int trainerId, String reviewTitle,
			String reviewContent, int score, int likeCount, String postedDate) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.workoutId = workoutId;
		this.trainerId = trainerId;
		this.title = reviewTitle;
		this.content = reviewContent;
		this.score = score;
		this.likeCount = likeCount;
		this.postedDate = postedDate;
	}

	public Review(String userId, int workoutId, int trainerId, String reviewTitle, String reviewContent, int score,
			int likeCount) {
		this.userId = userId;
		this.workoutId = workoutId;
		this.trainerId = trainerId;
		this.title = reviewTitle;
		this.content = reviewContent;
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
	
	public int getTrainerId() {
		return trainerId;
	}
	
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public void setTrainerName(int trainerId) {
		this.trainerId = trainerId;
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

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", workoutId=" + workoutId + ", trainerId="
				+ trainerId + ", title=" + title + ", content=" + content + ", score=" + score
				+ ", likeCount=" + likeCount + "]";
	}

}
