package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Review;

public class ReviewSessionRepository {
	private final String namespace = "model.dao.mybatis.mapper.ReviewMapper";

	private SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
	
	private SqlSessionFactory createSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	public List<Review> findReviewByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					namespace + ".selectReviewByCondition", condition);	
		} finally {
			sqlSession.close();
		}
	}

}
