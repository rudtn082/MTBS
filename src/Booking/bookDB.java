package Booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import People.member;

public class bookDB {
	public bookDB(){
		
	}
	
	// DB���� �޼ҵ�
	public Connection getConn() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. ����̹� �ε�
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MTBS?serverTimezone=UTC&useSSL=false",
					"MTBS", "mtbs"); // 2. ����̹� ����

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	// �ѻ���� ȸ�� ���� ��������
	public book getStartTime(int time) {

		book book = new book();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select startTime from theaterSchedule";
			ps = con.prepareStatement(sql);
			ps.setInt(3, time);

			rs = ps.executeQuery();

			if (rs.next()) {
				book.setstartTime(rs.getInt("startTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return book;
	}
}
