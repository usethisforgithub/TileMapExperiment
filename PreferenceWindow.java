
import java.awt.Color;
import java.awt.Frame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PreferenceWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	
	int windowX, windowY;
	

	
	public PreferenceWindow(){
		super();
		
		windowX = 300;
		windowY = 349;
		
		imgBuffer = this.createImage(windowX, windowY);
		
		
		
		
		//more window stuff
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setSize(windowX, windowY);
		this.setTitle("Title");
		isRunning = true;
		isDone = false;
		this.setVisible(true);
		
		this.setResizable(false);
		
		
		
		
	}
	
	public void run(){
		while(isRunning){
			draw();
			
			
			
			
			try{
				Thread.sleep(10);
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
		}
		isDone = true;
	}
	
	
	public void draw(){
		imgBuffer = this.createImage(this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D)imgBuffer.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//background color
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
				
		g2.setColor(Color.black);
		g2.fillRect(3, 25, 300, 104);
		
		g2.setColor(Color.white);
		g2.drawString("Set Save Directory", 100, 75);
		
		
		g2.setColor(Color.gray);
		g2.fillRect(3, 105, 300, 184);
		
		g2.setColor(Color.white);
		g2.drawString("Set Spritesheet Directory", 100, 155);
		
		g2.setColor(Color.black);
		g2.fillRect(3, 185, 300, 264);
		
		g2.setColor(Color.white);
		g2.drawString("Set Export Directory", 100, 235);
		
		
		g2.setColor(Color.gray);
		g2.fillRect(3, 265, 300, 345);
		
		g2.setColor(Color.white);
		g2.drawString("View Current Preferences", 100, 315);
		
			
		
		g2 = (Graphics2D)this.getGraphics();
		g2.drawImage(imgBuffer, 0, 0, this.getWidth(), this.getHeight(), 0, 0, this.getWidth(), this.getHeight(), null);
		g2.dispose();
	}
	
	
	

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		while(true){
			if(isDone){
				System.exit(0);
			}try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		isRunning = false;
		this.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//preference window
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 25 && arg0.getY() < 104)) {
			
		}
		
		//set spritesheet
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 105 && arg0.getY() <184)) {
			
		}
		
		//set export directory
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 185 && arg0.getY() < 264)) {
			System.out.println("add s");
		}
		
		//view current preferences
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 265 && arg0.getY() < 344)) {
			System.out.println("preferences");		
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}