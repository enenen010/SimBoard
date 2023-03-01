package com.bit.framework.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.cj.result.Row;

import java.sql.*;

public class JdbcTemplate<T> {
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}


	@SuppressWarnings("unused")
	private JdbcTemplate() {}
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public int queryForInt(String sql, Object ... args) {
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			for(int i=0; i<args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			ResultSet rs = pstmt.executeQuery();
			int result=0;
			if(rs.next())
				result= (int)rs.getInt(1);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int queryUpdate(String sql, Object ... args) {
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			for(int i=0; i<args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public T queryForObject(String sql,RowMapper<T> rowMapper, Object ... args) {
		return queryForList(sql, rowMapper, args).get(0);
	}
	public List<T> queryForList(String sql,RowMapper<T> rowMapper){
		return queryForList(sql, rowMapper, new Object[] {});
	}
	
	public List<T> queryForList(String sql,RowMapper<T> rowMapper, Object ... args ) {
		List<T> list = new ArrayList<T>();
		ResultSet rs=null;
		try	(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			for(int i=0; i<args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rowMapper.rows(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
}
