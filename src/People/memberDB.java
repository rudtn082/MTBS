package People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class memberDB {
	public memberDB() {

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
	public member getMemberDTO(String id) {

		member member = new member();

		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				member.setmID(rs.getString("ID"));
				member.setmPW(rs.getString("PW"));
				member.setmName(rs.getString("Name"));
				member.setmDOB(rs.getString("DOB"));
				member.setmAddress(rs.getString("Address"));
				member.setmPN(rs.getString("PN"));
				member.setmticket(rs.getString("ticket"));
				member.setMpoint(rs.getString("point"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	// �������Ʈ ���
	public ArrayList getMemberList() {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�
		
		Connection con = null; // ����
		PreparedStatement ps = null; // ���
		ResultSet rs = null; // ���

		try {
			con = getConn();
			String sql = "select * from member order by name asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String mID = rs.getString("ID");
				String mPW = rs.getString("PW");
				String mName = rs.getString("Name");
				String mDOB = rs.getString("DOB");
				String mAddress = rs.getString("Address");
				String mPN = rs.getString("PN");
				String mticket = rs.getString("ticket");
				String mpoint = rs.getString("point");

				ArrayList<String> array = new ArrayList<String>();
				array.add(mID);
				array.add(mPW);
				array.add(mName);
				array.add(mDOB);
				array.add(mAddress);
				array.add(mPN);
				array.add(mticket);
				array.add(mpoint);

				data.add(array);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// ȸ�� ���
	public boolean insertMember(member member) {
		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			con = getConn();
			String sql = "insert into member(ID,PW,Name,DOB,Address,PN,ticket,point) values(?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, member.getmID());
			ps.setString(2, member.getmPW());
			ps.setString(3, member.getmName());
			ps.setString(4, member.getmDOB());
			ps.setString(5, member.getmAddress());
			ps.setString(6, member.getmPN());
			ps.setString(7, member.getmticket());
			ps.setString(8, member.getMpoint());
			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0) {
				System.out.println("���� ����");
				return true;
			} else {
				System.out.println("���� ����");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// ȸ������ ����
	public boolean updateMember(member member) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update member set ID=?, PW=?, Name=?, DOB=?, Address=?, PN=?, ticket=?, point=?"
					+ "where ID=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, member.getmID());
			ps.setString(2, member.getmPW());
			ps.setString(3, member.getmName());
			ps.setString(4, member.getmDOB());
			ps.setString(5, member.getmAddress());
			ps.setString(6, member.getmPN());
			ps.setString(7, member.getmticket());
			ps.setString(8, member.getMpoint());
			ps.setString(9, member.getmID());
			
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

	//������ҽ� ����Ʈ, �������� Ƽ�ϼ� ����
		public boolean updateCancelMember(member member , int recoveryPoint) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConn();
				String sql = "update member set  ticket=?, point = point + ?"
						+ "where ID=?";
				ps = con.prepareStatement(sql);

				
				ps.setString(1, String.valueOf(Integer.valueOf(member.getmticket()) - 1));
				ps.setString(2, String.valueOf(recoveryPoint));
				ps.setString(3, member.getmID());
				
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
	
	// ȸ�� ����
	public boolean deleteMember(String id, String pwd) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from member where ID=? and PW=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
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
