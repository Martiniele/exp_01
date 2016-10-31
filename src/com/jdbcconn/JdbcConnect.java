package com.jdbcconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

/**
 * JDBC�������ݿ⼰��ز���
 * @author wxx
 *
 */
public class JdbcConnect {
	public static String DRIVER = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8";
	public static String USER = "root";
	public static String PWD = "12345";
	/**
	 * �������ݿ�
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
	 * �����û�����֤����
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
	 * ����û�
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
	 * ͨ��ID��ȡ�û���Ϣ
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
	 * ��ȡ�����û���Ϣ
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
	 * �ر����ݿ�����
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
