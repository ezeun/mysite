package mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysite.vo.UserVo;

public class UserDao {
	public int join(UserVo vo) {
		int count = 0;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into user values(null, ?, ?, ?, ?, curdate());");
			){
				// 4. Parameter Binding  
				pstmt.setString(1, vo.getName()); 
				pstmt.setString(2, vo.getEmail()); 
				pstmt.setString(3, vo.getPassword()); 
				pstmt.setString(4, vo.getGender()); 
				
				// 5. SQL 실행
				count = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			return count;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo userVo = null;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select id, name from user where email=? and password=?");
			){
				// 4. Parameter Binding  
				pstmt.setString(1, email); 
				pstmt.setString(2,password);  
				
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					Long id = rs.getLong(1);
					String name = rs.getString(2);
					
					userVo = new UserVo();
					userVo.setId(id);
					userVo.setName(name);
				}
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			return userVo;
	}

	public UserVo findById(Long userId) {
		UserVo userVo = null;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select id, name, email, password, gender from user where id = ?");
			){
				// 4. Parameter Binding  
				pstmt.setLong(1, userId); 
				
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					Long id = rs.getLong(1);
					String name = rs.getString(2);
					String email = rs.getString(3);
					String password = rs.getString(4);
					String gender = rs.getString(5);
					
					userVo = new UserVo();
					userVo.setId(id);
					userVo.setName(name);
					userVo.setEmail(email);
					userVo.setPassword(password);
					userVo.setGender(gender);
				}
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			return userVo;
	}

	public int update(UserVo vo) {
		int count = 0;
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update user set name = ?, password=?, gender=? where id=?");
			){
				// 4. Parameter Binding  
				pstmt.setString(1, vo.getName()); 
				pstmt.setString(2, vo.getPassword()); 
				pstmt.setString(3, vo.getGender()); 
				pstmt.setLong(4, vo.getId()); 
				
				// 5. SQL 실행
				count = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			return count;
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
