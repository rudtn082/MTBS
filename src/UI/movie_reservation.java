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
import javax.swing.JTextField;

import Booking.book;
import Booking.bookDB;
import Cinema.cinemaDB;
import Movie.movieDB;
import Payment.pay;
import Payment.payDB;
import People.member;
import People.memberDB;
import Seat.seatDB;
import Theater.TheaterDB;
import Theater.theater;

public class movie_reservation extends JPanel {
	JTextField usePoint;
	JButton movie_search, movie_reserv, logout, memberInfo;
	UI_Main ui;
	JButton ok, cancel, cancel_reservation, check_reservation;
	JComboBox movieCombo, seatCombo, cinemaCombo, dateCombo, timeCombo, typeCombo;
	member member;

	public movie_reservation(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// �ɹ� �������� //
		String mid = ui.getmember().getmID();
		memberDB memberDB = new memberDB();
		member = memberDB.getMemberDTO(mid);

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

		movieCombo = new JComboBox();
		movieCombo.setBounds(220, 210, 200, 30);
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

		cinemaCombo = new JComboBox();
		cinemaCombo.setBounds(220, 280, 200, 30);
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
		seatCombo.setBounds(220, 355, 200, 30);
		seatCombo.setOpaque(false);
		add(seatCombo);
		seatCombo.setModel(new DefaultComboBoxModel(seat));
		
		
		String[] time = new String[1];
		seatCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ev) {
				//���¼���ȣ�� �ҷ���
				Object seatThNum = seatCombo.getSelectedItem();
				String seatNum_str = seatThNum.toString();
				
				//���¼���ȣ�� �Ǿ��ڸ��� �Ľ��ؼ� �󿵰� ���̵� �˾Ƴ�
				String seatTheaterID = seatNum_str.substring(0, 1);
				TheaterDB tmpTheaterDB = new TheaterDB();
				theater tmpTheater = tmpTheaterDB.getTheaterDTO(seatTheaterID);
				
				time[0] = tmpTheater.gettStartTime();

				timeCombo.setModel(new DefaultComboBoxModel(time));
				timeCombo.revalidate();
				timeCombo.repaint();
			}
		});
		// ===========================================================
		// ��¥ ���� �޺� �ڽ� ���� �� �߰�
		// �޺� �ڽ� �⵵ �� ��������
		String[] date = new String[30];

		for (int i = 0; i < 30; i++) {
			date[i] = String.valueOf(i + 1);
		}

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		dateCombo = new JComboBox();
		dateCombo.setBounds(220, 430, 200, 30);
		dateCombo.setOpaque(false);
		add(dateCombo);
		dateCombo.setModel(new DefaultComboBoxModel(date));
		// ===========================================================
		// �ð� ���� �޺� �ڽ� ���� �� �߰�
		// �޺� �ڽ� �⵵ �� ��������
		
		
		/*//���¼���ȣ�� �ҷ���
		Object seatThNum = seatCombo.getSelectedItem();
		String seatNum_str = seatThNum.toString();
		
		//���¼���ȣ�� �Ǿ��ڸ��� �Ľ��ؼ� �󿵰� ���̵� �˾Ƴ�
		String seatTheaterID = seatNum_str.substring(0, 1);
		TheaterDB tmpTheaterDB = new TheaterDB();
		theater tmpTheater = tmpTheaterDB.getTheaterDTO(seatTheaterID);
		
		time[0] = tmpTheater.gettStartTime();*/
		

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		timeCombo = new JComboBox();
		timeCombo.setBounds(220, 505, 200, 30);
		timeCombo.setOpaque(false);
		add(timeCombo);
		timeCombo.setModel(new DefaultComboBoxModel(seat));
		
		
	
		
		// ===========================================================
		// ���� ���� ���� �޺� �ڽ� ���� �� �߰�
		// �޺� �ڽ� �⵵ �� ��������
		String[] type = new String[2];

		type[0] = "���ͳ� ����";
		type[1] = "���� ����";

		// ��ȭ ���� �޺� �ڽ� ���� �� �߰�
		typeCombo = new JComboBox();
		typeCombo.setBounds(580, 210, 200, 30);
		typeCombo.setOpaque(false);
		add(typeCombo);
		typeCombo.setModel(new DefaultComboBoxModel(type));
		// �޺� �ڽ� ====================================================
		// ����Ʈ ��Ȳ
		JLabel Point = new JLabel(member.getMpoint());
		Point.setBounds(605, 280, 200, 30);
		Point.setForeground(Color.WHITE);
		add(Point);

		// ����Ʈ ��� �ʵ�
		usePoint = new JTextField(20);
		usePoint.setBounds(605, 355, 200, 30);
		usePoint.setOpaque(false);
		usePoint.setForeground(Color.WHITE);
		usePoint.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		usePoint.setCaretColor(Color.white);
		add(usePoint);

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
		ok = new JButton("���� �� ����");
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

		// ��ȭ��ҹ�ư �߰�
		cancel_reservation = new JButton("�������");
		cancel_reservation.setBackground(new Color(114, 137, 218));
		cancel_reservation.setForeground(Color.WHITE);
		cancel_reservation.setBounds(510, 495, 150, 40);
		cancel_reservation.setBorderPainted(false);
		cancel_reservation.setFocusPainted(false);

		// ��ȭ����Ȯ�� ��ư �߰�
		check_reservation = new JButton("����Ȯ��");
		check_reservation.setBackground(new Color(114, 137, 218));
		check_reservation.setForeground(Color.WHITE);
		check_reservation.setBounds(510, 425, 150, 40);
		check_reservation.setBorderPainted(false);
		check_reservation.setFocusPainted(false);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(ok);
		add(cancel);
		add(cancel_reservation);
		add(check_reservation);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
		cancel_reservation.addActionListener(new MyActionListener());
		check_reservation.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "����Ȯ��":
				ui.update_UI("check_reservation");
				break;
			case "�������":
				ui.update_UI("cancel_reservation");
				break;
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
			case "���� �� ����":
				// ����Ʈ ��� ����ó��
				if (!usePoint.getText().isEmpty()) {
					if (isStringDouble(usePoint.getText()) == false) {
						JOptionPane.showMessageDialog(null, "��������� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (Integer.parseInt(usePoint.getText()) < 1000
								|| Integer.parseInt(member.getMpoint()) < 1000) {
							JOptionPane.showMessageDialog(null, "����Ʈ�� 1000�� �̻� ��� �����մϴ�.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					bookDB bookDB = new bookDB();
					payDB payDB = new payDB();
					book book = new book();
					pay pay = new pay();

					// ����
					ArrayList array = bookDB.getbookList();
					book.setbookNo(String.valueOf(array.size() + 1));
					book.setmID(member.getmID());
					book.setTheaterID(((String) seatCombo.getSelectedItem()).substring(0, 1));
					boolean torf1 = bookDB.insertbook(book);

					// ����
					ArrayList array2 = payDB.getpayList();
					pay.setpayNo(String.valueOf(array2.size() + 1));
					pay.setbookNo(String.valueOf(array.size() + 1));
					member.setmticket(String.valueOf(Integer.parseInt(member.getmticket()) + 1)); // ���� Ƚ�� +1
					memberDB memberDB = new memberDB();
					memberDB.updateMember(member);
					if (!usePoint.getText().isEmpty()) {
						pay.setprice(String.valueOf(10000 - Integer.parseInt(usePoint.getText())));
						member.setMpoint(String
								.valueOf(Integer.parseInt(member.getMpoint()) - Integer.parseInt(usePoint.getText()))); // ����Ʈ
																														// ����
						boolean torf3 = memberDB.updateMember(member);

						if (torf3)
							JOptionPane.showMessageDialog(null, "����Ʈ�� �����Ǿ����ϴ�.", "�޼���",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "����Ʈ ������ �����Ͽ����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					} else {
						pay.setprice("10000");
					}

					memberDB.updateMember(member);
					// ���ͳ� ���� �� ���(�ٷ� ���� �� ���� �Ϸ�)
					if (typeCombo.getSelectedItem().equals("���ͳ� ����")) {

						pay.setpayMethod("1");
						boolean torf2 = payDB.insertpay(pay);

						if (torf1 && torf2)
							JOptionPane.showMessageDialog(null, "���ͳ� ������ �Ϸ�Ǿ����ϴ�!", "�޼���",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "���ͳ� ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					}
					// ���� ���� �� ���(������ ����)
					else {

						pay.setpayMethod("2");
						boolean torf2 = payDB.insertpay(pay);

						if (torf1 && torf2)
							JOptionPane.showMessageDialog(null, "���� ������ �Ϸ�Ǿ����ϴ�!", "�޼���",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "���� ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "���� �Ǵ� ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.setmember(member);
				ui.update_UI("Main_Menu");
				break;
			case "���":
				ui.update_UI("Main_Menu");
			}
		}
	}

	public boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
