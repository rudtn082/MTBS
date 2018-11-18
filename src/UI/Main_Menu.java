package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Main_Menu extends JPanel {
	JButton bt;
	UI_Main ui;

	public Main_Menu(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		// 로그인버튼 추가
		bt = new JButton("하,...");
		bt.setBounds(430, 528, 150, 40);
		add(bt);

		bt.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("aa");
			ui.update_UI("Login");
		}
	}
}
