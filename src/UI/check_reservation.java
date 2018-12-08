package UI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class check_reservation extends JPanel{
	UI_Main ui;
	public check_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/check_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		
		add(lblNewLabel);
	}
}
