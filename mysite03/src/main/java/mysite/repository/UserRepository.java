package mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	private DataSource dataSource;
	
	public UserRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int join(UserVo vo) {
		int count = 0;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into user values(null, ?, ?, ?, ?, curdate(), 'USER')");
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
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select id, name, role from user where email=? and password=?");
			){
				// 4. Parameter Binding  
				pstmt.setString(1, email); 
				pstmt.setString(2,password);  
				
				// 5. SQL 실행
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					Long id = rs.getLong(1);
					String name = rs.getString(2);
					String role = rs.getString(3);
					
					userVo = new UserVo();
					userVo.setId(id);
					userVo.setName(name);
					userVo.setRole(role);
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
				Connection conn = dataSource.getConnection();
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
				Connection conn = dataSource.getConnection();
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


}
