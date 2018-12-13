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
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cancel_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		bookNO = new JTextField(20);
		bookNO.setBounds(380, 355, 200, 30);
		bookNO.setOpaque(false);
		bookNO.setForeground(Color.WHITE);
		bookNO.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
		bookNO.setCaretColor(Color.BLACK);

		ok = new JButton("���� ���");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// ��ҹ�ư �߰�
		re = new JButton("���ư���");
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
		boolean isCancel = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "���� ���":
				// ����Ʈ ��� ����ó��
				if (!bookNO.getText().isEmpty()) {
					if (isStringDouble(bookNO.getText()) == false) {
						JOptionPane.showMessageDialog(null, "�����ȣ�� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						try {
							bookDB bookDB = new bookDB();
							member member = ui.getmember();
							
							memberDB memberDB = new memberDB();
							payDB payDB= new payDB();
							String mID = member.getmID();
							
							book tmpBook = bookDB.getBook2(member.getmID());
							pay tmpPay = payDB.getpay2(bookNO.getText());
							int payMoney = Integer.valueOf(tmpPay.getprice());
							
							isCancel = bookDB.deleteBook(bookNO.getText(), mID);
							
							if (isCancel == true) {
								//Ƽ�ϰ����� 10000���� ���� ����Ʈ�� �̿���Ѱ��̹Ƿ� ����Ʈ�� ���� ä���� �ʿ䰡����
								if(payMoney == 10000) {
									memberDB.updateCancelMember(member, 0);
								}
								//�������� 10000 - ����Ƽ�� ���� = ����Ʈ , �� ����Ʈ�� ���������ش�.
								else if(payMoney < 10000) {
									memberDB.updateCancelMember(member, 10000 - payMoney);
								}
								JOptionPane.showMessageDialog(null, "������Ұ� �Ϸ�Ǿ����ϴ�!", "�޼���",
										JOptionPane.INFORMATION_MESSAGE);
							} else if (isCancel == false) {
								JOptionPane.showMessageDialog(null, "������Ҹ� ���� �߽��ϴ�.(�������� ���� ��ȭ�Դϴ�!)", "�޼���",
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (Exception e10) {
							JOptionPane.showMessageDialog(null, "��������� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "������Ҹ� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
				}
			case "���ư���":
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