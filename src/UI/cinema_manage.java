package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cinema_manage extends JPanel {
	JButton cinema_info, cinema_modify, cinema_delete, back;
	UI_Main ui;

	public cinema_manage(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinema_manage.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		// ��ȭ�� ���� ��� ��ư �߰�
		cinema_info = new JButton("��ȭ�� ���� ���");
		cinema_info.setContentAreaFilled(false);
		cinema_info.setFocusPainted(false);
		cinema_info.setForeground(Color.WHITE);
		cinema_info.setBounds(20, 23, 200, 55);
		
		// ��ȭ�� ���� ��ư �߰�
		cinema_modify = new JButton("��ȭ�� ����");
		cinema_modify.setContentAreaFilled(false);
		cinema_modify.setFocusPainted(false);
		cinema_modify.setForeground(Color.WHITE);
		cinema_modify.setBounds(270, 23, 200, 55);

		// ��ȭ�� ���� ��ư �߰�
		cinema_delete = new JButton("��ȭ�� ����");
		cinema_delete.setContentAreaFilled(false);
		cinema_delete.setFocusPainted(false);
		cinema_delete.setForeground(Color.WHITE);
		cinema_delete.setBounds(520, 23, 200, 55);
		
		// ���ư��� ��ư �߰�
		back = new JButton("���ư���");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setForeground(Color.WHITE);
		back.setBounds(770, 23, 200, 55);

		add(cinema_info);
		add(cinema_modify);
		add(cinema_delete);
		add(back);
		add(lblNewLabel);
		cinema_info.addActionListener(new MyActionListener());
		cinema_modify.addActionListener(new MyActionListener());
		cinema_delete.addActionListener(new MyActionListener());
		back.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "��ȭ�� ���� ���":
				ui.update_UI("cinema_add");
				break;
			case "��ȭ�� ����":
				ui.update_UI("cinema_change");
				break;
			case "��ȭ�� ����":
				ui.update_UI("cinema_delete");
				break;
			case "���ư���":
				ui.update_UI("Main_Menu_admin");
				break;
			}
		}
	}


}
