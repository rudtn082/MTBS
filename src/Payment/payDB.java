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
import People.member;

public class payDB {
   public payDB(){
      
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
   
   // �� ���� ���� ��������
   public pay getpay(String string) {

      pay pay = new pay();

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from pay where payNo=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, string);

         rs = ps.executeQuery();

         if (rs.next()) {
            pay.setpayNo(rs.getString("payNo"));
            pay.setbookNo(rs.getString("bookNo"));
            pay.setprice(rs.getString("price"));
            pay.setpayMethod(rs.getString("payMethod"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return pay;
   }
   
// �� ���� ���� ��������
   public pay getpay2(String bookNO) {

      pay pay = new pay();

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from pay where bookNO=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, bookNO);

         rs = ps.executeQuery();

         if (rs.next()) {
            pay.setpayNo(rs.getString("payNo"));
            pay.setbookNo(rs.getString("bookNo"));
            pay.setprice(rs.getString("price"));
            pay.setpayMethod(rs.getString("payMethod"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return pay;
   }
   
	// ���� ���
	public boolean insertpay(pay pay) {
		Connection con = null; // ����
		PreparedStatement ps = null; // ���

		try {
			con = getConn();
			String sql = "insert into pay(payNo,bookNo,price,payMethod) values(?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, pay.getpayNo());
			ps.setString(2, pay.getbookNo());
			ps.setString(3, pay.getprice());
			ps.setString(4, pay.getpayMethod());
			int r = ps.executeUpdate(); // ���� -> ����

			if (r > 0) {
				System.out.println("����");
				return true;
			} else {
				System.out.println("����");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	// �������� ����
		public boolean updatepay(pay pay) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConn();
				String sql = "update pay set payNo=?, bookNo=?, price=?, payMethod=?"
						+ "where payNo=?";
				ps = con.prepareStatement(sql);

				ps.setString(1, pay.getpayNo());
				ps.setString(2, pay.getbookNo());
				ps.setString(3, pay.getprice());
				ps.setString(4, pay.getpayMethod());
				ps.setString(5, pay.getpayNo());
				
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
	
	// ����Ʈ ���
		public ArrayList getpayList() {

			ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�
			
			Connection con = null; // ����
			PreparedStatement ps = null; // ���
			ResultSet rs = null; // ���

			try {
				con = getConn();
				String sql = "select * from pay";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					   String payNo = rs.getString("payNo");
					   String bookNo = rs.getString("bookNo");
					   String price = rs.getString("price");
					   String payMethod = rs.getString("payMethod");

					ArrayList<String> array = new ArrayList<String>();
					array.add(payNo);
					array.add(bookNo);
					array.add(price);
					array.add(payMethod);

					data.add(array);
				} // while
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
}