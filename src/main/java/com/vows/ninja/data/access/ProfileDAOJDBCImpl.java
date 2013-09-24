package com.vows.ninja.data.access;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class ProfileDAOJDBCImpl implements ProfileDAO{

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static ProfileDAOJDBCImpl _instance;

	private ProfileDAOJDBCImpl() {

	}

	public static ProfileDAOJDBCImpl getInstance() {
		if(_instance == null) {
			synchronized (ProfileDAOJDBCImpl.class) {
				if(_instance == null) {
					_instance = new ProfileDAOJDBCImpl();
				}			
			}
		}
		return _instance;
	}

	public void setDataSource(BasicDataSource dataSource) {
		System.out.println("Gets called");
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void create(Profile p) {
		String sql = "insert into profile (p_id, p_name) values (:p_id, :p_name)";

		MapSqlParameterSource mapParameters = new MapSqlParameterSource();
		mapParameters.addValue("p_name", p.getProfile_name());
		mapParameters.addValue("p_id", p.getProfile_id());		

		namedParameterJdbcTemplate.update(sql, mapParameters);
	}

	@Override
	public Profile get(int id) {
		String sql = "select * from profile where p_id = :p_id";

		MapSqlParameterSource mapParameters = new MapSqlParameterSource();
		mapParameters.addValue("p_id", id);

		return  (Profile)namedParameterJdbcTemplate.queryForObject(sql, mapParameters, new ProfileRowMapper()) ;			
	}


	private class ProfileRowMapper implements RowMapper<Profile> {    
		@Override
		public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
			Profile p = new Profile();
			p.setProfile_id(rs.getInt("p_id"));            
			p.setProfile_name(rs.getString("p_name"));
			return p;        
		}
	};

	public static void main(String[] args) {
		ProfileDAOJDBCImpl pd = new ProfileDAOJDBCImpl();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setPassword("");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("");
		pd.setDataSource(dataSource);		
		Profile p = pd.get(1);
		System.out.println(p.getProfile_id() + p.getProfile_name());
	}
}
