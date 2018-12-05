package Cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;



public class cinemaDB {
	public cinemaDB() {
		
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

	// ��ȭ�� ���� ��������
	public cinema getCinemaDTO(String NAME) {

		cinema cinema = new cinema();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select * from cinema where NAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, NAME);

			rs = ps.executeQuery();

			if (rs.next()) {
				cinema.setcNAME(rs.getString("NAME"));
				cinema.setcAddress(rs.getString("Address"));
				cinema.setcPhoneNum(rs.getString("PhoneNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cinema;
	}

	// ��ȭ�� ����Ʈ ���
	public Vector getCinemaList() {

		Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select * from cinema order by NAME asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cName = rs.getString("NAME");
				String cAddress = rs.getString("Address");
				String cPhoneNum = rs.getString("PhoneNum");

				Vector row = new Vector();
				row.add(cName);
				row.add(cAddress);
				row.add(cPhoneNum);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// ��ȭ�� ���
	public boolean insertCinema(cinema cinema) {
		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			con = getConn();
			String sql = "insert into cinema(NAME, Address, PhoneNum) values(?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, cinema.getcNAME());
			ps.setString(2, cinema.getcAddress());
			ps.setString(3, cinema.getcPhoneNum());
			
			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0) {
				System.out.println("��ȭ�� �߰� ����");
				return true;
			} else {
				System.out.println("��ȭ�� �߰� ����");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// ��ȭ�� ���� ����
	public boolean updateCinema(cinema cinema) {
		System.out.println("dto=" + cinema.toString());
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update cinema set NAME=?, Address=?, PhoneNum=?"
					+ "where NAME=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, cinema.getcNAME());
			ps.setString(2, cinema.getcAddress());
			ps.setString(3, cinema.getcPhoneNum());
			ps.setString(4, cinema.getcNAME());
			

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

	// ��ȭ�� ����
	public boolean deleteCinema(String name) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from cinema where Name=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			System.out.println(e + "-> �����߻�");
		}
		return true;
	}

	/** DB������ �ٽ� �ҷ����� */
	public void userSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from cinema order by NAME asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel�� �ִ� ������ �����
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
