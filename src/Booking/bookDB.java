package Booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import People.member;

public class bookDB {
   public bookDB() {

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
   public book getbook(String bookNo) {

      book book = new book();

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from book where bookNo=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, bookNo);

         rs = ps.executeQuery();

         if (rs.next()) {
            book.setbookNo(rs.getString("bookNo"));
            book.setmID(rs.getString("mID"));
            book.setTheaterID(rs.getString("theaterID"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return book;
   }
   
   // �� ���� ���� ��������
      public book getBook2(String mID) {

         book book = new book();

         Connection con = null; // ����
         PreparedStatement ps = null; // ���
         ResultSet rs = null; // ���

         try {
            con = getConn();
            String sql = "select * from book where mID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mID);

            rs = ps.executeQuery();

            if (rs.next()) {
               book.setbookNo(rs.getString("bookNo"));
               book.setmID(rs.getString("mID"));
               book.setTheaterID(rs.getString("theaterID"));
            }
         } catch (Exception e) {
            e.printStackTrace();
         }

         return book;
      }
      

   // ���� ���
   public boolean insertbook(book book) {
      Connection con = null; // ����
      PreparedStatement ps = null; // ���

      try {
         con = getConn();
         String sql = "insert into book(bookNo,mID,theaterID) values(?,?,?)";

         ps = con.prepareStatement(sql);
         ps.setString(1, book.getbookNo());
         ps.setString(2, book.getmID());
         ps.setString(3, book.getTheaterID());
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

   // ����Ʈ ���
   public ArrayList getbookList() {

      ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select * from book";
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();

         while (rs.next()) {
            String bookNo = rs.getString("bookNo");
            String mID = rs.getString("mID");
            String theaterID = rs.getString("theaterID");

            ArrayList<String> array = new ArrayList<String>();
            array.add(bookNo);
            array.add(mID);
            array.add(theaterID);

            data.add(array);
         } // while
      } catch (Exception e) {
         e.printStackTrace();
      }
      return data;
   }

   // ��ȭ���࿡ ���� ������
   public ArrayList getCheckReservationList(String mID) {

      ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

      Connection con = null; // ����
      PreparedStatement ps = null; // ���
      ResultSet rs = null; // ���

      try {
         con = getConn();
         String sql = "select book.bookNO, theater.CinemaName, theater.MovieTitle, theater.startTime   from book, theater where book.mID = ? AND book.theaterID = theater.theaterID";
         ps = con.prepareStatement(sql);
         ps.setString(1, mID);
         rs = ps.executeQuery();

         while (rs.next()) {
            String theaterID = rs.getString("bookNO");
            String CinemaName = rs.getString("CinemaName");
            String MovieTitle = rs.getString("MovieTitle");
            String startTime = rs.getString("startTime");

            ArrayList<String> array = new ArrayList<String>();
            array.add(theaterID);
            array.add(CinemaName);
            array.add(MovieTitle);
            array.add(startTime);
            data.add(array);
         } // while
      } catch (Exception e) {
         e.printStackTrace();
      }
      return data;
   }

   // �������
   public boolean deleteBook(String bookNO, String mID) {
      Connection con = null;
      PreparedStatement ps = null;

      try {
         con = getConn();
         String sql = "delete from book where bookNO=? AND mID = ?";

         ps = con.prepareStatement(sql);
         ps.setString(1, bookNO);
          ps.setString(2, mID);
         int r = ps.executeUpdate(); // ���� -> ����

         if (r > 0)
            return true;
         else
            return false;

      } catch (Exception e) {
         System.out.println(e + "-> �����߻�");
      }
      return true;
   }
   

}