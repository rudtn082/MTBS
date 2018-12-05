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

import Cinema.cinemaDB;

public class cinema_delete extends JPanel {
	JTextField cinemaName;
	UI_Main ui;
	JButton ok, cancel;

	public cinema_delete(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinema_delete.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ�� �̸� �ʵ�
		cinemaName = new JTextField(20);
		cinemaName.setBounds(455, 325, 470, 55);
		cinemaName.setOpaque(false);
		cinemaName.setForeground(Color.WHITE);
		cinemaName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaName.setCaretColor(Color.white);

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
				// ��ȭ ���̵� ����ó��
				if (cinemaName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ�� �̸��� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(cinemaName.getText()) != false) {
						JOptionPane.showMessageDialog(null, "��ȭ�� �̸��� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
				
				try {
				cinemaDB cinemaDB = new cinemaDB();
				boolean torf = cinemaDB.deleteCinema(cinemaName.getText());

				if(torf)
					JOptionPane.showMessageDialog(null, "��ȭ���� ���� �Ǿ����ϴ�!", "�޼���", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "��ȭ�� ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "��ȭ�� ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
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
