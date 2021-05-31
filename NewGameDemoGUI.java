import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NewGameDemoGUI extends JFrame implements ActionListener, Starter {

	public JTextField name;
	public JPanel Difficulty;
	NewGameDemoGUI gamemain = this;
	int result = 0;
	// public ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {
//		new NewGameDemoGUI();
		NewGameDemoGUI fight = new NewGameDemoGUI();
		fight.setVisible(true);
	}

	public NewGameDemoGUI() {

		setting();
		JPanel main = new JPanel();
		ImageIcon back = new ImageIcon(
				"C:\\Users\\������\\eclipse-workspace\\FinalTeamProject\\src\\start_background.png");

		JPanel screen = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(back.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		screen.setLayout(null);

		name = new JTextField(90);
		name.setBackground(Color.white);
		name.setBounds(495, 393, 350, 50);
		screen.add(name);

		JButton start = new JButton();
		start.addActionListener(this);
		start.setActionCommand("start");
		// start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		// start.setFocusPainted(false);
		start.setBounds(470, 460, 150, 107);
		ImageIcon startBtn = new ImageIcon("C:\\Users\\������\\eclipse-workspace\\FinalTeamProject\\src\\startBtn.png");
		start.setIcon(startBtn);
		screen.add(start);

		JButton help = new JButton();
		help.addActionListener(this);
		help.setActionCommand("help");
		help.setBorderPainted(false);
		help.setContentAreaFilled(false);
		// help.setFocusPainted(false);
		help.setBounds(640, 460, 150, 107);
		ImageIcon helpBtn = new ImageIcon("C:\\Users\\������\\eclipse-workspace\\FinalTeamProject\\src\\help.png");
		help.setIcon(helpBtn);
		screen.add(help);

		add(screen);

		setVisible(true);
		// ��������� screen panel�Դϴ�~ ���

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		continued(e);
	}

	public void continued(ActionEvent e) {
		String actionCmd = e.getActionCommand();
		String input = name.getText();
		double userScore = 3.5; //���Ͽ� ������ Score
		ArrayList<User> readUser = new ArrayList<User>();
		
		String FileName = "data.dat";
		File file = new File(FileName);
		
		if (actionCmd.equals("start")) {
				User newUser = new User(userScore, input);
				System.out.println(newUser);
				//readUser.add(newUser);
				
				// ��������� �κ� (�Է� + ���)
				ObjectInputStream inputStream = null;
				ObjectOutputStream outputStream = null;
				try {
					inputStream = new ObjectInputStream(new FileInputStream("data.dat"));
					outputStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
					//readUser = (ArrayList<User>) inputStream.readObject(); //�̷��� �ϸ� ������
					try {
						
						//User readOne = (User) inputStream.readObject();// = new User(); //���� �ȿ� �ִ� ��ü �ϳ��� �ϳ��� ������ readUser�� �����ϱ� ���� ����
						
						//���� ���� �ƴ� ���� readOne���� ��ü�� �ϳ��� �ҷ��ͼ� �װ��� id�� input�� ������ �˻� -> ������ ������ exist = true;
						
						
						outputStream.writeObject(newUser);
						
						while (readOne != null) {
							readUser.add(readOne);
							readOne = (User) inputStream.readObject();
							System.out.println(readOne);
						}
					} catch (ClassNotFoundException e2) {
						System.out.println("Problems with file input. (ClassNotFoundException)");
					}
					inputStream.close();
					outputStream.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
					System.err.println("���� �������� ����");
				} catch (IOException e2) {
					e2.printStackTrace();
					System.err.println("Problem with file input. (IOException)");
				
				boolean exist = true;
				// ��������� �κ� (���)
				if (!exist) {
					ObjectOutputStream outputStream = null;
					try {
						outputStream = new ObjectOutputStream(new FileOutputStream("data.dat"));
						outputStream.writeObject(newUser);
						outputStream.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					int result = JOptionPane.showConfirmDialog(null, "�Է��� ���̵�� �̾ ������ �����մϴ�.", "�ȳ��޼���",
							JOptionPane.OK_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						// ���� �Ѱ��ִ� �κ� �����ؾ���
						new SelectSkillPortion();
						setVisible(false);
					}
				}else { //exist == true
					
					int result = JOptionPane.showConfirmDialog(null, "�Է��� ���̵�� ������ �����մϴ�.", "�ȳ��޼���",
							JOptionPane.OK_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						// ���� �Ѱ��ִ� �κ� �����ؾ���
						new SelectSkillPortion();
						setVisible(false);
					}
				}
				
				
		}else if (actionCmd.equals("help")) {
			Help helpwindow = new Help();
			helpwindow.setVisible(true);
		}
	}

	@Override
	public void setting() {
		// TODO Auto-generated method stub
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("�ڹ� ���!!");
	}
}