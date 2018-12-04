package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import People.memberDB;

public class VIP_manage extends JPanel {
	JButton movie_manage, cinema_manage, VIP_manage, ticket_manage, re;
	UI_Main ui;
	JLabel sID;

	public VIP_manage(UI_Main ui) {
		this.ui = ui;
		// ���̾ƿ� ����
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/VIP_manage.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		ArrayList<ArrayList> vipMember = new ArrayList<>();
		VIP_take VIP_take = new VIP_take();
		vipMember = VIP_take.getVIPList();

		String[][] a = new String[vipMember.size()][3];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = new String();
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = (String) vipMember.get(i).get(j);
			}
		}

		// row
		JLabel[] row = new JLabel[vipMember.size()];
		JLabel[] row2 = new JLabel[vipMember.size()];
		JLabel[] row3 = new JLabel[vipMember.size()];
		for (int i = 0; i < a.length; i++) {
			int j = 0;
			row[i] = new JLabel(a[i][j]);
			row[i].setBounds(165, 198 + (i * 19), 470, 55);
			row[i].setForeground(Color.WHITE);

			row2[i] = new JLabel(a[i][j + 1]);
			row2[i].setBounds(450, 198 + (i * 19), 470, 55);
			row2[i].setForeground(Color.WHITE);

			row3[i] = new JLabel(a[i][j + 2]);
			row3[i].setBounds(800, 198 + (i * 19), 470, 55);
			row3[i].setForeground(Color.WHITE);
			add(row[i]);
			add(row2[i]);
			add(row3[i]);
		}

		// ��ȭ ���� ��ư �߰�
		movie_manage = new JButton("��ȭ ����");
		movie_manage.setContentAreaFilled(false);
		movie_manage.setFocusPainted(false);
		movie_manage.setForeground(Color.WHITE);
		movie_manage.setBounds(20, 23, 200, 55);

		// ��ȭ�� ���� ��ư �߰�
		cinema_manage = new JButton("��ȭ�� ����");
		cinema_manage.setContentAreaFilled(false);
		cinema_manage.setFocusPainted(false);
		cinema_manage.setForeground(Color.WHITE);
		cinema_manage.setBounds(270, 23, 200, 55);

		// VIP�� ���� ��ư �߰�
		VIP_manage = new JButton("VIP�� ����");
		VIP_manage.setContentAreaFilled(false);
		VIP_manage.setFocusPainted(false);
		VIP_manage.setForeground(Color.WHITE);
		VIP_manage.setBounds(520, 23, 200, 55);

		// ��ȭ Ƽ�� ���� ��ư �߰�
		ticket_manage = new JButton("��ȭ Ƽ�� ����");
		ticket_manage.setContentAreaFilled(false);
		ticket_manage.setFocusPainted(false);
		ticket_manage.setForeground(Color.WHITE);
		ticket_manage.setBounds(770, 23, 200, 55);
		
		// ���ư��� ��ư �߰�
		re = new JButton("���ư���");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(307, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		add(movie_manage);
		add(cinema_manage);
		add(VIP_manage);
		add(ticket_manage);
		add(re);
		add(lblNewLabel);
		movie_manage.addActionListener(new MyActionListener());
		cinema_manage.addActionListener(new MyActionListener());
		VIP_manage.addActionListener(new MyActionListener());
		ticket_manage.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());

		try {
			memberDB memberDB = new memberDB();
			ArrayList data = memberDB.getMemberList();
			System.out.println(data);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "��ȭ ����":
				ui.update_UI("movie_manage");
				break;
			case "��ȭ�� ����":
				ui.update_UI("cinema_manage");
				break;
			case "VIP�� ����":
				ui.update_UI("VIP_manage");
				break;
			case "��ȭ Ƽ�� ����":
				System.out.println("��ȭ Ƽ�� ���� ��ư");
				break;
			case "���ư���":
				ui.update_UI("Main_Menu_admin");
				break;
			}
		}
	}

	public class VIP_take {

		public Connection getConn() {
			Connection con = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); // 1. ����̹� �ε�
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MTBS?serverTimezone=UTC&useSSL=false",
						"MTBS", "mtbs"); // 2. ����̹� ����

			} catch (Exception e) {
				e.printStackTrace();
			}

			return con;
		}

		public ArrayList getVIPList() {

			ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable�� ���� ���� �ִ� ��� 1. 2�����迭 2. Vector �� vector�߰�

			Connection con = null; // ����
			PreparedStatement ps = null; // ���
			ResultSet rs = null; // ���

			try {
				con = getConn();
				String sql = "select ID, Name, ticket from member order by ticket desc LIMIT 10 ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					String mID = rs.getString("ID");
					String mName = rs.getString("Name");
					String mticket = rs.getString("ticket");

					ArrayList<String> array = new ArrayList<String>();
					array.add(mID);
					array.add(mName);
					array.add(mticket);

					data.add(array);
				} // while
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
	}
}
