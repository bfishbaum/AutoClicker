import java.io.*;
import java.lang.Math;
import java.awt.Robot.*;
import java.awt.event.InputEvent;
import java.awt.event.InputEvent.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.JPanel;

public class AutoClicker extends JPanel implements KeyListener, Runnable {
	Robot Clicker;
	boolean stop;
	int clicks = 0;
	static final int startDelay = 1000;
	static final int delay = 50;

	double x = 100;
	double y = 300;
	double eps = 5;
	

	public AutoClicker(){
		super();
		stop = false;
		clicks = 0;
		try {
			Clicker = new Robot();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Thread th = new Thread(this);
		th.start();
	}

	public void run(){
		this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
		try{
			Thread.sleep(startDelay);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		PointerInfo mouseLoc = MouseInfo.getPointerInfo();
		double x2,y2;
		x2 = mouseLoc.getLocation().x;
		y2 = mouseLoc.getLocation().y;

		x = x2;
		y = y2;

		Clicker.mouseMove((int) x,(int) y);

		// loop ends when user moves their mouse
		while(!MovedAway(x2,y2)){
			Clicker.mousePress(InputEvent.BUTTON1_MASK);
			Clicker.mouseRelease(InputEvent.BUTTON1_MASK);
			clicks += 1;
			Clicker.delay(delay);

			mouseLoc = MouseInfo.getPointerInfo();
			x2 = mouseLoc.getLocation().x;
			y2 = mouseLoc.getLocation().y;
		}
	}

	public boolean MovedAway(double x2, double y2){
		return (Math.abs(x2 - x) > eps || Math.abs(y2-y) > eps);
	}

	@Override
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		System.out.println("something");
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			stop = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyCode());
		System.out.println("somethingr2");
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			stop = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
