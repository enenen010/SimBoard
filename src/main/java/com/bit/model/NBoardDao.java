package com.bit.model;
import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.bit.framework.jdbc.*;
import com.mysql.cj.jdbc.MysqlDataSource;

public class NBoardDao {
	DataSource dataSource;
	RowMapper<NBoardDto> mapper = new RowMapper<NBoardDto>() {
		
		@Override
		public NBoardDto rows(ResultSet rs) throws SQLException {
			NBoardDto boardDto = new NBoardDto();
			boardDto.setNqid(rs.getString("nqid"));
			boardDto.setContent(rs.getString("content"));
			boardDto.setImg(rs.getString("img"));
			boardDto.setSub(rs.getString("sub"));
			boardDto.setId(rs.getString("id"));
			boardDto.setwDate(rs.getDate("wdate"));
			return boardDto;
		}
	};
	
	public NBoardDao() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/12kchess");
		dataSource.setUser(System.getenv().get("MYSQL_USER"));
		dataSource.setPassword(System.getenv().get("MYSQL_PW"));
		this.dataSource = dataSource;
	}
	
	public int SelectCount(String content,String id) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="select count(nqid) from NBOARD where nqid like ? and id like ?";
		int result = jdbcTemplate.queryForInt(sql, "%"+content+"%","%"+id+"%");
		return result;
	}
	
	public List<NBoardDto> SelectList(String content,String id) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="select * from NBOARD where nqid like ? and id like ?";
		List<NBoardDto> list = jdbcTemplate.queryForList(sql, mapper, "%"+content+"%","%"+id+"%");
		return list;
	}
	
	public NBoardDto SelectOne(String nqid) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="select * from NBOARD where nqid = ?";
		NBoardDto boardDto = jdbcTemplate.queryForObject(sql, mapper, nqid);
		return boardDto;
	}
	
	public int InsertOne(String nqid, String content, String img, String sub, String id) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="insert into NBOARD(nqid,content,img,sub,id,wdate) ";
		sql+="values(?,?,?,?,?,now())";
		return jdbcTemplate.queryUpdate(sql, nqid, content, img, sub, id);
	}
	
	public int UpdateOne(String nqid, String content, String img, String sub, String id) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="update NBOARD set content=?,img=?,sub=?,id=? ";
		sql+="where nqid=?";
		return jdbcTemplate.queryUpdate(sql, content, img, sub, id, nqid);
	}
	
	public int DeleteOne(String nqid) {
		JdbcTemplate<NBoardDto> jdbcTemplate = new JdbcTemplate<NBoardDto>(dataSource);
		String sql="delete from NBOARD ";
		sql+="where nqid=?";
		return jdbcTemplate.queryUpdate(sql, nqid);
	}
}
