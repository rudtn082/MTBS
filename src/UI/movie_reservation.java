package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cinema.cinemaDB;
import Movie.movieDB;
import People.member;
import People.memberDB;
import Seat.seatDB;

public class movie_reservation extends JPanel {
	JButton movie_search, movie_reserv, logout, memberInfo;
	UI_Main ui;
	JButton ok, cancel;
	JComboBox seatCombo;

	public movie_reservation(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// �޺� �ڽ� ===============================================
		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		movieDB movieDB = new movieDB();
		Vector v = movieDB.getMovieList();

		// �޺� �ڽ� �� ���� ���� �޴� ���� (��ȭ)
		String[] temp = new String[v.size()];
		ArrayList<String> list = new ArrayList<String>();

		// ��ȭ �ߺ� ����
		for (int i = 0; i < v.size(); i++) {
			int j = 0;
			for (j = 0; j < i; j++) {
				if (list.get(j).equals((String) ((Vector) v.get(i)).get(2)))
					break;
			}
			if (j == i) {
				list.add((String) ((Vector) v.get(i)).get(2));
			}
		}

		String[] movie = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			movie[i] = list.get(i);
		}

		JComboBox movieCombo = new JComboBox();
		movieCombo.setBounds(390, 210, 200, 30);
		movieCombo.setOpaque(false);
		add(movieCombo);
		movieCombo.setModel(new DefaultComboBoxModel(movie));

		// �޺� �ڽ� �⵵ �� ����
		movieCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ev) {
				seatDB seatDB = new seatDB();
				String[] seat = null;
				Object movieName = ev.getItem();
				Vector v3 = seatDB.getSeatList(movieName.toString());
				// TODO Auto-generated method stub
				if (v3 != null) {
					seat = new String[v3.size()];

					for (int i = 0; i < v3.size(); i++) {
						seat[i] = ((String) ((Vector) v3.get(i)).get(0));
					}
				}
				System.out.println(v3);

				seatCombo.setModel(new DefaultComboBoxModel(seat));
				seatCombo.revalidate();
				seatCombo.repaint();
			}
		});
		// ===========================================================
		// ��ȭ�� ���� �޺� �ڽ� ���� �� �߰�
		cinemaDB cinemaDB = new cinemaDB();
		Vector v2 = cinemaDB.getCinemaList();

		String[] cinema = new String[v2.size()];

		for (int i = 0; i < v2.size(); i++) {
			cinema[i] = (String) ((Vector) v2.get(i)).get(0);
		}

		JComboBox cinemaCombo = new JComboBox();
		cinemaCombo.setBounds(390, 280, 200, 30);
		cinemaCombo.setOpaque(false);
		add(cinemaCombo);
		cinemaCombo.setModel(new DefaultComboBoxModel(cinema));
		// ===========================================================
		// �¼� ���� �޺� �ڽ� ���� �� �߰�
		seatDB seatDB = new seatDB();
		String[] seat = null;
		Object movieName = movieCombo.getSelectedItem();
		Vector v3 = seatDB.getSeatList(movieName.toString());

		if (v3 != null) {
			seat = new String[v3.size()];

			for (int i = 0; i < v3.size(); i++) {
				seat[i] = ((String) ((Vector) v3.get(i)).get(0));
			}
		}

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		seatCombo = new JComboBox();
		seatCombo.setBounds(390, 355, 200, 30);
		seatCombo.setOpaque(false);
		seatCombo.setModel(new DefaultComboBoxModel(seat));
		add(seatCombo);
		// ===========================================================
		// ��¥ ���� �޺� �ڽ� ���� �� �߰�
		// �޺� �ڽ� �⵵ �� ��������
		String[] date = new String[30];

		for (int i = 0; i < 30; i++) {
			date[i] = String.valueOf(i + 1);
		}

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		JComboBox dateCombo = new JComboBox();
		dateCombo.setBounds(390, 430, 200, 30);
		dateCombo.setOpaque(false);
		add(dateCombo);
		dateCombo.setModel(new DefaultComboBoxModel(date));
		// ===========================================================
		// �ð� ���� �޺� �ڽ� ���� �� �߰�
		// �޺� �ڽ� �⵵ �� ��������
		String[] time = new String[5];

		time[0] = "10��";
		time[1] = "12��";
		time[2] = "14��";
		time[3] = "16��";
		time[4] = "18��";

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		JComboBox timeCombo = new JComboBox();
		timeCombo.setBounds(390, 505, 200, 30);
		timeCombo.setOpaque(false);
		add(timeCombo);
		timeCombo.setModel(new DefaultComboBoxModel(time));
		// �޺� �ڽ� ====================================================

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

		// �����ư �߰�
		ok = new JButton("����");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// ��ҹ�ư �߰�
		cancel = new JButton("���");
		cancel.setBackground(new Color(114, 137, 218));
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(510, 647, 350, 60);
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(ok);
		add(cancel);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
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
			case "����":
				System.out.println("�����ư");
				break;
			case "���":
				ui.update_UI("Main_Menu");
			}
		}
	}

}
