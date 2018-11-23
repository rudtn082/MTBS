package TheaterSchedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import People.member;

public class theaterScheduleDB {
	public theaterScheduleDB(){
		
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
	public theaterSchedule getTheaterNum(String num) {

		theaterSchedule schedule = new theaterSchedule();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select theaterNum from theater"; // where num=? �� ���߿� �ڿ� �߰������ �ϳ��� ��ȭ�� ���� �󿵰� ��ȣ�� ������ �� �ִ�.
			ps = con.prepareStatement(sql);
			ps.setString(2, num);

			rs = ps.executeQuery();

			if (rs.next()) {
				schedule.settheaterNum(rs.getString("theaterNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return schedule;
	}
}
