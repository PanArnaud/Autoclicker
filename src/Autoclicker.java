/*
	Author: Arnaud Panapadéatchy
	GitHub: ArnaudPan
	Website: http://ar-pa.esy.es
	Version: 1.1
*/

import java.awt.AWTException;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Autoclicker extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	private static Robot robot = null;
	private static boolean pressed = false;
	private static boolean running = false;

	public static void main(String[] args) {
		new Autoclicker();
		
		while(running){
			update();
		}
	}
	
	public Autoclicker(){
		this.setTitle("Autoclicker");
	    this.setSize(270, 100);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(2, 1));
	    
	    JPanel header = new JPanel();
	    container.add(header);
	    header.add(new JLabel("<html>Positionnez la souris et<br>maintenez F10 pour cliquer.<html>"));
	    
	    this.addKeyListener(this);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    
	    running = true;
		
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	public static void update(){
		System.out.println(pressed);
		if(pressed == true){
			Point coordinates = getCoordinates();
			click(coordinates.x, coordinates.y);
		}
	}
	
	public static Point getCoordinates(){
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point location = pointer.getLocation();
		return location;
	}
	
	public static void click(int x, int y){
		robot.mouseMove(x,  y);
		robot.delay(25);
		robot.mousePress(MouseEvent.BUTTON1_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_MASK);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F10){
			pressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_F10){
			pressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
