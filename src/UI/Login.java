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
		// ���̾ƿ� ����
		setLayout(null);

		// �г�1
		// �̹��� �޾ƿ���
		try {
			img = ImageIO.read(new File("Resource/login.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}
		////////////////////////////////////////////////////////////////////////// ��ǥ������

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());
		la.setBounds(0, 0, 200, 30);
		add(la);

		////////////////////////////////////////////////////////////////////////////

		// �α��� �ʵ�
		loginTextField = new JTextField(10);
		loginTextField.setBounds(460, 310, 300, 40);
		add(loginTextField);

		// �н�����
		passwordField = new JPasswordField(10);
		passwordField.setBounds(460, 420, 300, 40);
		add(passwordField);

		// �α��ι�ư �߰�
		bt = new JButton("�α���");
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