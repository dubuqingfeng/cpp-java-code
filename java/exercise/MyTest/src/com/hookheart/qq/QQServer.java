package com.hookheart.qq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

/*
 * 服务端一对多接收消息
 * 
 */
public class QQServer {

	private static ArrayList<MyThread> clients;
	private static ArrayList<String> users;

	public static void main(String[] args) {
		new QQServer();
	}

	public QQServer() {
		clients = new ArrayList<MyThread>();
		users = new ArrayList<String>();
		try {
			ServerSocket ss = new ServerSocket(8886);
			System.out.println("hello,I'm a server...");
			while (true) {
				Socket socket = ss.accept();
				MyThread thread = new MyThread(socket);
				thread.start();
				clients.add(thread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class MyThread extends Thread {
		private Socket socket;
		private PrintWriter pw;

		public PrintWriter getPw() {
			return pw;
		}

		public BufferedReader getBr() {
			return br;
		}

		private BufferedReader br;

		public MyThread(Socket socket) {
			this.socket = socket;
			try {
				br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(
						socket.getOutputStream()), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {

				String receiveMess = "";
				while ((receiveMess = br.readLine()) != null) {
					System.out.println(receiveMess);
					if (receiveMess.substring(0, 5).equals("Login")) {
						String username = receiveMess.substring(5).split(
								"===hookheart===")[0];
						String password = receiveMess.substring(5).split(
								"===hookheart===")[1];
						System.out.println(username + password);
						BufferedReader fbr = new BufferedReader(new FileReader(
								new File("/home/user/users.txt")));
						Boolean login = false;
						while (fbr.ready()) {
							String users = fbr.readLine();
							String user = users.split("===hookheart===")[0];
							String pass = users.split("===hookheart===")[1];
							if (username.equals(user) && password.equals(pass)) {
								login = true;
							}
						}

						if (login) {
							/*
							 * Set<Map.Entry<String, MyThread>> entrySet =
							 * hm.entrySet(); for(Map.Entry<String, MyThread>
							 * entry : entrySet){ //广播给其他人名字
							 * if(!entry.getKey().equals(username)) {
							 * hm.get(entry.getKey()).getPw().println("yes" +
							 * username); System.out.println(entry.getKey() +
							 * entry.getValue()); } }
							 */
							users.add(username);
							String sendMess = username + (users.size() - 1);

							for (int i = clients.size() - 2; i >= 0; i--) {
								clients.get(i).getPw()
										.println("yes" + username);
							}
							for (int i = users.size() - 2; i >= 0; i--) {
								sendMess = sendMess + "%" + users.get(i);

							}
							pw.println("yes" + sendMess);

						} else {
							pw.println("no");
						}
					} else if (receiveMess.substring(0, 4).equals("exit")) {
						users.remove(receiveMess.substring(4));
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getPw()
									.println("exit" + receiveMess.substring(4));
						}
					} else if(receiveMess.substring(0, 5).equals("scene")){
						for (int i = 1; i <= 50; i++) {
							String url = "http://piao.qunar.com/ticket/list.json?keyword=%E5%8C%97%E4%BA%AC&region=&from=mps_search_suggest&total=2288&page="+ i;
							String json = openUrl(url, "utf-8");
							JSONObject jsonMap = new JSONObject();
							Map map = jsonMap.fromObject(json);
							Map sightListMap = (Map) map.get("data");
							List<Map<String, Object>> list = (List) sightListMap.get("sightList");
							for (Map itemMap : list) {
								String sightId = itemMap.get("sightId").toString();
								String sightName = itemMap.get("sightName").toString();
								String address = itemMap.get("address").toString();
								String price = itemMap.get("qunarPrice").toString();
								// 景点详细页URL
								String detailUrl = "http://piao.qunar.com/ticket/detail_"
										+ sightId + ".html#from=qunarindex";
								// 景点详细页html
								String html = openUrl(detailUrl, "utf-8");
								for (int j = clients.size() - 1; j >= 0; j--) {
									clients.get(j).getPw().println(sightName + address + price);
								}
							}
						}
					} else {
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getPw().println(receiveMess);
						}
					}
					pw.flush();
				}
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 访问url返回url的html代码
		 */
		public String openUrl(String currentUrl, String charset) {
			InputStream is = null;
			BufferedReader br = null;
			URL url;
			StringBuffer html = new StringBuffer();
			try {
				url = new URL(currentUrl);
				URLConnection conn = url.openConnection();
				conn.setReadTimeout(5000);
				conn.connect();
				is = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(is, charset));
				String str;
				while (null != (str = br.readLine())) {
					html.append(str).append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			return html.toString();
		}

	}
	
}
