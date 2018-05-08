import java.io.*;
import java.awt.Robot.*;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.InputEvent.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class AutoClicker extends JPanel implements KeyListener, Runnable {
	Robot Clicker;
	boolean stop;
	int clicks = 0;
	static final int maxClicks = 50000;
	static final int delay = 50;
	static final int brake = 1000;
	static final int perClick = 1000;
	

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
		Clicker.delay(delay);
		Clicker.mouseMove(100,300);
		while(!stop && clicks < maxClicks){
			Clicker.mousePress(InputEvent.BUTTON1_MASK);
			Clicker.mouseRelease(InputEvent.BUTTON1_MASK);
			clicks += 1;
			Clicker.delay(delay);
			if(clicks % perClick == 0){
				Clicker.delay(brake);
			}
		}
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
