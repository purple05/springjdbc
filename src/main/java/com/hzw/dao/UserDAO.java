package com.hzw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hzw.bean.User;

@Repository("userDAO")
public class UserDAO {
	@Resource(name="jt")
	private JdbcTemplate template; 
	public void save(User user) {
		String sql = "insert into user_cstms values(?,?,?,?)";
		Object[] param = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail()};
		template.update(sql,param);
	}
	
	public User getOne(String username) {
		String sql = "select * from user_cstms where username= ?";
		Object[] param = {username};
		User user = template.queryForObject(sql, param,new UserRowMapper());
		return user;
	}
	
	public List<User> getUser() {
		List<User> list = new ArrayList<User>();
		String sql = "select * from user_cstms";
		list = template.query(sql, new UserRowMapper());
		return list;
	}
	
	class UserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int index) throws SQLException {
			User user = new User();
			user.setUid(rs.getString("uid"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			return user;
		}
				
	}
}
