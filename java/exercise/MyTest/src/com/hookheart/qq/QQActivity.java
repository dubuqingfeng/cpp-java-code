package com.hookheart.qq;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

/*
 * 客户端给服务端发送消息
 */
public class QQActivity extends JFrame implements ActionListener {

	private JTextField textUser;
	private JTextArea textArea;
	private JComboBox<String> comboBox;
	private PrintWriter pwsocket;
	private Socket s;
	private BufferedReader br;
	private InputStream is;
	private String username;
	public static boolean login = false;

	public QQActivity(String username, String other) {
		this.username = username;
		textUser = new JTextField();
		textUser.setColumns(22);
		textUser.addActionListener(this);
		this.setTitle(username);
		textArea = new JTextArea(18, 40);
		textArea.setEditable(true);
		textArea.setRows(26);
		textArea.setLineWrap(true);
		textArea.setText("------------------------消息列表-------------------------");

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"/home/user/aaa.txt")));
			while (br.ready()) {
				textArea.append(br.readLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int count = Integer.parseInt(other.substring(0, 1));
		System.out.println(count);
		String str[] = new String[count];
		for (int i = 0; i < count; i++) {
			str[i] = other.substring(2).split("%")[i];
		}

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		comboBox = new JComboBox<String>();

		for (int i = 0; i < count; i++) {
			comboBox.addItem(str[i]);
		}
		comboBox.addItem(username);

		JPanel panInput = new JPanel();
		panInput.add(textUser);
		JPanel panSelect = new JPanel();

		panSelect.setLayout(new GridLayout(1, 2));
		panSelect.add(comboBox);
		panSelect.add(btnSend);

		JScrollPane panTextArea = new JScrollPane(textArea);
		panTextArea.setLayout(new ScrollPaneLayout());
		this.setLayout(new BorderLayout() {
		});
		this.add(panInput, BorderLayout.SOUTH);
		this.add(panSelect, BorderLayout.CENTER);
		this.add(panTextArea, BorderLayout.NORTH);
		this.setSize(258, 580);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = textUser.getText();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		try {

			PrintWriter pw = new PrintWriter(new FileWriter(new File(
					"/home/user/aaa.txt"), true), true);
			pwsocket = new PrintWriter(new OutputStreamWriter(
					s.getOutputStream()), true);

			if (login) {
				if(str.equals("scene")){
					pwsocket.println("scene");
				} else if (str.substring(0, 6).equals("niming")) {
					pw.println(df.format(new Date()) + " " + "路人甲" + ":@"
							+ comboBox.getItemAt(comboBox.getSelectedIndex())
							+ " " + str.substring(6).trim());
					pwsocket.println(df.format(new Date()) + " " + "路人甲" + ":@"
							+ comboBox.getItemAt(comboBox.getSelectedIndex())
							+ " " + str.substring(6).trim());
				} else {
					pw.println(df.format(new Date()) + " " + username + ":@"
							+ comboBox.getItemAt(comboBox.getSelectedIndex())
							+ " " + str.trim());
					pwsocket.println(df.format(new Date()) + " " + username
							+ ":@"
							+ comboBox.getItemAt(comboBox.getSelectedIndex())
							+ " " + str.trim());
				}
			}
			pw.flush();
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		textUser.setText("");
	}

	public void connection() {
		MyThread thread = new MyThread(s);
		thread.start();
	}

	class MyThread extends Thread {
		public MyThread(Socket socket) {
		}

		@Override
		public void run() {
			super.run();
			try {
				s = new Socket("127.0.0.1", 8886);
				String receive = null;
				while (true) {
					is = s.getInputStream();
					br = new BufferedReader(new InputStreamReader(is));
					if ((receive = br.readLine()) != null) {
						System.out.println(receive);
						if (receive.substring(0, 3).equals("yes")) {
							comboBox.addItem(receive.substring(3));
						} else if (receive.substring(0, 4).equals("exit")) {
							comboBox.removeItem(receive.substring(4));
						} else {
							textArea.append(receive + "\n");
						}
					}
					Thread.sleep(1000);

				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}