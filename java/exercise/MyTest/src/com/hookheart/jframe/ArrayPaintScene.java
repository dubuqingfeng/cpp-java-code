package com.hookheart.jframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 粗线条可变换颜色，类按钮
 */
public class ArrayPaintScene {

	public static void main(String args[]){
		JFrame jframe = new JFrame();
		jframe.setSize(1366, 768);
		jframe.setVisible(true);
		
		MyArrayJPanel jp = new MyArrayJPanel();
		jframe.add(jp);
		jframe.addMouseListener(jp);
		jframe.addMouseMotionListener(jp);
		
		jp.addMouseListener(jp);
		jp.addMouseMotionListener(jp);
		jp.setBackground(Color.lightGray);
		
		JButton btn_start = new JButton("ok");
		jp.add(btn_start);
		
		
	}
}
class MyArrayJPanel extends JPanel implements MouseListener,MouseMotionListener{

	int x, y;
	int xy[][] = new int[1000][5];
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int count = 0;
	int selectColor = 0;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		switch(selectColor){
			case 0:g.setColor(Color.white);xy[count][4] = 0;break;
			case 1:g.setColor(Color.BLACK);xy[count][4] = 1;break;
			case 2:g.setColor(Color.red);xy[count][4] = 2;break;
		}
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x1 + 1, y1 + 1, x2 + 1, y2 + 1);
		g.drawLine(x1 + 2, y1 + 2, x2 + 2, y2 + 2);
		g.drawLine(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
		g.drawLine(x1 - 2, y1 - 2, x2 - 2, y2 - 2);
		for(int i = 0; i < count; i++){
			switch(xy[i][4]){
			case 0:g.setColor(Color.white);xy[i][4] = 0;break;
			case 1:g.setColor(Color.BLACK);xy[i][4] = 1;break;
			}
			g.drawLine(xy[i][0], xy[i][1], xy[i][2], xy[i][3]);
			g.drawLine(xy[i][0] + 1, xy[i][1] + 1, xy[i][2] + 1, xy[i][3] + 1);
			g.drawLine(xy[i][0] + 2, xy[i][1] + 2, xy[i][2] + 2, xy[i][3] + 2);
			g.drawLine(xy[i][0] - 1, xy[i][1] - 1, xy[i][2] - 1, xy[i][3] - 1);
			g.drawLine(xy[i][0] - 2, xy[i][1] - 2, xy[i][2] - 2, xy[i][3] - 2);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!e.isMetaDown()){
			x2 = e.getX();
			y2 = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.isMetaDown()){
			switch(selectColor){
			case 0:selectColor = 1;break;
			case 1:selectColor = 0;break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!e.isMetaDown()){
			x1 = e.getX();
			y1 = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!e.isMetaDown()){
			xy[count][0] = x1;
			xy[count][1] = y1;
			xy[count][2] = x2;
			xy[count][3] = y2;
			count++;
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}