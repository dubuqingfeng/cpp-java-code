package com.hookheart.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * 非匀速下降不同颜色飘零*，月
 */
public class SnowScene {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame f = new JFrame();
		f.setSize(1366, 768);
		
		panel p = new panel();
		p.setBackground(Color.black);
		Thread thread = new Thread(p);
		thread.start();
		f.addKeyListener(p);
		f.add(p);
		f.setVisible(true);
	}

}
class panel extends JPanel implements Runnable,KeyListener{

	int x[] = new int [300];
	int y[] = new int [300];
	int velocity = 1;
	
	@Override
	public void run() {
		for(int i = 0; i < 300; i++){
			x[i] = (int) (Math.random() * 1366);
			y[i] = (int) (Math.random() * 768);
		}
		while(true){
			for(int i = 0; i < 300; i++){
				velocity = (int) (Math.random() * 3);
				y[i] = y[i] + velocity;
				if(y[i] > 768){
					y[i] = 0;
				}
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i < 300; i++){
			Font font = new Font("", Font.BOLD , (int) (Math.random() * 2) + 10);
			g.setFont(font);
			g.setColor(new Color((int)(Math.random() * 255) , (int)(Math.random() * 255), (int)(Math.random() * 255)));
			g.drawString("*", x[i], y[i]);
		}
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
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode())
		{
			case 65:System.out.println("h");break;
			case 37:System.out.println("37" + e.getKeyCode());break;
			case 38:System.out.println("38" + e.getKeyCode());break;
		
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}