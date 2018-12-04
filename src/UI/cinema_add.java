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
import UI.movie_info_UI.MyActionListener;

public class cinema_add extends JPanel {
	JTextField mvID, mvMovieTitle, mvDirector, mvActor, mvGrade, mvInfo;
	UI_Main ui;
	JButton ok, cancel;

	public cinema_add(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinema_add.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화 아이디 필드
		mvID = new JTextField(20);
		mvID.setBounds(360, 198, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);

		// 영화 제목 필드
		mvMovieTitle = new JTextField(20);
		mvMovieTitle.setBounds(360, 275, 470, 55);
		mvMovieTitle.setOpaque(false);
		mvMovieTitle.setForeground(Color.WHITE);
		mvMovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvMovieTitle.setCaretColor(Color.white);

		// 감독 필드
		mvDirector = new JTextField(20);
		mvDirector.setOpaque(false);
		mvDirector.setBounds(360, 349, 470, 55);
		mvDirector.setForeground(Color.WHITE);
		mvDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvDirector.setCaretColor(Color.white);

		// 출연 필드
		mvActor = new JTextField(30);
		mvActor.setOpaque(false);
		mvActor.setBounds(360, 425, 470, 55);
		mvActor.setForeground(Color.WHITE);
		mvActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvActor.setCaretColor(Color.white);

		// 등급 필드
		mvGrade = new JTextField(10);
		mvGrade.setOpaque(false);
		mvGrade.setBounds(360, 499, 470, 55);
		mvGrade.setForeground(Color.WHITE);
		mvGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvGrade.setCaretColor(Color.white);

		// 주요정보 필드
		mvInfo = new JTextField(30);
		mvInfo.setOpaque(false);
		mvInfo.setBounds(360, 573, 470, 55);
		mvInfo.setForeground(Color.WHITE);
		mvInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvInfo.setCaretColor(Color.white);
		
		// 저장버튼 추가
		ok = new JButton("저장");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// 취소버튼 추가
		cancel = new JButton("취소");
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
		mvID.addActionListener(new MyActionListener());
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
			case "저장":
				// 영화 아이디 예외처리
				if (mvID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화 아이디를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvID.getText()) != false) {
						JOptionPane.showMessageDialog(null, "영화 아이디는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 영화 제목 예외처리
				if (mvMovieTitle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화 제목을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 감독 예외처리
				if (mvDirector.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "감독이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvDirector.getText()) == true) {
						JOptionPane.showMessageDialog(null, "감독이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 출연 예외처리
				if (mvActor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "출연자를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvActor.getText()) == true) {
						JOptionPane.showMessageDialog(null, "출연자는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 등급 예외처리
				if (mvGrade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "등급을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvGrade.getText()) == false) {
						JOptionPane.showMessageDialog(null, "등급은 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (mvGrade.getText().length() != 2) {
							JOptionPane.showMessageDialog(null, "등급은 2자리로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				// 주요 정보 예외처리
				if (mvInfo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "주요정보를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvInfo.getText()) == true) {
						JOptionPane.showMessageDialog(null, "주요정보는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
				
				try {
					movie new_movie = new movie();
					new_movie.setMvID(mvID.getText());
					new_movie.setMvMovieTitle(mvMovieTitle.getText());
					new_movie.setMvDirector(mvDirector.getText());
					new_movie.setMvActor(mvActor.getText());
					new_movie.setMvGrade(mvGrade.getText());
					new_movie.setMvInfo(mvInfo.getText());

					movieDB movieDB = new movieDB();
					movieDB.getConn();
					boolean torf = movieDB.insertMovie(new_movie);
					if(torf)
						JOptionPane.showMessageDialog(null, "영화가 등록 되었습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "영화등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "영화등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.update_UI("cinema_manage");
				break;
			case "취소":
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
