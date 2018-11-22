package People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import UI.Login;

public class memberDAO {    
    public memberDAO() {
    	getConn();
    	getMemberDTO("id");
    }
    /**DB연결 메소드*/
    public Connection getConn(){
        Connection con = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //1. 드라이버 로딩
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MTBS?serverTimezone=UTC&useSSL=false","MTBS","mtbs"); //2. 드라이버 연결
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return con;
    }
    
    /**한사람의 회원 정보를 얻는 메소드*/
    public member getMemberDTO(String id){
       
    	member member = new member();
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try {
            con = getConn();
            String sql = "select * from member where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
           
            rs = ps.executeQuery();
           
            if(rs.next()){
            	member.setmID(rs.getString("ID"));
            	member.setmPW(rs.getString("PW"));
            	member.setmName(rs.getString("Name"));
            	member.setmDOB(Integer.valueOf(rs.getString("DOB")));
                member.setmAddress(rs.getString("Address"));
                member.setmPN(Integer.valueOf(rs.getString("PN")));
                member.setmticket(Integer.valueOf(rs.getString("ticket")));
                member.setmVIP(Integer.valueOf(rs.getString("VIP")));
            }
            System.out.println(member.getmID());
            System.out.println(member.getmPW());
            System.out.println(member.getmName());
            System.out.println(member.getmDOB());
            System.out.println(member.getmAddress());
            System.out.println(member.getmPN());
            System.out.println(member.getmticket());
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return member;    
    }
   
    /**멤버리스트 출력*/
    public Vector getMemberList(){
       
        Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
           
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
           
            con = getConn();
            String sql = "select * from member order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
                String mID = rs.getString("ID");
                String mPW = rs.getString("PW");
                String mName = rs.getString("Name");
                int mDOB = Integer.valueOf(rs.getString("DOB"));
                String mAddress = rs.getString("Address");
                int mPN = Integer.valueOf(rs.getString("PN"));
                int mticket = Integer.valueOf(rs.getString("ticket"));
                int mVIP = Integer.valueOf(rs.getString("VIP"));
               
                Vector row = new Vector();
                row.add(mID);
                row.add(mPW);
                row.add(mName);
                row.add(mDOB);
                row.add(mAddress);
                row.add(mPN);
                row.add(mticket);
                row.add(mVIP);
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }//getMemberList()
   
 
 
    /**회원 등록*/
    public boolean insertMember(member member){
       
        boolean ok = false;
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
       
        try{
           
            con = getConn();
            String sql = "insert into member(" +
                        "id,pwd,name,tel,addr,birth," +
                        "job,gender,email,intro) "+
                        "values(?,?,?,?,?,?,?,?,?,?)";


            ps = con.prepareStatement(sql);
            ps.setString(1, member.getmID());
            ps.setString(2, member.getmPW());
            ps.setString(3, member.getmName());
            ps.setInt(4, member.getmDOB());
            ps.setString(5, member.getmAddress());
            ps.setInt(6, member.getmPN());
            ps.setInt(7, member.getmticket());
            ps.setInt(8, member.getmVIP());
            int r = ps.executeUpdate(); //실행 -> 저장
           
           
            if(r>0){
                System.out.println("가입 성공");   
                ok=true;
            }else{
                System.out.println("가입 실패");
            }
           
               
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }//insertMmeber
   
   
    /**회원정보 수정*/
    public boolean updateMember(member member){
        System.out.println("dto="+member.toString());
        boolean ok = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
           
            con = getConn();           
            String sql = "update member set name=?, tel=?, addr=?, birth=?, job=?, gender=?" +
                    ", email=?,intro=? "+ "where id=? and pwd=?";
           
            ps = con.prepareStatement(sql);


            ps.setString(1, member.getmID());
            ps.setString(2, member.getmPW());
            ps.setString(3, member.getmName());
            ps.setInt(4, member.getmDOB());
            ps.setString(5, member.getmAddress());
            ps.setInt(6, member.getmPN());
            ps.setInt(7, member.getmticket());
            ps.setInt(8, member.getmVIP());
            
            int r = ps.executeUpdate(); //실행 -> 수정
            // 1~n: 성공 , 0 : 실패
           
            if(r>0) ok = true; //수정이 성공되면 ok값을 true로 변경
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }
   
    /**회원정보 삭제 :
     *tip: 실무에서는 회원정보를 Delete 하지 않고 탈퇴여부만 체크한다.*/
    public boolean deleteMember(String id, String pwd){
       
        boolean ok =false ;
        Connection con =null;
        PreparedStatement ps =null;
       
        try {
            con = getConn();
            String sql = "delete from member where id=? and pwd=?";
           
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pwd);
            int r = ps.executeUpdate(); // 실행 -> 삭제
           
            if (r>0) ok=true; //삭제됨
           
        } catch (Exception e) {
            System.out.println(e + "-> 오류발생");
        }      
        return ok;
    }
   
   
    /**DB데이터 다시 불러오기*/   
    public void userSelectAll(DefaultTableModel model) {
       
       
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try {
            con = getConn();
            String sql = "select * from member order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            // DefaultTableModel에 있는 데이터 지우기
            for (int i = 0; i < model.getRowCount();) {
                model.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                       rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)};
 
                model.addRow(data);                
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> userSelectAll fail");
        } finally{
           
            if(rs!=null)
                try {
                    rs.close();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            if(ps!=null)
                try {
                    ps.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
}
