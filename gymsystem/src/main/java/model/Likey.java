package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Likey implements Serializable {

	private String userId;
	private int reviewId;

	public Likey() {	}

	public Likey(String userId, int reviewId) {
		this.userId = userId;
		this.reviewId = reviewId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	@Override
	public String toString() {
		return "Likey [userId=" + userId + ", reviewId=" + reviewId + "]";
	}

}
