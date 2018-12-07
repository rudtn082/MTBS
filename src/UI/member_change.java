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

import People.member;
import People.memberDB;

public class member_change extends JPanel {
	JButton ok, cancel;
	JTextField name, id, PW, address, PN, dob;
	UI_Main ui;

	public member_change(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/member_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��й�ȣ �ʵ�
		PW = new JTextField(20);
		PW.setBounds(360, 198, 470, 55);
		PW.setOpaque(false);
		PW.setForeground(Color.WHITE);
		PW.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PW.setCaretColor(Color.white);

		// �̸� �ʵ�
		name = new JTextField(20);
		name.setOpaque(false);
		name.setBounds(360, 275, 470, 55);
		name.setForeground(Color.WHITE);
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setCaretColor(Color.white);

		// ������� �ʵ�
		dob = new JTextField(10);
		dob.setOpaque(false);
		dob.setBounds(360, 349, 470, 55);
		dob.setForeground(Color.WHITE);
		dob.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		dob.setCaretColor(Color.white);

		// �ּ� �ʵ�
		address = new JTextField(40);
		address.setOpaque(false);
		address.setBounds(360, 425, 470, 55);
		address.setForeground(Color.WHITE);
		address.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		address.setCaretColor(Color.white);

		// ��ȭ��ȣ �ʵ�
		PN = new JTextField(12);
		PN.setOpaque(false);
		PN.setBounds(360, 499, 470, 55);
		PN.setForeground(Color.WHITE);
		PN.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PN.setCaretColor(Color.white);

		// ���������ư �߰�
		ok = new JButton("��������");
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

		add(name);
		add(address);
		add(PW);
		add(PN);
		add(dob);
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
			case "��������":
				// �̸� ����ó��
				if (!name.getText().isEmpty()) {
					if (isStringDouble(name.getText()) == true) {
						JOptionPane.showMessageDialog(null, "�̸��� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// ������� ����ó��
				if (!dob.getText().isEmpty()) {
					if (isStringDouble(dob.getText()) == false) {
						JOptionPane.showMessageDialog(null, "��������� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (dob.getText().length() != 6) {
							JOptionPane.showMessageDialog(null, "��������� 6�ڸ��� �Է����ּ���.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				// �ּ� ����ó��
				if (!address.getText().isEmpty()) {
					if (isStringDouble(address.getText()) == true) {
						JOptionPane.showMessageDialog(null, "�ּҴ� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// ��ȭ��ȣ ����ó��
				if (!PN.getText().isEmpty()) {
					if (isStringDouble(PN.getText()) == false) {
						JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (PN.getText().length() != 11) {
							JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� 11�ڸ��� �Է����ּ���.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					member member = ui.getmember();

					if (!PW.getText().equals(""))
						member.setmPW(PW.getText());
					if (!name.getText().equals(""))
						member.setmName(name.getText());
					if (!dob.getText().equals(""))
						member.setmDOB(dob.getText());
					if (!address.getText().equals(""))
						member.setmAddress(address.getText());
					if (!PN.getText().equals(""))
						member.setmPN(PN.getText());

					memberDB memberDB = new memberDB();
					boolean torf = memberDB.updateMember(member);

					if (torf)
						JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�!", "�޼���", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "���������� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "���������� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				break;
			case "���":
				ui.update_UI("Main_Menu");
				break;
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
