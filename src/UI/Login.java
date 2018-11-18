package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
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

	public Login(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(""); 
	    lblNewLabel.setIcon(new ImageIcon("Resource/login.png")); 
	    lblNewLabel.setBounds(0, 0, 1024, 768);
		
		////////////////////////////////////////////////////////////////////////// 좌표볼려구

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());
		la.setBounds(0, 0, 200, 30);
		la.setForeground(Color.WHITE);

		////////////////////////////////////////////////////////////////////////////

		// 아이디 필드
		loginTextField = new JTextField(10);	
		loginTextField.setBounds(435, 260, 400, 60);
		loginTextField.setOpaque(false);		
		loginTextField.setForeground(Color.WHITE);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// 패스워드 필드
		passwordField = new JPasswordField(10);
		passwordField.setBounds(435, 415, 400, 60);
		passwordField.setOpaque(false);		
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// 로그인버튼 추가
		bt = new JButton("로그인");
		bt.setBackground(new Color(114, 137, 218));
		bt.setForeground(Color.WHITE);
		bt.setBounds(435, 500, 400, 60);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);		
		
		add(loginTextField);
		add(la);
		add(passwordField);
		add(bt);
		add(lblNewLabel);
		bt.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("aa");
			ui.update_UI("Main_Menu");
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