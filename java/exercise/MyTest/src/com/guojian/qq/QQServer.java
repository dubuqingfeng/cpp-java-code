package com.guojian.qq;
import java.io.*;
import java.net.*;
import java.util.*;


public class QQServer{
	
	public static void main(String[] args) {
		
		
		HashMap hm = new HashMap();
		try {
			ServerSocket ss = new ServerSocket(8000);
			while(true){
				System.out.println("Server startup in 1000ms.........");
				Socket s = ss.accept();
				Myservice t = new Myservice();
				t.setHashMap(hm);
				t.setSocket(s);
				t.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		
	}
	
}
class Myservice extends Thread {
	private Socket s ;
	public void setSocket(Socket s){
		this.s = s;
	}
	private HashMap hm;
	public void setHashMap(HashMap hm){
		this.hm = hm;
	}
	public void run(){
		
		try {
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String uandp = br.readLine();
			String user = uandp.split("%")[0];
			String pass = uandp.split("%")[1];
			File f = new File("/home/user/guojian.txt");
			FileReader fr = new FileReader(f);
			BufferedReader fbr = new BufferedReader(fr);
			boolean yorn = false;
			while(fbr.ready()){
				String users = fbr.readLine();
				String uu = users.split("===")[0];
				String pp = users.split("===")[1];
				if(user.equals(uu)&&pass.equals(pp)){
					yorn = true;
					break;
				}
			}
			OutputStream os = s.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osr,true);
			if(yorn){
				pw.println("ok");
				for(Object temp:hm.values()){
					Socket ts = (Socket)temp;
					OutputStream tos = ts.getOutputStream();
					OutputStreamWriter tosr = new OutputStreamWriter(tos);
					PrintWriter tpw = new PrintWriter(tosr,true);
					tpw.println("user%"+user);
				}
				for(Object temp:hm.keySet()){
					pw.println("user%"+(String)temp);
				}
				hm.put(user, s);
				
				while(true){
					String message = br.readLine();
					String to = message.split("%")[0];
					String mess = message.split("%")[1];
					if(to.equals("群发")){
						//这里给所有人发消息，获取到所有的socket
						for(Object temp:hm.values()){
							
							Socket ts = (Socket)temp;
							if(!ts.equals(s)){
								OutputStream tos = ts.getOutputStream();
								OutputStreamWriter tosr = new OutputStreamWriter(tos);
								PrintWriter tpw = new PrintWriter(tosr,true);
								tpw.println("mess%" + mess);
							}
							
						}
					}else{
						Object temp = hm.get(to);
						Socket ts = (Socket)temp;
						OutputStream tos = ts.getOutputStream();
						OutputStreamWriter tosr = new OutputStreamWriter(tos);
						PrintWriter tpw = new PrintWriter(tosr,true);
						tpw.println("mess%"+mess);
					}
					
				}
			}else{
				pw.println("err");
			}
				
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
	