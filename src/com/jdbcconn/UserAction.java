package com.jdbcconn;

import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String password;

	private boolean flag = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ��¼��֤
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String loginAlivator() throws SQLException {
		String usr = getUsername();
		String pwd = getPassword();
		boolean tag = false;
		JdbcConnect conn = new JdbcConnect();
		ResultSet rs = conn.query(usr);
		while (rs.next()) {
			String cupwd = rs.getString("password");
			if (pwd.equals(cupwd)) {
				tag = true;
			}
		}
		if (tag) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * �༭�û���Ϣ
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String editUser() throws SQLException, Exception {

		String u_id = getId();
		String u_name = getUsername();
		String u_pwd = getPassword();

		System.out.println(u_id + u_name + u_pwd);
		JdbcConnect m = new JdbcConnect();
		String sql = "update  user set username=?,password=? where id=?";
		Connection c = m.getConn();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u_name);
		ps.setString(2, u_pwd);
		ps.setString(3, u_id);

		int i = ps.executeUpdate();
		if (i == 0) {
			flag = false;
		}

		if (flag) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String deleteUser() throws SQLException, Exception {
		String u_id = getId();
		String sql = "delete from user where id=?";
		JdbcConnect m = new JdbcConnect();
		Connection c = m.getConn();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u_id);
		int i = ps.executeUpdate();
		if (i == 0) {
			flag = false;
		}

		if (flag) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * ����û�
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String addUser() throws SQLException, Exception {
		String u_name = getUsername();
		String u_pwd = getPassword();
		String sql = "insert into user (username,password) values (?,?)";
		JdbcConnect m = new JdbcConnect();
		Connection c = m.getConn();
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u_name);
		ps.setString(2, u_pwd);
		int i = ps.executeUpdate();//������Ӱ�������
		if (i > 0) {
			return SUCCESS;
		}else {
			return ERROR;
		}	
	}
}
