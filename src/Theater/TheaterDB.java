package Theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


public class TheaterDB {
	public TheaterDB() {
		
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
	public theater getTheaterDTO(String id) {

		theater theater = new theater();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from theater where TheaterID=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				theater.settTheaterID(rs.getString("TheaterID"));
				theater.settCinemaName(rs.getString("CinemaName"));
				theater.settSeatNum(rs.getString("SeatNum"));
				theater.settMovieID(rs.getString("MovieID"));
				theater.settStartTime(rs.getString("StartTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return theater;
	}

	// 멤버리스트 출력
	public Vector getTheaterList() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from theater order by TheaterID asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String tTheaterID = rs.getString("TheaterID");
				String tCinemaName = rs.getString("CinemaName");
				String tSeatNum = rs.getString("SeatNum");
				String tMovieID = rs.getString("MovieID");
				String tStartTime = rs.getString("StartTime");

				Vector row = new Vector();
				row.add(tTheaterID);
				row.add(tCinemaName);
				row.add(tSeatNum);
				row.add(tMovieID);
				row.add(tStartTime);
				
				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 회원 등록
	public boolean insertTheater(theater theater) {
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령

		try {
			con = getConn();
			String sql = "insert into theater(TheaterID,CinemaName,SeatNum,MovieID,StartTime) values(?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, theater.gettTheaterID());
			ps.setString(2, theater.gettCinemaName());
			ps.setString(3, theater.gettSeatNum());
			ps.setString(4, theater.gettMovieID());
			ps.setString(5, theater.gettStartTime());
			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("상영관 추가 성공");
				return true;
			} else {
				System.out.println("상영관 추가실패");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 회원정보 수정
	public boolean updateTheater(theater theater) {
		System.out.println("dto=" + theater.toString());
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update theater set TheaterID=?, CinemaName=?, SeatNum=?, MovieID=?, StartTime=?"
					+ "where TheaterID=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, theater.gettTheaterID());
			ps.setString(2, theater.gettCinemaName());
			ps.setString(3, theater.gettSeatNum());
			ps.setString(4, theater.gettMovieID());
			ps.setString(5, theater.gettStartTime());
			
			int r = ps.executeUpdate(); // 실행 -> 수정
			// 1~n: 성공 , 0 : 실패

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	// 회원 삭제
	public boolean deleteTheater(String TheaterID) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from theater where TheaterID=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, TheaterID);

			int r = ps.executeUpdate(); // 실행 -> 삭제

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			System.out.println(e + "-> 오류발생");
		}
		return true;
	}

	/** DB데이터 다시 불러오기 */
	public void userSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from theater order by TheaterID asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel에 있는 데이터 지우기
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
