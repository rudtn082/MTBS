package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.movieDB;

public class movie_search extends JPanel {
	JButton movie_search, movie_reserv, logout, memberInfo, re;
	UI_Main ui;

	public movie_search(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_search.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		Vector allMovie = new Vector();
		movieDB movieDB = new movieDB();
		allMovie = movieDB.getMovieList();

		String[][] a = new String[allMovie.size()][8];// 6
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = new String();
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = (String) ((Vector) allMovie.get(i)).get(j);
			}
		}

		// ��ȭ
		JLabel[] row = new JLabel[allMovie.size()];
		JLabel[] row2 = new JLabel[allMovie.size()];
		JLabel[] row3 = new JLabel[allMovie.size()];
		JLabel[] row4 = new JLabel[allMovie.size()];
		JLabel[] row5 = new JLabel[allMovie.size()];
		JLabel[] row6 = new JLabel[allMovie.size()];
		for (int i = 0; i < a.length; i++) {

			int j = 2;
			row[i] = new JLabel(a[i][j]);
			row[i].setBounds(130, 257 + (i * 41), 100, 20);
			row[i].setForeground(Color.WHITE);

			row2[i] = new JLabel(a[i][j + 1]);
			row2[i].setBounds(260, 257 + (i * 41), 100, 20);
			row2[i].setForeground(Color.WHITE);

			row3[i] = new JLabel(a[i][j + 2]);
			row3[i].setBounds(390, 257 + (i * 41), 100, 20);
			row3[i].setForeground(Color.WHITE);

			row4[i] = new JLabel(a[i][j + 3]);
			row4[i].setBounds(520, 257 + (i * 41), 100, 20);
			row4[i].setForeground(Color.WHITE);

			row5[i] = new JLabel(a[i][j + 4]);
			row5[i].setBounds(650, 257 + (i * 41), 100, 20);
			row5[i].setForeground(Color.WHITE);

			row6[i] = new JLabel(a[i][j + 5]);
			row6[i].setBounds(780, 257 + (i * 41), 100, 20);
			row6[i].setForeground(Color.WHITE);
			add(row[i]);
			add(row2[i]);
			add(row3[i]);
			add(row4[i]);
			add(row5[i]);
			add(row6[i]);

		}

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

		// ȸ������ ��ư �߰�
		memberInfo = new JButton("ȸ������");
		memberInfo.setContentAreaFilled(false);
		memberInfo.setFocusPainted(false);
		memberInfo.setForeground(Color.WHITE);
		memberInfo.setBounds(770, 23, 200, 55);

		// ���ư��� ��ư �߰�
		re = new JButton("���ư���");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(307, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(re);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());
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
			case "���ư���":
				ui.update_UI("Main_Menu");
			}
		}
	}

}