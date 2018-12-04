package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import People.member;
import People.memberDB;
import UI.cinema_manage.MyActionListener;

public class movie_search extends JPanel {
	JButton movie_search, movie_reserv, logout, dropout, back;
	JLabel mov1, mov2, mov3, mov4, mov5, mov6, mov7, mov8, mov9, mov10;
	UI_Main ui;

	public movie_search(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_search.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		// 영화 한개당 1라벨 / String으로 바로 넣기!
		mov1 = new JLabel("제목                             감독.... 이렇게");
		mov1.setBounds(135, 257, 750, 20);
		mov1.setForeground(Color.WHITE);
		
		mov2 = new JLabel("제목                             감독.... 이렇게");
		mov2.setBounds(135, 298, 750, 20);
		mov2.setForeground(Color.WHITE);
		
		mov3 = new JLabel("제목                             감독.... 이렇게");
		mov3.setBounds(135, 339, 750, 20);
		mov3.setForeground(Color.WHITE);

		mov4 = new JLabel("제목                             감독.... 이렇게");
		mov4.setBounds(135, 380, 750, 20);
		mov4.setForeground(Color.WHITE);

		mov5 = new JLabel("제목                             감독.... 이렇게");
		mov5.setBounds(135, 421, 750, 20);
		mov5.setForeground(Color.WHITE);

		mov6 = new JLabel("제목                             감독.... 이렇게");
		mov6.setBounds(135, 462, 750, 20);
		mov6.setForeground(Color.WHITE);

		mov7 = new JLabel("제목                             감독.... 이렇게");
		mov7.setBounds(135, 503, 750, 20);
		mov7.setForeground(Color.WHITE);

		mov8 = new JLabel("제목                             감독.... 이렇게");
		mov8.setBounds(135, 544, 750, 20);
		mov8.setForeground(Color.WHITE);

		mov9 = new JLabel("제목                             감독.... 이렇게");
		mov9.setBounds(135, 585, 750, 20);
		mov9.setForeground(Color.WHITE);
		// 영화 한개당 1라벨 / String으로 바로 넣기!

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

		// 돌아가기 버튼 추가
		back = new JButton("돌아가기");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setForeground(Color.WHITE);
		back.setBounds(770, 23, 200, 55);

		add(mov1);
		add(mov2);
		add(mov3);
		add(mov4);
		add(mov5);
		add(mov6);
		add(mov7);
		add(mov8);
		add(mov9);
		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(dropout);
		add(back);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		dropout.addActionListener(new MyActionListener());
		back.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "영화 검색":
				System.out.println("영화 검색 버튼");
				break;
			case "영화 예약":
				System.out.println("영화 예약 버튼");
				break;
			case "로그아웃":
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
				ui.update_UI("Login");
				break;
			case "회원탈퇴":
				try {
					JOptionPane.showMessageDialog(null, "탈퇴 하시겠습니까?", "메세지", JOptionPane.WARNING_MESSAGE);
					member member = ui.getmember();
					memberDB memberDB = new memberDB();
					memberDB.deleteMember(member.getmID(), member.getmPW());
					JOptionPane.showMessageDialog(null, "탈퇴 성공!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					ui.update_UI("Login");
					break;
				} catch(Exception e1) {
					System.out.println(e1.toString());
					JOptionPane.showMessageDialog(null, "탈퇴를 실패했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					break;
				}
			case "돌아가기":
				ui.update_UI("Main_Menu");
				break;
			}
		}
	}

}
