package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI_Main extends JFrame {
	final static int width = 1024;
	final static int height = 768;
	public Login Login = null;
	public Main_Menu Main_Menu = null;

	public UI_Main() {
		setTitle("로그인 테스트");
		// 닫기 버튼 시 종료
		Login = new Login(this);
		add(Login);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(width, height);
		setVisible(true);
	}

	public void update_UI(String panelName) {
		if (panelName.equals("Login")) {
			getContentPane().removeAll();
			getContentPane().add(Login);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(Main_Menu);
			revalidate();
			repaint();
		}
	}

}
