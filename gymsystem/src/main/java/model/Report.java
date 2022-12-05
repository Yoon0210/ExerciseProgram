package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Report implements Serializable {

	private String userId;
	private int reviewId;
	private String reportReason;

	public Report() {	}

	public Report(String userId, int reviewId) {
		this.userId = userId;
		this.reviewId = reviewId;
	}

	public Report(String userId, int reviewId, String reportReason) {
		super();
		this.userId = userId;
		this.reviewId = reviewId;
		this.reportReason = reportReason;
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

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
}
