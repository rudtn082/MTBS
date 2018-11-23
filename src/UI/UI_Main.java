package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		if (panelName.equals("Login")) {
			getContentPane().removeAll();
			getContentPane().add(Login);
			revalidate();
			repaint();
		} else if (panelName.equals("Join_UI")) {
			getContentPane().removeAll();
			getContentPane().add(Join_UI);
			revalidate();
			repaint();
		} else if (panelName.equals("Main_Menu")) {
			getContentPane().removeAll();
			getContentPane().add(Main_Menu);
			revalidate();
			repaint();
		} else if (panelName.equals("Main_Menu_admin")) {
			getContentPane().removeAll();
			getContentPane().add(Main_Menu_admin);
			revalidate();
			repaint();
		}
	}
	
	public void setmember(member member) {
		this.member = member;
	}
	
	public member getmember() {
		return member;
	}
}
