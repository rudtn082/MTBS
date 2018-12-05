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

   // �� ��ȭ ���� ��������
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

   // ��ȭ����Ʈ ���
   public Vector getSeatList() {

      Vector data = new Vector(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from seat order by sSeatNum asc";
         ps = con.prepareStatement(sql);
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

   // ��ȭ ���
   public boolean insertSeat(Seat seat) {
      Connection con = null; // ����
      PreparedStatement ps = null; // ���

      try {
         con = getConn();
         String sql = "insert into seat(sSeatNum, sTheaterID) values(?,?)";

         ps = con.prepareStatement(sql);
         ps.setString(1, seat.getsSeatNum());
         ps.setString(2,  seat.getsTheaterID());
        
         int r = ps.executeUpdate(); // ���� -> ����

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

   // ��ȭ ���� ����
   public boolean updateSeat(Seat seat) {
      System.out.println("dto=" + seat.toString());
      Connection con = null;
      PreparedStatement ps = null;
      try {
         con = getConn();
         String sql = "update seat set sSeatNum =?, sTheaterName=?"
               + "where SeatNum=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, seat.getsSeatNum());
         ps.setString(2,  seat.getsTheaterID());
               
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
   public boolean deleteSeat(String id) {
      Connection con = null;
      PreparedStatement ps = null;

      try {
         con = getConn();
         String sql = "delete from seat where sSeatNum=?";

         ps = con.prepareStatement(sql);
         ps.setString(1, id);
         //ps.setString(2, pwd);
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
         String sql = "select * from seat order by sSeatNum asc";
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
