package UI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cancel_reservation extends JPanel{
	UI_Main ui;
	public cancel_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cancel_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);
		
		
		
		add(lblNewLabel);
	}
}
