package model.dao.mybatis.mapper;

import java.util.List;
import model.Review;

public interface ReviewMapper {

	public int insertReview(Review review);
	
 
	public int updateReview(Review review);
	
	public int updateChair(Review review);

	public int deleteReview(int reviewId);

	public Review selectReviewByPrimaryKey(int reviewId);
	
	public Review selectReviewWithMembers(int reviewId);

	public List<Review> selectAllCommunities();
}
