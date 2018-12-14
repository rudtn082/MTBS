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

import Movie.movie;
import Movie.movieDB;
import Theater.TheaterDB;
import Theater.theater;

public class movie_info_UI extends JPanel {
	JTextField mvID, mvTheaterID, mvMovieTitle, mvDirector, mvActor, mvGrade, mvInfo;
	UI_Main ui;
	JButton ok, cancel;

	public movie_info_UI(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_info.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// ��ȭ ���̵� �ʵ�
		mvID = new JTextField(20);
		mvID.setBounds(360, 176, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);

		// �󿵰� ���̵� �ʵ�
		mvTheaterID = new JTextField(20);
		mvTheaterID.setBounds(360, 234, 470, 55);
		mvTheaterID.setOpaque(false);
		mvTheaterID.setForeground(Color.WHITE);
		mvTheaterID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvTheaterID.setCaretColor(Color.white);

		// ��ȭ ���� �ʵ�
		mvMovieTitle = new JTextField(20);
		mvMovieTitle.setBounds(360, 293, 470, 55);
		mvMovieTitle.setOpaque(false);
		mvMovieTitle.setForeground(Color.WHITE);
		mvMovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvMovieTitle.setCaretColor(Color.white);

		// ���� �ʵ�
		mvDirector = new JTextField(20);
		mvDirector.setOpaque(false);
		mvDirector.setBounds(360, 352, 470, 55);
		mvDirector.setForeground(Color.WHITE);
		mvDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvDirector.setCaretColor(Color.white);

		// �⿬ �ʵ�
		mvActor = new JTextField(30);
		mvActor.setOpaque(false);
		mvActor.setBounds(360, 414, 470, 55);
		mvActor.setForeground(Color.WHITE);
		mvActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvActor.setCaretColor(Color.white);

		// ��� �ʵ�
		mvGrade = new JTextField(10);
		mvGrade.setOpaque(false);
		mvGrade.setBounds(360, 470, 470, 55);
		mvGrade.setForeground(Color.WHITE);
		mvGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvGrade.setCaretColor(Color.white);

		// �ֿ����� �ʵ�
		mvInfo = new JTextField(30);
		mvInfo.setOpaque(false);
		mvInfo.setBounds(360, 530, 470, 55);
		mvInfo.setForeground(Color.WHITE);
		mvInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvInfo.setCaretColor(Color.white);

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

		add(mvID);
		add(mvTheaterID);
		add(mvMovieTitle);
		add(mvDirector);
		add(mvActor);
		add(mvGrade);
		add(mvInfo);
		add(cancel);
		add(ok);
		add(lblNewLabel);

		mvID.addActionListener(new MyActionListener());
		mvTheaterID.addActionListener(new MyActionListener());
		mvMovieTitle.addActionListener(new MyActionListener());
		mvDirector.addActionListener(new MyActionListener());
		mvActor.addActionListener(new MyActionListener());
		mvGrade.addActionListener(new MyActionListener());
		mvInfo.addActionListener(new MyActionListener());

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

				// �󿵰� ���̵� ����ó��
				if (mvTheaterID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�󿵰� ���̵� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// ��ȭ ���� ����ó��
				if (mvMovieTitle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ ������ �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// ���� ����ó��
				if (mvDirector.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�����̸��� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvDirector.getText()) == true) {
						JOptionPane.showMessageDialog(null, "�����̸��� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// �⿬ ����ó��
				if (mvActor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�⿬�ڸ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvActor.getText()) == true) {
						JOptionPane.showMessageDialog(null, "�⿬�ڴ� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// ��� ����ó��
				if (mvGrade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "����� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
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
				if (mvInfo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�ֿ������� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvInfo.getText()) == true) {
						JOptionPane.showMessageDialog(null, "�ֿ������� ���ڷ� �Է����ּ���.", "�Է� ����", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				try {
					TheaterDB TheaterDB = new TheaterDB();
					theater theater = new theater();
					Vector theaterlist = TheaterDB.getTheaterList();

					boolean temp = false;
					for (int i = 0; i < theaterlist.size(); i++) {
						if (mvTheaterID.getText().equals(((Vector) (theaterlist.get(i))).get(0))) {
							theater.settTheaterID((String) ((Vector) theaterlist.get(i)).get(0));
							theater.settCinemaName((String) ((Vector) theaterlist.get(i)).get(1));
							theater.settSeatNum((String) ((Vector) theaterlist.get(i)).get(2));
							theater.settMovieTitle(mvMovieTitle.getText());
							theater.settStartTime((String) ((Vector) theaterlist.get(i)).get(4));
							TheaterDB.updateTheater(theater);
							temp = true;
							break;
						}
					}

					// ������ �ִ� �󿵰�
					if (temp) {
						movie new_movie = new movie();
						new_movie.setMvID(mvID.getText());
						new_movie.setMvTheaterID(mvTheaterID.getText());
						new_movie.setMvMovieTitle(mvMovieTitle.getText());
						new_movie.setMvDirector(mvDirector.getText());
						new_movie.setMvActor(mvActor.getText());
						new_movie.setMvGrade(mvGrade.getText());
						new_movie.setMvInfo(mvInfo.getText());
						new_movie.setMvAccumulateNum("0");
						movieDB movieDB = new movieDB();

						boolean torf = movieDB.insertMovie(new_movie);
						if (torf)
							JOptionPane.showMessageDialog(null, "��ȭ�� ��� �Ǿ����ϴ�!", "�޼���",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "��ȭ����� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "��ġ�ϴ� �󿵰��� �����ϴ�. ��ȭ���� ���� �߰� ���ּ���.", "�޼���",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "��ȭ����� ���� �߽��ϴ�.", "�޼���", JOptionPane.WARNING_MESSAGE);
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