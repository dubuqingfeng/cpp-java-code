package com.hookheart.jframe;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.lang.model.element.ElementKind;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * ArrayList画线条
 */
public class PaintScene {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setSize(1366, 768);
		jframe.setVisible(true);
		MyJPanel jp = new MyJPanel();
		jframe.add(jp);
		jframe.addMouseListener(jp);
		jframe.addMouseMotionListener(jp);
	}

}
class MyJPanel extends JPanel implements MouseListener, MouseMotionListener{

	int x, y;
	private Point e= new Point();
	private ArrayList<Point> line = new ArrayList<Point>();
	private boolean flag;
	
	@Override
	public void update(Graphics g) {
		super.update(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		for(int i = 0; i < line.size(); i += 2){
			//当i的值小于个数的时候,并且个数为偶数时，
			
			if(i + 1 <= line.size()){
				if(flag){
					if(line.size() % 2 != 0){
						e = line.get(line.size()-1);
						line.add(e);
					}
					flag = false;
				}
				try{
					g.drawLine(line.get(i).x, line.get(i).y, line.get(i + 1).x, line.get(i + 1).y);
				}catch(IndexOutOfBoundsException ex){
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		line.add(e.getPoint());
		flag = true;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		flag = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		line.add(e.getPoint());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}
