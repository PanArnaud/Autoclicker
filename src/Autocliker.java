/*
	Author: Arnaud Panapadéatchy
	GitHub: ArnaudPan
	Website: http://ar-pa.esy.es
	Version: 1.0
*/

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Autocliker {
	private static Robot robot = null;
	private static int time;

	public static void main(String[] args) {
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
		
		try {
			time =  Integer.parseInt(JOptionPane.showInputDialog("Temps: "));
		} catch (Exception e) {
			System.exit(0);
			}
		
		Point coordinates = getCoordinates();
		
		int i = 1;  
		while(i != (1000 / 25) * time){
			click(coordinates.x, coordinates.y);
			i++;
		}
		System.exit(0);
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
}
