package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import People.Admin;
import People.member;
import People.memberDB;

public class Login extends JPanel {
	JTextField loginTextField;
	JPasswordField passwordField;
	BufferedImage img = null;
	JButton bt, join;
	UI_Main ui;

	public Login(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/login.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ���̵� �ʵ�
		loginTextField = new JTextField(10);
		loginTextField.setBounds(435, 260, 400, 60);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.WHITE);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		loginTextField.setCaretColor(Color.white);
		// �н����� �ʵ�
		passwordField = new JPasswordField(10);
		passwordField.setBounds(435, 415, 400, 60);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		passwordField.setCaretColor(Color.white);

		// �α��ι�ư �߰�
		bt = new JButton("�α���");
		bt.setBackground(new Color(114, 137, 218));
		bt.setForeground(Color.WHITE);
		bt.setBounds(435, 500, 400, 60);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);

		// ���Թ�ư �߰�
		join = new JButton(" ");
		join.setBounds(435, 590, 150, 25);
		join.setContentAreaFilled(false);
		join.setBorderPainted(false);
		join.setFocusPainted(false);

		add(loginTextField);
		add(passwordField);
		add(bt);
		add(join);
		add(lblNewLabel);
		join.addActionListener(new MyActionListener());
		bt.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "�α���":
				Admin admin = new Admin();
				// admin�Է����� ��
				if (loginTextField.getText().equals(admin.getID())) {
					if (String.valueOf(passwordField.getPassword()).equals(admin.getPW())) {
						ui.update_UI("Main_Menu_admin");
						break;
					}
				}
				// ȸ���� �Է����� ��
				else {
					try {
						memberDB memberDB = new memberDB();
						member member = memberDB.getMemberDTO(loginTextField.getText());
						String pw = member.getmPW();
						if (pw.equals(passwordField.getText())) {
							ui.setmember(member);
							ui.update_UI("Main_Menu");
							break;
						}
					} catch (Exception e1) {
						System.out.println(e1.toString());
						JOptionPane.showMessageDialog(null, "�α��ο� �����Ͽ����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						break;
					}
					JOptionPane.showMessageDialog(null, "���̵�, �н����带 Ȯ�� ���ּ���.", "�޼���", JOptionPane.WARNING_MESSAGE);
				}
				break;
			case " ":
				ui.update_UI("Join_UI");
				break;
			}
		}
	}
}