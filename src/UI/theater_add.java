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
import javax.swing.JTextField;

import Cinema.cinemaDB;
import Seat.Seat;
import Seat.seatDB;
import Theater.TheaterDB;
import Theater.theater;

public class theater_add extends JPanel {
	JTextField SeatNum, StartTime;
	UI_Main ui;
	JButton ok;

	public theater_add(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/theater_add.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// �¼� �� �ʵ�
		SeatNum = new JTextField(20);
		SeatNum.setBounds(360, 198, 470, 55);
		SeatNum.setOpaque(false);
		SeatNum.setForeground(Color.WHITE);
		SeatNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		SeatNum.setCaretColor(Color.white);

		// ���� �ð� �ʵ�
		StartTime = new JTextField(20);
		StartTime.setBounds(360, 275, 470, 55);
		StartTime.setOpaque(false);
		StartTime.setForeground(Color.WHITE);
		StartTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		StartTime.setCaretColor(Color.white);

		// �����ư �߰�
		ok = new JButton("����");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(307, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		add(SeatNum);
		add(StartTime);
		add(ok);
		add(lblNewLabel);
		SeatNum.addActionListener(new MyActionListener());
		StartTime.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "����":

				if (SeatNum.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�¼� ���� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(SeatNum.getText()) != true) {
						JOptionPane.showMessageDialog(null, "�¼� ���� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				if (StartTime.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "���� �ð��� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(StartTime.getText()) != true) {
						JOptionPane.showMessageDialog(null, "���� �ð��� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (StartTime.getText().length() != 2) {
							JOptionPane.showMessageDialog(null, "���� �ð��� 2�ڸ��� �Է����ּ���.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					TheaterDB TheaterDB = new TheaterDB();
					Vector TheaterList = TheaterDB.getTheaterList();
					for (int i = 0; i < 3; i++) {
						theater theater = new theater();
						theater.settCinemaName(ui.getcinema().getcNAME());
						theater.settMovieTitle("null");
						theater.settSeatNum(SeatNum.getText());
						theater.settStartTime(StartTime.getText());
						theater.settTheaterID(String.valueOf(TheaterList.size() + (i + 1)));
						boolean torf = TheaterDB.insertTheater(theater);
						if (torf) {
							// �¼� �߰�
							seatDB seatDB = new seatDB();
							Seat Seat = new Seat();
							Seat.setsTheaterID(String.valueOf(TheaterList.size() + (i + 1)));
							boolean torf2 = seatDB.insertSeat(Seat, Integer.parseInt(SeatNum.getText()));
							if(torf2) {
								JOptionPane.showMessageDialog(null, i + 1 + "��° �󿵰��� ��� �Ǿ����ϴ�!", "�޼���",
										JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "�󿵰� �¼� ����� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
								break;
							}
						} else
							JOptionPane.showMessageDialog(null, "�󿵰������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�󿵰������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.update_UI("cinema_manage");
				break;
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

}
