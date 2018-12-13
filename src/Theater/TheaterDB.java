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

	// �� �󿵰� ȸ�� ���� ��������
	public theater getTheaterDTO(String id) {

		theater theater = new theater();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

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
				theater.settMovieTitle(rs.getString("MovieTitle"));
				theater.settStartTime(rs.getString("StartTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return theater;
	}

	// �󿵰� ����Ʈ ���
	public Vector getTheaterList() {

		Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select * from theater order by TheaterID asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String tTheaterID = rs.getString("TheaterID");
				String tCinemaName = rs.getString("CinemaName");
				String tSeatNum = rs.getString("SeatNum");
				String tMovieTitle = rs.getString("MovieTitle");
				String tStartTime = rs.getString("StartTime");

				Vector row = new Vector();
				row.add(tTheaterID);
				row.add(tCinemaName);
				row.add(tSeatNum);
				row.add(tMovieTitle);
				row.add(tStartTime);
				
				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// �󿵰� ���
	public boolean insertTheater(theater theater) {
		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			con = getConn();
			String sql = "insert into theater(TheaterID,CinemaName,SeatNum,MovieTitle,StartTime) values(?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, theater.gettTheaterID());
			ps.setString(2, theater.gettCinemaName());
			ps.setString(3, theater.gettSeatNum());
			ps.setString(4, theater.gettMovieTitle());
			ps.setString(5, theater.gettStartTime());
			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0) {
				System.out.println("�󿵰� �߰� ����");
				return true;
			} else {
				System.out.println("�󿵰� �߰�����");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// �󿵰� ���� ����
	public boolean updateTheater(theater theater) {
		System.out.println("dto=" + theater.toString());
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update theater set TheaterID=?, CinemaName=?, SeatNum=?, MovieTitle=?, StartTime=?"
					+ "where TheaterID=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, theater.gettTheaterID());
			ps.setString(2, theater.gettCinemaName());
			ps.setString(3, theater.gettSeatNum());
			ps.setString(4, theater.gettMovieTitle());
			ps.setString(5, theater.gettStartTime());
			ps.setString(6, theater.gettTheaterID());
			
			int r = ps.executeUpdate(); // ���� -> ����
			// 1~n: ���� , 0 : ����

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	// �󿵰� ����
	public boolean deleteTheater(String TheaterID) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from theater where TheaterID=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, TheaterID);

			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			System.out.println(e + "-> �����߻�");
		}
		return true;
	}
}
