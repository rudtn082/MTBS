package UI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel {
	JTextField loginTextField;
	JPasswordField passwordField;
	BufferedImage img = null;
	JButton bt;
	JLabel Id, passwd;
	JLabel la = new JLabel("No Mouse Event");
	UI_Main ui;

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public Login(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		// 패널1
		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("Resource/login.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}
		////////////////////////////////////////////////////////////////////////// 좌표볼려구

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());
		la.setBounds(0, 0, 200, 30);
		add(la);

		////////////////////////////////////////////////////////////////////////////

		// 로그인 필드
		loginTextField = new JTextField(10);
		loginTextField.setBounds(460, 310, 300, 40);
		add(loginTextField);

		// 패스워드
		passwordField = new JPasswordField(10);
		passwordField.setBounds(460, 420, 300, 40);
		add(passwordField);

		// 로그인버튼 추가
		bt = new JButton("로그인");
		bt.setBounds(430, 528, 150, 40);
		add(bt);

		bt.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("aa");
			// ui.update_UI("Main_Menu");
		}
	}

	class MyMouseListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			la.setText("MouseClicked(" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			la.setText("MousePressed(" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			la.setText("MouseReleased(" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			la.setText("MouseDragged(" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			la.setText("MouseMoved(" + e.getX() + "," + e.getY() + ")");
		}

	}
}