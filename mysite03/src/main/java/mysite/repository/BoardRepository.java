package mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	
	public int getMaxgNo() {
		
		Optional<Integer> max_gNo;
		int ret = 0;
		
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select max(g_no) from board");
			){							
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					max_gNo = Optional.of(rs.getInt(1));
					if(max_gNo.isEmpty()) ret = 0; // null 방지 
					else ret = max_gNo.get();
				}
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
		
		return ret;
	}
	private void updateOrderNo(int gNo, int oNo) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update board set o_no = o_no+1 where g_no=? and o_no>=?");
			){
				// 4. Parameter Binding  
				pstmt.setInt(1, gNo); 
				pstmt.setInt(2, oNo); 
				
				// 5. SQL 실행
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
	}
	public void insert(BoardVo vo, UserVo authUser) {
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into board values(null, ?, ?, ?, now(), ?, ?, ?, ?);");
		){			
			// 4. Parameter Binding  
			pstmt.setString(1, vo.getTitle()); 
			pstmt.setString(2, vo.getContents()); 
			pstmt.setInt(3, vo.getHit()); 
			if(vo.getgNo()!=-1) { //답글
				pstmt.setInt(4, vo.getgNo());
				pstmt.setInt(5, vo.getoNo()+1);
				pstmt.setInt(6, vo.getDepth()+1);
				updateOrderNo( vo.getgNo(), vo.getoNo()+1);
			}
			else { //새글
				pstmt.setInt(4, getMaxgNo()+1);
				pstmt.setInt(5, 1);
				pstmt.setInt(6, 0);
			}			
			pstmt.setLong(7, authUser.getId());
			
			// 5. SQL 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
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
