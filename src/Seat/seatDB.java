package Seat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Movie.movie;

public class seatDB {
   public seatDB() {

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

   // �� �¼� ���� ��������
   public Seat getSeatDTO(String ID) {

      Seat seat = new Seat();

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from seat where sSeatNum=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         
         rs = ps.executeQuery();
         if (rs.next()) {
            seat.setsSeatNum(rs.getString("sSeatNum"));
            seat.setsTheaterID(rs.getString("sTheaterID"));
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return seat;
   }

   // �¼� ����Ʈ ���
   public Vector getSeatList(String movieName) {

      Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from seat where sTheaterID in (select TheaterID from theater where MovieTitle=?)";
         ps = con.prepareStatement(sql);
         ps.setString(1, movieName);
         rs = ps.executeQuery();

         while (rs.next()) {
            String sSeatNum = rs.getString("sSeatNum");
            String sTheaterID = rs.getString("sTheaterID");
           

            Vector row = new Vector();
            row.add(sSeatNum);
            row.add(sTheaterID);
           
            data.add(row);
         } // while
      } catch (Exception e) {
         e.printStackTrace();
      }
      return data;
   }

   // �¼� ���
   public boolean insertSeat(Seat seat, int snum) {
      Connection con = null; // ����
      PreparedStatement ps = null; // ���

      try {
         con = getConn();
         String sql = "insert into seat(sSeatNum, sTheaterID) values(?,?)";

         int r = 0;
         for(int i = 0; i < snum; i++) {
             ps = con.prepareStatement(sql);
             ps.setString(1, String.valueOf(seat.getsTheaterID()+(i+1)));
             ps.setString(2, seat.getsTheaterID());
            
             r = ps.executeUpdate(); // ���� -> ����
             
         }

         if (r > 0) {
             System.out.println("�¼� �߰� ����");
             return true;
          } else {
             System.out.println("�¼� �߰� ����");
             return false;
          }
         
      
      } catch (Exception e) {
         e.printStackTrace();
      }

      return false;
   }
}
