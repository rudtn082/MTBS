package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class movie_manage extends JPanel {
	JButton movie_info, movie_modify, movie_delete, back;
	UI_Main ui;

	public movie_manage(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_manage.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		// ��ȭ ���� ��� ��ư �߰�
		movie_info = new JButton("��ȭ ���� ���");
		movie_info.setContentAreaFilled(false);
		movie_info.setFocusPainted(false);
		movie_info.setForeground(Color.WHITE);
		movie_info.setBounds(20, 23, 200, 55);
		
		// ��ȭ ���� ��ư �߰�
		movie_modify = new JButton("��ȭ ����");
		movie_modify.setContentAreaFilled(false);
		movie_modify.setFocusPainted(false);
		movie_modify.setForeground(Color.WHITE);
		movie_modify.setBounds(270, 23, 200, 55);

		// ��ȭ ���� ��ư �߰�
		movie_delete = new JButton("��ȭ ����");
		movie_delete.setContentAreaFilled(false);
		movie_delete.setFocusPainted(false);
		movie_delete.setForeground(Color.WHITE);
		movie_delete.setBounds(520, 23, 200, 55);
		
		// ���ư��� ��ư �߰�
		back = new JButton("���ư���");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setForeground(Color.WHITE);
		back.setBounds(770, 23, 200, 55);

		add(movie_info);
		add(movie_modify);
		add(movie_delete);
		add(back);
		add(lblNewLabel);
		movie_info.addActionListener(new MyActionListener());
		movie_modify.addActionListener(new MyActionListener());
		movie_delete.addActionListener(new MyActionListener());
		back.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "��ȭ ���� ���":
				ui.update_UI("movie_info_UI");
				break;
			case "��ȭ ����":
				ui.update_UI("movie_change");
				break;
			case "��ȭ ����":
				ui.update_UI("movie_delete");
				break;
			case "���ư���":
				ui.update_UI("Main_Menu_admin");
				break;
			}
		}
	}

}
