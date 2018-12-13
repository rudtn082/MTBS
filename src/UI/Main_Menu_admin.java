package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main_Menu_admin extends JPanel {
	JButton movie_manage, cinema_manage, VIP_manage, ticket_manage;
	UI_Main ui;

	public Main_Menu_admin(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/main_menu.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ ���� ��ư �߰�
		movie_manage = new JButton("��ȭ ����");
		movie_manage.setContentAreaFilled(false);
		movie_manage.setFocusPainted(false);
		movie_manage.setForeground(Color.WHITE);
		movie_manage.setBounds(20, 23, 200, 55);
		
		// ��ȭ�� ���� ��ư �߰�
		cinema_manage = new JButton("��ȭ�� ����");
		cinema_manage.setContentAreaFilled(false);
		cinema_manage.setFocusPainted(false);
		cinema_manage.setForeground(Color.WHITE);
		cinema_manage.setBounds(270, 23, 200, 55);

		// VIP�� ���� ��ư �߰�
		VIP_manage = new JButton("VIP�� ����");
		VIP_manage.setContentAreaFilled(false);
		VIP_manage.setFocusPainted(false);
		VIP_manage.setForeground(Color.WHITE);
		VIP_manage.setBounds(520, 23, 200, 55);
		
		// ��ȭ Ƽ�� ���� ��ư �߰�
		ticket_manage = new JButton("��ȭ Ƽ�� ����");
		ticket_manage.setContentAreaFilled(false);
		ticket_manage.setFocusPainted(false);
		ticket_manage.setForeground(Color.WHITE);
		ticket_manage.setBounds(770, 23, 200, 55);

		add(movie_manage);
		add(cinema_manage);
		add(VIP_manage);
		add(ticket_manage);
		add(lblNewLabel);
		movie_manage.addActionListener(new MyActionListener());
		cinema_manage.addActionListener(new MyActionListener());
		VIP_manage.addActionListener(new MyActionListener());
		ticket_manage.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "��ȭ ����":
				ui.update_UI("movie_manage");
				break;
			case "��ȭ�� ����":
				ui.update_UI("cinema_manage");
				break;
			case "VIP�� ����":
				ui.update_UI("VIP_manage");
				break;
			case "��ȭ Ƽ�� ����":
				ui.update_UI("ticket_issue");
				break;
			}
		}
	}
}
