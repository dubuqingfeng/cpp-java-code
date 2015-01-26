package com.hookheart.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

/*
 * 随机位置*，运动的圆，月
 * @author user
 */
public class AsteriskScene {
	public static void main(String args[]) {
		Frame w = new Frame();
		MyPanel mp = new MyPanel();
		w.add(mp);
		w.setSize(1366, 768);
		w.setBackground(Color.BLACK);
		w.setVisible(true);

		Thread thread = new Thread(mp);
		thread.start();
	}
}

class MyPanel extends Panel implements Runnable {

	private int  x = 0;
	private int  y = 0;
	private int xvelocity = 1;
	private int yvelocity = 1;

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++) {
			Font font = new Font("", Font.BOLD,36);
			g.setFont(font);
			g.setColor(new Color((int) (Math.random() * 255), (int) (Math
					.random() * 255), (int) (Math.random() * 255)));
			g.drawString("*", (int) (Math.random() * 1366),
					(int) (Math.random() * 768));
		}
		g.fillOval(40 + 20*x,40 + 20* y, 80, 80);
		g.setColor(Color.white);
		g.fillOval(800, 100, 100, 100);
		g.setColor(Color.black);
		g.fillOval(790, 90, 80, 80);
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
		paint(g);
	}

	@Override
	public void run() {
		while (true) {
			x = x + xvelocity;
			y = y + yvelocity;
			if(x >  20){
				xvelocity = -xvelocity;
			}else if(x < 0){
				xvelocity = -xvelocity;
			}
			if(y > 20){
				yvelocity = -yvelocity;
			}else if(y < 0){
				yvelocity = -yvelocity;
			}
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}