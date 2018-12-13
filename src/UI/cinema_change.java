package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cinema.cinema;
import Cinema.cinemaDB;

public class cinema_change extends JPanel {
	JTextField cinemaName, cinemaAddress, cinemaNum;
	UI_Main ui;
	JButton ok, cancel;

	public cinema_change(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinena_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ�� �̸� �ʵ�
		cinemaName = new JTextField(20);
		cinemaName.setBounds(455, 182, 470, 55);
		cinemaName.setOpaque(false);
		cinemaName.setForeground(Color.WHITE);
		cinemaName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaName.setCaretColor(Color.white);

		// ��ȭ�� �ּ� �ʵ�
		cinemaAddress = new JTextField(20);
		cinemaAddress.setBounds(360, 255, 470, 55);
		cinemaAddress.setOpaque(false);
		cinemaAddress.setForeground(Color.WHITE);
		cinemaAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaAddress.setCaretColor(Color.white);

		// ��ȭ�� ��ȣ �ʵ�
		cinemaNum = new JTextField(20);
		cinemaNum.setOpaque(false);
		cinemaNum.setBounds(360, 329, 470, 55);
		cinemaNum.setForeground(Color.WHITE);
		cinemaNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaNum.setCaretColor(Color.white);

		// ������ư �߰�
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

		add(cinemaName);
		add(cinemaAddress);
		add(cinemaNum);
		add(cancel);
		add(ok);
		add(lblNewLabel);
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "����":
				// ��ȭ�� �̸� ����ó��
				if (cinemaName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ�� �̸��� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// �ּ� ����ó��
				if (isStringDouble(cinemaAddress.getText()) == true) {
					JOptionPane.showMessageDialog(null, "�ּҴ� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// ��ȭ��ȣ ����ó��
				if (!cinemaNum.getText().equals("")) {
					if (isStringDouble(cinemaNum.getText()) == true) {
						JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ***-****�������� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (cinemaNum.getText().length() > 9) {
							JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� 9�ڸ� �̳��� �Է����ּ���.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					cinemaDB cinemaDB = new cinemaDB();
					cinema changecinema = cinemaDB.getCinemaDTO(cinemaName.getText());
					if (cinemaName.getText() == null) {
						JOptionPane.showMessageDialog(null, "�ش��ϴ� ��ȭ�� �̸��� �����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					if (cinemaName.getText().equals("") && cinemaAddress.getText().equals("")
							&& cinemaNum.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "�ƹ� ���� ������ �����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					
					if (!cinemaName.getText().equals(""))
						changecinema.setcNAME(cinemaName.getText());
					if (!cinemaAddress.getText().equals(""))
						changecinema.setcAddress(cinemaAddress.getText());
					if (!cinemaNum.getText().equals(""))
						changecinema.setcPhoneNum(cinemaNum.getText());

					boolean torf = cinemaDB.updateCinema(changecinema);

					if (torf)
						JOptionPane.showMessageDialog(null, "��ȭ�� �����Ͽ����ϴ�!", "�޼���", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "��ȭ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "��ȭ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
					
					
					
					
				ui.update_UI("cinema_manage");
				break;
			case "���":
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
