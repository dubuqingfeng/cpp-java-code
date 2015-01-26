package com.hookheart.jframe;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.loon.framework.javase.game.GameDeploy;
import org.loon.framework.javase.game.GameScene;
import org.loon.framework.javase.game.core.graphics.Screen;
import org.loon.framework.javase.game.core.graphics.device.LGraphics;
import org.loon.framework.javase.game.core.timer.LTimerContext;
import org.loon.framework.javase.game.utils.GraphicsUtils;

/**
 * 這是遊戲的開始場景。 這裏有一個標題 一個背景選項
 * 主标题：不语有希
 * 
 * @author user
 * 
 */
public class GameStartScene {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameScene scene = new GameScene("窗口名字", true, 800, 480);
		// 得到此窗体所对应的游戏部署器
		GameDeploy deploy = scene.getDeploy();
		// 设定此游戏屏幕（在任何时候都可以通过Screen中的setScreen函数切换游戏屏幕）
		deploy.setScreen(new startScene());
		// 是否显示FPS
		deploy.setShowFPS(true);
		// 允许的最大刷新率
		deploy.setFPS(100);
		// 开始游戏主循环
		deploy.mainLoop();
		// 显示游戏画面
		scene.showFrame();

	}
}

class startScene extends Screen {
	BufferedImage[] image = new BufferedImage[10];

	@Override
	public void alter(LTimerContext arg0) {
	}
	

	@Override
	public void draw(LGraphics arg0) {
		try {
			image[0] = ImageIO.read(new File("res/background/start.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(image[0]);
		GraphicsUtils.setAntialias(arg0, true);
		arg0.setFont(GraphicsUtils.getFont("黑体", 100));
		GraphicsUtils.drawStyleString(arg0, "不语有希", 180, 200, Color.blue, Color.cyan);
		arg0.setFont(GraphicsUtils.getFont("华文新魏", 40));
		GraphicsUtils.drawStyleString(arg0, "开时有希", 300, 300, Color.WHITE, Color.black);
		GraphicsUtils.drawStyleString(arg0, "更欢北京", 300, 360, Color.WHITE, Color.black);
		GraphicsUtils.setAntialias(arg0, false);
		

	}
	public startScene(){
		
	}

	@Override
	public void onTouchDown(LTouch arg0) {

	}

	@Override
	public void leftClick(MouseEvent e) {
		super.leftClick(e);
	}


	@Override
	public void onTouchMove(LTouch arg0) {

	}

	@Override
	public void onTouchUp(LTouch arg0) {

	}

}
