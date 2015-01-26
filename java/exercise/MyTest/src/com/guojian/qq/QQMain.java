package com.guojian.qq;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;


public class QQMain extends JFrame implements ActionListener,Runnable{

	JTextField txtMess = new JTextField();
	JTextArea txtCon = new JTextArea();
	JComboBox cmbUser = new JComboBox();
	private Socket s;
	private JFileChooser jfileChooser;
	private String filedirectory;
	private File file;
	public void setSocket(Socket s){
		this.s = s;
		Thread t = new Thread(this);
		t.start();
	}
		public QQMain(String title){
			this.setSize(300,768);
			this.setLocation(200, 0);
			this.setTitle(title);
			
			JButton btnSend = new JButton("��������");
			jfileChooser = new JFileChooser();
			txtMess.addActionListener(this);
			JScrollPane spCon = new JScrollPane(txtCon);
			btnSend.addActionListener(this);
			JPanel panSmall = new JPanel();
			panSmall.setLayout(new GridLayout(1,2));
			panSmall.add(cmbUser);
			panSmall.add(btnSend);
			JPanel panBig = new JPanel();
			panBig.setLayout(new GridLayout(2,1));
			panBig.add(txtMess);
			panBig.add(panSmall);
			cmbUser.addItem("群发");
			this.setLayout(new BorderLayout());
			this.add(panBig,BorderLayout.NORTH);
			this.add(spCon,BorderLayout.CENTER);
			this.add(jfileChooser,BorderLayout.SOUTH);
			try {
				File f = new File("/home/user/bbb.txt");
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				while(br.ready()){
					txtCon.append(br.readLine()+"\n");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		
		
		
	public void actionPerformed(ActionEvent e) {
		//txtMess.......................>>txtCon
		txtCon.append(txtMess.getText()+"\n");
		
		try {
			File f = new File("/home/user/bbb.txt");
			FileWriter fw = new FileWriter(f,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(txtMess.getText());
			pw.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		try {
			OutputStream os = s.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osr,true);
			pw.println(cmbUser.getSelectedItem()+"%"+txtMess.getText());
		} catch (Exception e2) {
		}
		txtMess.setText("");
	}

		@Override
		public void run() {
			try {
				InputStream is = s.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				while(true){
					jfileChooser.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							file = jfileChooser.getSelectedFile();
							System.out.println(jfileChooser.accept(file));
							filedirectory = file.getAbsolutePath();
							System.out.println(filedirectory);
							if(filedirectory != null){
								txtMess.setText(filedirectory);
							}
						}
						
					});
					
					String message = br.readLine();
					String to = message.split("%")[0];
					String mess = message.split("%")[1];
					if(to.equals("user")){
						cmbUser.addItem(mess);
					}
					if(to.equals("mess")){
						txtCon.append(mess+"\n");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
		
}