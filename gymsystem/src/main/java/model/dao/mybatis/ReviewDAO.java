package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Review;
import model.dao.mybatis.mapper.ReviewMapper;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Review 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class ReviewDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public ReviewDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	public Review create(Review review) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReviewMapper.class).insertReview(review);
			if (result > 0) {
				sqlSession.commit();
			} 
			return review;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 기존의 커뮤니티 정보를 수정
	 */
	public int update(Review review) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReviewMapper.class).updateReview(review);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 커뮤니티의 회장을 변경  
	 */
	public int updateChair(Review review) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReviewMapper.class).updateChair(review);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 주어진 ID에 해당하는 커뮤니티 정보를 삭제.
	 */
	public int remove(int reviewId) {		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReviewMapper.class).deleteReview(reviewId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 주어진  ID에 해당하는 커뮤니티 정보를 데이터베이스에서 찾아 Review 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Review findReview(int reviewId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ReviewMapper.class).selectReviewByPrimaryKey(reviewId);			
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 주어진  ID에 해당하는 커뮤니티 정보를 데이터베이스에서 찾아 Review 도메인 클래스에 
	 * 저장하고, 동시에 그 커뮤니티에 속한 모든 회원들의 정보를 찾아 List<UserInfo> 에 저장하여 함께 반환함  
	 */
	public Review findReviewWithMembers(int reviewId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ReviewMapper.class).selectReviewWithMembers(reviewId);			
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Review> findReviewList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ReviewMapper.class).selectAllCommunities();			
		} finally {
			sqlSession.close();
		}
	}
	
}
