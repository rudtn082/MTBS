package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main_Menu extends JPanel {
	JButton movie_search, movie_reserv, logout, memberInfo;
	UI_Main ui;

	public Main_Menu(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/main_menu.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ �˻� ��ư �߰�
		movie_search = new JButton("��ȭ �˻�");
		movie_search.setContentAreaFilled(false);
		movie_search.setFocusPainted(false);
		movie_search.setForeground(Color.WHITE);
		movie_search.setBounds(20, 23, 200, 55);

		// ��ȭ ���� ��ư �߰�
		movie_reserv = new JButton("��ȭ ����");
		movie_reserv.setContentAreaFilled(false);
		movie_reserv.setFocusPainted(false);
		movie_reserv.setForeground(Color.WHITE);
		movie_reserv.setBounds(270, 23, 200, 55);

		// �α׾ƿ� ��ư �߰�
		logout = new JButton("�α׾ƿ�");
		logout.setContentAreaFilled(false);
		logout.setFocusPainted(false);
		logout.setForeground(Color.WHITE);
		logout.setBounds(520, 23, 200, 55);

		// ȸ��Ż�� ��ư �߰�
		memberInfo = new JButton("ȸ������");
		memberInfo.setContentAreaFilled(false);
		memberInfo.setFocusPainted(false);
		memberInfo.setForeground(Color.WHITE);
		memberInfo.setBounds(770, 23, 200, 55);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "��ȭ �˻�":
				ui.update_UI("movie_search");
				break;
			case "��ȭ ����":
				ui.update_UI("movie_reservation");
				break;
			case "�α׾ƿ�":
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);
				ui.update_UI("Login");
				break;
			case "ȸ������":
				ui.update_UI("member_Main");
			}
		}
	}
}
