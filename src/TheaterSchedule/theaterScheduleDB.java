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
	public theaterSchedule getTheaterNum(String num) {

		theaterSchedule schedule = new theaterSchedule();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select theaterNum from theater"; // where num=? 을 나중에 뒤에 추가해줘야 하나의 영화에 대한 상영관 번호를 가져올 수 있다.
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
