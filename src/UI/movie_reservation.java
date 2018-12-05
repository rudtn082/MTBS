package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cinema.cinemaDB;
import Movie.movieDB;
import People.member;
import People.memberDB;

public class movie_reservation extends JPanel {
	JButton movie_search, movie_reserv, logout, dropout;
	UI_Main ui;
	JButton ok, cancel;

	public movie_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 콤보 박스 ===============================================
		JPanel panel = new JPanel();
		panel.setBounds(500, 220, 200, 70);
		panel.setOpaque(false);
		
		movieDB movieDB = new movieDB();
		Vector v = movieDB.getMovieList();
		
		System.out.println();

		// 콤보 박스 내 선택 가능 메뉴 선언 (영화)
		String[] movie = new String[v.size()];
		
		for(int i = 0; i<v.size(); i++) {
			movie[i] = (String)((Vector)v.get(i)).get(2);
		}
		
		// 영화 선택 콤보 박스 생성 및 추가
		JComboBox movieCombo = new JComboBox();
		movieCombo.setModel(new DefaultComboBoxModel(movie));
		//===========================================================
		JPanel panel2 = new JPanel();
		panel2.setBounds(500, 270, 200, 70);
		panel2.setOpaque(false);
		
		cinemaDB cinemaDB = new cinemaDB();
		Vector v2 = cinemaDB.getCinemaList();
		
		String[] cinema = new String[v2.size()];
		
		for(int i = 0; i<v2.size(); i++) {
			cinema[i] = (String)((Vector)v2.get(i)).get(0);
		}
		
		// 영화 선택 콤보 박스 생성 및 추가
		JComboBox cinemaCombo = new JComboBox();
		cinemaCombo.setModel(new DefaultComboBoxModel(cinema));
		
		// 콤보 박스 ====================================================
		
		// 영화 검색 버튼 추가
		movie_search = new JButton("영화 검색");
		movie_search.setContentAreaFilled(false);
		movie_search.setFocusPainted(false);
		movie_search.setForeground(Color.WHITE);
		movie_search.setBounds(20, 23, 200, 55);
		
		// 영화 예약 버튼 추가
		movie_reserv = new JButton("영화 예약");
		movie_reserv.setContentAreaFilled(false);
		movie_reserv.setFocusPainted(false);
		movie_reserv.setForeground(Color.WHITE);
		movie_reserv.setBounds(270, 23, 200, 55);

		// 로그아웃 버튼 추가
		logout = new JButton("로그아웃");
		logout.setContentAreaFilled(false);
		logout.setFocusPainted(false);
		logout.setForeground(Color.WHITE);
		logout.setBounds(520, 23, 200, 55);
		
		// 회원탈퇴 버튼 추가
		dropout = new JButton("회원탈퇴");
		dropout.setContentAreaFilled(false);
		dropout.setFocusPainted(false);
		dropout.setForeground(Color.WHITE);
		dropout.setBounds(770, 23, 200, 55);
		
		// 예약버튼 추가
		ok = new JButton("예약");
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
		

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(dropout);
		add(ok);
		add(cancel);
		add(panel);
		panel.add(movieCombo);
		panel.add(cinemaCombo);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		dropout.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "영화 검색":
				ui.update_UI("movie_search");
				break;
			case "영화 예약":
				ui.update_UI("movie_reservation");
				break;
			case "로그아웃":
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
				ui.update_UI("Login");
				break;
			case "회원탈퇴":
				try {
					int result = JOptionPane.showConfirmDialog(null, "탈퇴 하시겠습니까?", "메세지", JOptionPane.WARNING_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						member member = ui.getmember();
						memberDB memberDB = new memberDB();
						memberDB.deleteMember(member.getmID(), member.getmPW());
						JOptionPane.showMessageDialog(null, "탈퇴 성공!", "메세지", JOptionPane.INFORMATION_MESSAGE);
						ui.update_UI("Login");
					}
					break;
				} catch(Exception e1) {
					System.out.println(e1.toString());
					JOptionPane.showMessageDialog(null, "탈퇴를 실패했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					break;
				}
			case "예약":
				System.out.println("예약버튼");
				break;
			case "취소":
				System.out.println("취소버튼");
			}
		}
	}

}
