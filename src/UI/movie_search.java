package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import People.member;
import People.memberDB;
import UI.cinema_manage.MyActionListener;

public class movie_search extends JPanel {
	JButton movie_search, movie_reserv, logout, dropout, back;
	JLabel mov1, mov2, mov3, mov4, mov5, mov6, mov7, mov8, mov9, mov10;
	UI_Main ui;

	public movie_search(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_search.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		// ��ȭ �Ѱ��� 1�� / String���� �ٷ� �ֱ�!
		mov1 = new JLabel("����                             ����.... �̷���");
		mov1.setBounds(135, 257, 750, 20);
		mov1.setForeground(Color.WHITE);
		
		mov2 = new JLabel("����                             ����.... �̷���");
		mov2.setBounds(135, 298, 750, 20);
		mov2.setForeground(Color.WHITE);
		
		mov3 = new JLabel("����                             ����.... �̷���");
		mov3.setBounds(135, 339, 750, 20);
		mov3.setForeground(Color.WHITE);

		mov4 = new JLabel("����                             ����.... �̷���");
		mov4.setBounds(135, 380, 750, 20);
		mov4.setForeground(Color.WHITE);

		mov5 = new JLabel("����                             ����.... �̷���");
		mov5.setBounds(135, 421, 750, 20);
		mov5.setForeground(Color.WHITE);

		mov6 = new JLabel("����                             ����.... �̷���");
		mov6.setBounds(135, 462, 750, 20);
		mov6.setForeground(Color.WHITE);

		mov7 = new JLabel("����                             ����.... �̷���");
		mov7.setBounds(135, 503, 750, 20);
		mov7.setForeground(Color.WHITE);

		mov8 = new JLabel("����                             ����.... �̷���");
		mov8.setBounds(135, 544, 750, 20);
		mov8.setForeground(Color.WHITE);

		mov9 = new JLabel("����                             ����.... �̷���");
		mov9.setBounds(135, 585, 750, 20);
		mov9.setForeground(Color.WHITE);
		// ��ȭ �Ѱ��� 1�� / String���� �ٷ� �ֱ�!

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
		dropout = new JButton("ȸ��Ż��");
		dropout.setContentAreaFilled(false);
		dropout.setFocusPainted(false);
		dropout.setForeground(Color.WHITE);
		dropout.setBounds(770, 23, 200, 55);

		// ���ư��� ��ư �߰�
		back = new JButton("���ư���");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setForeground(Color.WHITE);
		back.setBounds(770, 23, 200, 55);

		add(mov1);
		add(mov2);
		add(mov3);
		add(mov4);
		add(mov5);
		add(mov6);
		add(mov7);
		add(mov8);
		add(mov9);
		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(dropout);
		add(back);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		dropout.addActionListener(new MyActionListener());
		back.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "��ȭ �˻�":
				System.out.println("��ȭ �˻� ��ư");
				break;
			case "��ȭ ����":
				System.out.println("��ȭ ���� ��ư");
				break;
			case "�α׾ƿ�":
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�޼���", JOptionPane.INFORMATION_MESSAGE);
				ui.update_UI("Login");
				break;
			case "ȸ��Ż��":
				try {
					JOptionPane.showMessageDialog(null, "Ż�� �Ͻðڽ��ϱ�?", "�޼���", JOptionPane.WARNING_MESSAGE);
					member member = ui.getmember();
					memberDB memberDB = new memberDB();
					memberDB.deleteMember(member.getmID(), member.getmPW());
					JOptionPane.showMessageDialog(null, "Ż�� ����!", "�޼���", JOptionPane.INFORMATION_MESSAGE);
					ui.update_UI("Login");
					break;
				} catch(Exception e1) {
					System.out.println(e1.toString());
					JOptionPane.showMessageDialog(null, "Ż�� �����߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					break;
				}
			case "���ư���":
				ui.update_UI("Main_Menu");
				break;
			}
		}
	}

}
