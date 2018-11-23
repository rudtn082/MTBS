package Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Booking.book;

public class payDB {
	public payDB(){
		
	}
	
	// DB연결 메소드
	public Connection getConn() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. 드라이버 로딩
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MTBS?serverTimezone=UTC&useSSL=false",
					"MTBS", "mtbs"); // 2. 드라이버 연결

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	// 한사람의 회원 정보 가져오기
	public pay getStartTime(int num) {

		pay pay = new pay();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select bookNum from book";
			ps = con.prepareStatement(sql);
			ps.setInt(2, num);

			rs = ps.executeQuery();

			if (rs.next()) {
				pay.setpayNum(rs.getInt("payNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pay;
	}
}
