
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



public class MenuWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	
	int windowX, windowY;
	

	
	public MenuWindow(){
		super();
		
		windowX = 300;
		windowY = 501;
		
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
		g2.fillRect(3, 25, 300, 236);
		
		g2.setColor(Color.gray);
		g2.fillRect(3, 261, 300, 236);
		
	
		
			
		
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
		//new
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 25 && arg0.getY() < 261)) {
			JTextField aField = new JTextField(5);
			JTextField bField = new JTextField(5);
			JTextField cField = new JTextField(5);
			JTextField dField = new JTextField(5);

			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			myPanel.add(new JLabel("Enter x dim:"));
			myPanel.add(aField);

			myPanel.add(Box.createVerticalStrut(15));

			myPanel.add(new JLabel("Enter y dim:"));
			myPanel.add(bField);

			myPanel.add(Box.createVerticalStrut(15));

			int result = JOptionPane.showConfirmDialog(null, myPanel, " Enter dimensions for new tilemap", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION) {
				String temp1 = aField.getText();
				String temp2 = bField.getText();

				if (!temp1.equals("") && !temp2.equals("")) {
					int x = Integer.parseInt(temp1);
					int y = Integer.parseInt(temp2);
					EditorWindow window = new EditorWindow(x,y);
					new Thread(window).start();
				}
				else {
					System.out.println("One or more fields was left empty. New SCS simulation was not started.");
				}
			}
		}
		
		//load
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 261 && arg0.getY() < windowY-4)) {
			int loadedX = 0;
			int loadedY = 0;
			int[] loadedMap = null;
			
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(parentFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				try {
					//use file to scan the file
					FileReader fr = new FileReader(file);
					BufferedReader in = new BufferedReader(fr);
					String line;
					try {
						while((line = in.readLine()) != null){
						  //use String file here
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					} catch (FileNotFoundException e) {
					System.out.println("File not found.");
				} catch(NumberFormatException e){
					
				}catch(NoSuchElementException e){
					
				}
				
			}
			
			
			//some how get the loaded data
			EditorWindow window = new EditorWindow(loadedX,loadedY,loadedMap);
			new Thread(window).start();
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
