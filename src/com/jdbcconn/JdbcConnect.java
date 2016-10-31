package com.jdbcconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

/**
 * JDBC连接数据库及相关操作
 * @author wxx
 *
 */
public class JdbcConnect {
	public static String DRIVER = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
	public static String USER = "root";
	public static String PWD = "12345";
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 根据用户名验证密码
	 * @param username
	 * @return
	 */
	public ResultSet query(String username) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement prs = null;
		String sql ="select password from user where username=?";
		try {
			prs = conn.prepareStatement(sql);
			prs.setString(1, username);
			rs = prs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 添加用户
	 * @param username
	 * @param pwd
	 * @return
	 */
	public boolean insert(String username,String pwd){
		Connection conn = getConn();
		PreparedStatement prs = null;
		String sql = "insert into user ('username','password') values (?,?)";
		boolean tag = false;
		try {
			prs = conn.prepareStatement(sql);
			prs.setString(1, username);
			prs.setString(2, pwd);
			tag = !prs.execute();
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return tag;
	}
	
	/**
	 * 通过ID获取用户信息
	 * @param id
	 * @return
	 */
	public ResultSet getUserByID(String id){
		String sql = "select * from user where id="+id;
	  	Connection c = getConn();
	  	ResultSet rs = null;
	  	Statement s;
		try {
			s = (Statement) c.createStatement();
			rs = s.executeQuery(sql);
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	  	return rs;
	}
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public ResultSet getAllUser(){
		String sql = "select * from user";
    	Connection c = getConn();
    	Statement s = null;
    	ResultSet rs = null;
		try {
			s = (Statement) c.createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return rs;
	}
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param prs
	 */
	public void closeConn(Connection conn,PreparedStatement prs){
		try{
			if(conn != null){
				conn.close();
			}
			if(prs != null){
				prs.close();
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
