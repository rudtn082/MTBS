package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Booking.book;
import Booking.bookDB;
import Movie.movie;
import Movie.movieDB;
import Payment.pay;
import Payment.payDB;
import People.member;
import People.memberDB;
import UI.movie_reservation.MyActionListener;

public class cancel_reservation extends JPanel {
	UI_Main ui;
	JTextField bookNO;
	JButton ok, re;

	public cancel_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cancel_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		bookNO = new JTextField(20);
		bookNO.setBounds(605, 355, 200, 30);
		bookNO.setOpaque(false);
		bookNO.setForeground(Color.WHITE);
		bookNO.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		bookNO.setCaretColor(Color.white);

		ok = new JButton("예약 취소");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// 취소버튼 추가
		re = new JButton("돌아가기");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(510, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		add(bookNO);
		add(ok);
		add(re);
		add(lblNewLabel);

		ok.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		boolean isCancel;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "예약 취소":
				// 포인트 사용 예외처리
				if (!bookNO.getText().isEmpty()) {
					if (isStringDouble(bookNO.getText()) == false) {
						JOptionPane.showMessageDialog(null, "예약번호는 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						bookDB bookDB = new bookDB();
						member member = ui.getmember();
						String mID = member.getmID();
						isCancel = bookDB.deleteBook(bookNO.getText(), mID);
					}
				}
			case "돌아가기":
				ui.update_UI("Main_Menu");
				break;
			default:
				if (isCancel == true) {
					JOptionPane.showMessageDialog(null, "취소가 완료되었습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
				} else if (isCancel == false) {
					JOptionPane.showMessageDialog(null, "취소를 실패하였습니다", "메세지", JOptionPane.INFORMATION_MESSAGE);
				}

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