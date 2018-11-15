package MTBS;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Interface extends JFrame {
	final static int width = 1280;
	final static int height = 768;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton bt;
	JLabel Id, passwd;

	public Interface() {
		super("영화 예약 시스템 1조");

		setSize(1280, 768);
		// 닫기 버튼 시 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(50, 50, width, height);
		layeredPane.setLayout(null);

		// 로그인 필드
		loginTextField = new JTextField(10);
		loginTextField.setBounds(440, 214, 200, 30);
		layeredPane.add(loginTextField);
		
		Id = new JLabel("아이디");
		Id.setBounds(380, 214, 200, 30);
		layeredPane.add(Id);

		// 패스워드
		passwordField = new JPasswordField(10);
		passwordField.setBounds(440, 254, 200, 30);
		layeredPane.add(passwordField);

		passwd = new JLabel("패스워드");
		passwd.setBounds(380, 254, 200, 30);
		layeredPane.add(passwd);

		// 로그인버튼 추가
		bt = new JButton("로그인");
		bt.setBounds(500, 300, 80, 40);
		layeredPane.add(bt);
		
		add(layeredPane);
		setVisible(true);
	}
}