package com.hookheart.weather;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.json.JSONObject;

/**
 * 如何做一个文艺的天气预报？首先你需要一个文艺的思想。首先你需要做一个天气预报。 这是天气预报的界面 首先它有背景图 天气预报 json数据的解析
 * json-lib、org-json 本次使用json-lib 连接网络获取数据URL
 * http://www.weather.com.cn/data/cityinfo/101010100.html 将数据显示到界面上。 界面渐变。 切换背景
 * 不同城市
 * 
 * @author user
 * 
 */
public class MainJFrame extends JFrame {

	private BufferedImage backgroundimg;
	private BufferedReader br;
	private StringBuilder sb;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainJFrame mainjframe = new MainJFrame();
		mainjframe.initView();
		mainjframe.initData();
		mainjframe.setVisible(true);
	}

	private void initData() {
		URL url;
		try {
			url = new URL(
					"http://www.weather.com.cn/data/cityinfo/101010100.html");
			/*
			 * BufferedReader是字符流,需要转换流进行转换。
			 */
			br = new BufferedReader(new InputStreamReader(url.openStream(),
					"UTF-8"));
			sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null)
				sb.append(line);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject jsonData = JSONObject.fromObject(sb.toString());
		System.out.println(jsonData);
		JSONObject weatherinfo = jsonData.getJSONObject("weatherinfo");
		System.out.println(weatherinfo.getString("weather").toString());
		System.out.println(weatherinfo.getString("city").toString());
	}

	private void initView() {
		try {
			backgroundimg = ImageIO.read(new File("res/background/bj00.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel mainjpanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundimg, 0, 0, this.getWidth(),
						this.getHeight(), this);
			}
		};
		JLabel city = new JLabel();
		city.setForeground(Color.BLUE);
		city.setText("beijing");
		mainjpanel.add(city);
		this.add(mainjpanel);
		this.setSize(300, 500);
	}
}
