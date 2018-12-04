package UI;

import javax.swing.JFrame;

import People.member;

public class UI_Main extends JFrame {
	Login Login = new Login(this);
	member member = new member();
	final static int width = 1024;
	final static int height = 800;

	public UI_Main() {
		setTitle("영화 예약 시스템");
		// 닫기 버튼 시 종료
		add(Login);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(width, height);
		setVisible(true);
	}

	public void update_UI(String panelName) {
		Main_Menu Main_Menu = new Main_Menu(this);
		Join_UI Join_UI = new Join_UI(this);
		Main_Menu_admin Main_Menu_admin = new Main_Menu_admin(this);
		VIP_manage VIP_manage = new VIP_manage(this);
		movie_info_UI movie_info_UI = new movie_info_UI(this);
		movie_manage movie_manage = new movie_manage(this);
		cinema_manage cinema_manage = new cinema_manage(this);
		movie_search movie_search = new movie_search(this);
		movie_reservation movie_reservation = new movie_reservation(this);
		movie_delete movie_delete = new movie_delete(this);
		movie_change movie_change = new movie_change(this);
		cinema_delete cinema_delete = new cinema_delete(this);
		cinema_change cinema_change = new cinema_change(this);
		cinema_add cinema_add = new cinema_add(this);
		switch(panelName) {
		case "Login":
			getContentPane().removeAll();
			getContentPane().add(Login);
			revalidate();
			repaint();
			break;
		case "Join_UI":
			getContentPane().removeAll();
			getContentPane().add(Join_UI);
			revalidate();
			repaint();
			break;
		case "Main_Menu":
			getContentPane().removeAll();
			getContentPane().add(Main_Menu);
			revalidate();
			repaint();
			break;
		case "Main_Menu_admin":
			getContentPane().removeAll();
			getContentPane().add(Main_Menu_admin);
			revalidate();
			repaint();
			break;
		case "VIP_manage":
			getContentPane().removeAll();
			getContentPane().add(VIP_manage);
			revalidate();
			repaint();
			break;
		case "movie_manage":
			getContentPane().removeAll();
			getContentPane().add(movie_manage);
			revalidate();
			repaint();
			break;
		case "movie_info_UI":
			getContentPane().removeAll();
			getContentPane().add(movie_info_UI);
			revalidate();
			repaint();
			break;
		case "cinema_manage":
			getContentPane().removeAll();
			getContentPane().add(cinema_manage);
			revalidate();
			repaint();
			break;
		case "movie_search":
			getContentPane().removeAll();
			getContentPane().add(movie_search);
			revalidate();
			repaint();
			break;
		case "movie_reservation":
			getContentPane().removeAll();
			getContentPane().add(movie_reservation);
			revalidate();
			repaint();
			break;
		case "movie_delete":
			getContentPane().removeAll();
			getContentPane().add(movie_delete);
			revalidate();
			repaint();
			break;
		case "movie_change":
			getContentPane().removeAll();
			getContentPane().add(movie_change);
			revalidate();
			repaint();
			break;
		case "cinema_delete":
			getContentPane().removeAll();
			getContentPane().add(cinema_delete);
			revalidate();
			repaint();
			break;
		case "cinema_change":
			getContentPane().removeAll();
			getContentPane().add(cinema_add);
			revalidate();
			repaint();
			break;
		case "cinema_add":
			getContentPane().removeAll();
			getContentPane().add(cinema_add);
			revalidate();
			repaint();
			break;
		}
	}
	
	public void setmember(member member) {
		this.member = member;
	}
	
	public member getmember() {
		return member;
	}
}
