package com.hookheart.qq;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class QQLogin extends JFrame implements ActionListener {
	private JTextField textUser;
	private JPasswordField textPass;
	private PrintWriter pw;
	private Socket socket;

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public QQLogin() {
		// 設置窗口屬性
		this.setSize(275, 125);
		this.setResizable(false);
		this.setLocation(400, 300);
		this.setTitle("qqlogin");
		// new一大堆對象
		JLabel labUser = new JLabel("User:");
		JLabel labPass = new JLabel("Password:");

		textUser = new JTextField();

		textPass = new JPasswordField();
		textPass.addActionListener(this);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);

		// 添加輸入面板
		JPanel panInput = new JPanel(new GridLayout(2, 2));
		panInput.add(labUser);
		panInput.add(textUser);
		panInput.add(labPass);
		panInput.add(textPass);
		// 添加按鈕面板
		JPanel panButton = new JPanel(new FlowLayout());
		panButton.add(btnLogin);
		panButton.add(btnCancel);
		panButton.add(btnRegister);

		// 添加窗體
		this.setLayout(new BorderLayout());
		this.add(panButton, BorderLayout.SOUTH);
		this.add(panInput, BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		QQLogin qqlogin = new QQLogin();
		qqlogin.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Register")) {
			try {
				String username = textUser.getText();
				String password = String.valueOf(textPass.getPassword());
				PrintWriter pw = new PrintWriter(new FileWriter(new File(
						"/home/user/users.txt"), true), true);
				pw.println(username + "===hookheart===" + password);
				pw.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getActionCommand().equals("Cancel")) {
			System.exit(0);
		} else {
			String username = textUser.getText();
			String password = String.valueOf(textPass.getPassword());
			try {
				socket = new Socket("127.0.0.1", 8886);
				socket.setKeepAlive(true);
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(
						socket.getOutputStream()), true);
				pw.println("Login" + username + "===hookheart===" + password);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String receiveMess = null;
				receiveMess = br.readLine();
				System.out.println(receiveMess);
				if (receiveMess.substring(0, 3 + username.length()).equals(
						"yes" + username)) {
					QQActivity qqactivity = new QQActivity(username,
							receiveMess.substring(3 + username.length()));
					qqactivity.connection();
					qqactivity.login = true;
					this.setVisible(false);
					qqactivity.setVisible(true);
				}
				pw.close();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}