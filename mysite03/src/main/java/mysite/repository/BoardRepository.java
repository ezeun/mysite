package mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import mysite.vo.BoardVo;
import mysite.vo.UserVo;

@Repository
public class BoardRepository {
	private SqlSession sqlSession;
	
	public BoardRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}
	
	public BoardVo findById(Long boardId) {
		return sqlSession.selectOne("board.findById", boardId);
	}

	public int insert(BoardVo vo, UserVo authUser) {
		if(vo.getgNo()!=-1){ //답글
			sqlSession.update("board.updateOrderNo", Map.of("gNo", vo.getgNo(), "oNo", vo.getoNo()+1));
			return sqlSession.insert("board.insertReply", Map.of("vo", vo, "authUser", authUser));
		}
		else { //새글
			int MaxgNo = sqlSession.selectOne("board.getMaxgNo");
			return sqlSession.insert("board.insertNew", Map.of("vo", vo, "authUser", authUser, "MaxgNo", MaxgNo));
		}
	}

	public int deleteById(Long id) {
		return sqlSession.delete("board.deleteById", id);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);			
	}

	public int increaseHit(BoardVo vo) {
		return sqlSession.update("board.increaseHit", vo);	
	}

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
	
			// 2. 연결하기 
			String url = "jdbc:mariadb://192.168.0.118:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}







}
