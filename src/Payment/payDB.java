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
   public pay getStartTime(int payNo) {

      pay pay = new pay();

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from pay where payNo=?";
         ps = con.prepareStatement(sql);
         ps.setInt(1, payNo);

         rs = ps.executeQuery();

         if (rs.next()) {
            pay.setpayNo(rs.getInt("payNo"));
            pay.setbookNo(rs.getInt("bookNo"));
            pay.setprice(rs.getInt("price"));
            pay.setpayMethod(rs.getString("payMethod"));
            pay.setpayType(rs.getString("payType"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return pay;
   }
}