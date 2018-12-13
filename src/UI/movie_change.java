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

import Movie.movie;
import Movie.movieDB;

public class movie_change extends JPanel {
	JTextField mvID, mvMovieTitle, mvDirector, mvActor, mvGrade, mvInfo;
	UI_Main ui;
	JButton ok, cancel;

	public movie_change(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ ���̵� �ʵ�
		mvID = new JTextField(20);
		mvID.setBounds(455, 182, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);

		// ��ȭ ���� �ʵ�
		mvMovieTitle = new JTextField(20);
		mvMovieTitle.setBounds(360, 255, 470, 55);
		mvMovieTitle.setOpaque(false);
		mvMovieTitle.setForeground(Color.WHITE);
		mvMovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvMovieTitle.setCaretColor(Color.white);

		// ���� �ʵ�
		mvDirector = new JTextField(20);
		mvDirector.setOpaque(false);
		mvDirector.setBounds(360, 329, 470, 55);
		mvDirector.setForeground(Color.WHITE);
		mvDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvDirector.setCaretColor(Color.white);

		// �⿬ �ʵ�
		mvActor = new JTextField(30);
		mvActor.setOpaque(false);
		mvActor.setBounds(360, 405, 470, 55);
		mvActor.setForeground(Color.WHITE);
		mvActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvActor.setCaretColor(Color.white);

		// ��� �ʵ�
		mvGrade = new JTextField(10);
		mvGrade.setOpaque(false);
		mvGrade.setBounds(360, 479, 470, 55);
		mvGrade.setForeground(Color.WHITE);
		mvGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvGrade.setCaretColor(Color.white);

		// �ֿ����� �ʵ�
		mvInfo = new JTextField(30);
		mvInfo.setOpaque(false);
		mvInfo.setBounds(360, 553, 470, 55);
		mvInfo.setForeground(Color.WHITE);
		mvInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvInfo.setCaretColor(Color.white);

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

		add(mvID);
		add(mvMovieTitle);
		add(mvDirector);
		add(mvActor);
		add(mvGrade);
		add(mvInfo);
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
				if (mvID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ ���̵� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvID.getText()) == false) {
						JOptionPane.showMessageDialog(null, "��ȭ ���̵�� ���ڷ� �Է����ּ���.", "�Է� ����",
								JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// ���� ����ó��
				if (isStringDouble(mvDirector.getText()) == true) {
					JOptionPane.showMessageDialog(null, "�����̸��� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// �⿬ ����ó��
				if (isStringDouble(mvActor.getText()) == true) {
					JOptionPane.showMessageDialog(null, "�⿬�ڴ� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// ��� ����ó��
				if (!mvGrade.getText().equals("")) {
					if (isStringDouble(mvGrade.getText()) == false) {
						JOptionPane.showMessageDialog(null, "����� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (mvGrade.getText().length() != 2) {
							JOptionPane.showMessageDialog(null, "����� 2�ڸ��� �Է����ּ���.", "�Է� ����",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				// �ֿ� ���� ����ó��
				if (isStringDouble(mvInfo.getText()) == true) {
					JOptionPane.showMessageDialog(null, "�ֿ������� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				try {
					movieDB movieDB = new movieDB();
					movie changeMovie = movieDB.getMovieDTO(mvID.getText());
					if (changeMovie.getMvMovieTitle() == null) {
						JOptionPane.showMessageDialog(null, "�ش��ϴ� ��ȭ ���̵� �����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					if (mvMovieTitle.getText().equals("") && mvDirector.getText().equals("")
							&& mvActor.getText().equals("") && mvGrade.getText().equals("")
							&& mvInfo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "�ƹ� ���� ������ �����ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}

					if (!mvMovieTitle.getText().equals(""))
						changeMovie.setMvMovieTitle(mvMovieTitle.getText());
					if (!mvDirector.getText().equals(""))
						changeMovie.setMvDirector(mvDirector.getText());
					if (!mvActor.getText().equals(""))
						changeMovie.setMvActor(mvActor.getText());
					if (!mvGrade.getText().equals(""))
						changeMovie.setMvGrade(mvGrade.getText());
					if (!mvInfo.getText().equals(""))
						changeMovie.setMvInfo(mvInfo.getText());

					boolean torf = movieDB.updateMovie(changeMovie);

					if (torf)
						JOptionPane.showMessageDialog(null, "��ȭ�� �����Ͽ����ϴ�!", "�޼���", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "��ȭ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "��ȭ������ ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.update_UI("movie_manage");
				break;
			case "���":
				ui.update_UI("movie_manage");
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
