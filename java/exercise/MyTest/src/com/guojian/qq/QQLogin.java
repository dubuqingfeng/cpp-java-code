package com.guojian.qq;
import java.awt.*;

import javax.swing.*;

import java.io.*;
import java.awt.event.*;
import java.net.*;

public class QQLogin extends JFrame implements ActionListener{
	JTextField txtUser = new JTextField();
	JPasswordField txtPass = new JPasswordField();
	public QQLogin(){
		this.setSize(250,125);
		this.setLocation(400, 300);
		this.setTitle("QQµÇÂŒ");
		JLabel labUser = new JLabel("ÓÃ»§Ãû");
		JLabel labPass = new JLabel("ÃÜÂë");
		
		JButton btnLogin = new JButton("µÇÂŒ");
		JButton btnReg = new JButton("×¢²á");
		JButton btnCancel = new JButton("È¡Ïû");
		btnLogin.addActionListener(this);
		btnReg.addActionListener(this);
		btnCancel.addActionListener(this);
		JPanel panInput = new JPanel();
		panInput.setLayout(new GridLayout(2,2));
		panInput.add(labUser);
		panInput.add(txtUser);
		panInput.add(labPass);
		panInput.add(txtPass);
		JPanel panButton = new JPanel();
		panButton.setLayout(new FlowLayout());
		panButton.add(btnLogin);
		panButton.add(btnReg);
		panButton.add(btnCancel);
		this.setLayout(new BorderLayout());
		this.add(panInput,BorderLayout.CENTER);
		this.add(panButton,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		QQLogin w = new QQLogin();
		w.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			
		if(e.getActionCommand().equals("µÇÂŒ")){
			String user = txtUser.getText();
			String pass = txtPass.getText();
			try {	
				Socket s = new Socket("127.0.0.1",8000);
				OutputStream os = s.getOutputStream();
				OutputStreamWriter osr = new OutputStreamWriter(os);
				PrintWriter pw = new PrintWriter(osr,true);
				pw.println(user+"%"+pass);
				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String yorn = br.readLine();
				
				
				if(yorn.equals("ok")){
					QQMain w = new QQMain(user);
					w.setSocket(s);
					w.setVisible(true);
					this.setVisible(false);
				}
	
			} catch (Exception e2) {
			// TODO: handle exception
			}
		}
		if(e.getActionCommand().equals("×¢²á")){
			
		}
		if(e.getActionCommand().equals("È¡Ïû")){
			
		}
}}