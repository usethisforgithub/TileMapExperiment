
import java.awt.Color;
import java.awt.Desktop;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

import org.apache.commons.io.FileUtils;


//needs preference file
public class MenuWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	
	int windowX, windowY;
	

	
	public MenuWindow(){
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
		g2.drawString("NEW", 100, 75);
		
		
		g2.setColor(Color.gray);
		g2.fillRect(3, 105, 300, 184);
		
		g2.setColor(Color.white);
		g2.drawString("LOAD", 100, 155);
		
		g2.setColor(Color.black);
		g2.fillRect(3, 185, 300, 264);
		
		g2.setColor(Color.white);
		g2.drawString("Add Palette", 100, 235);
		
		
		g2.setColor(Color.gray);
		g2.fillRect(3, 265, 300, 345);
		
		g2.setColor(Color.white);
		g2.drawString("About/Help", 100, 315);
		
			
		
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
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 25 && arg0.getY() < 104)) {
			
			
			//has user select ss file out of the ss directory and stores it as file
			File file = null;
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			File dir = new File("spriteSheetDir");
			fileChooser.setCurrentDirectory(dir);
			int returnVal = fileChooser.showOpenDialog(parentFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				file = fileChooser.getSelectedFile();
				//gets user input for the x and y of new map and launches the editor using x,y, and file
				JTextField aField = new JTextField(5);
				JTextField bField = new JTextField(5);

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
						EditorWindow window = new EditorWindow(x,y,file);
						new Thread(window).start();
					}
					else {
						System.out.println("One or more fields was left empty. New SCS simulation was not started.");
					}
				}
			}
			
			
		
			
			
		}
		
		//load
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 105 && arg0.getY() <184)) {
			
			//user selects save file and launches new editor using loaded x loaded y loaded map and file
			int loadedX = 0;
			int loadedY = 0;
			int loadedXr = 0;
			int loadedYr = 0;
			int[] loadedMap = null;
			File saveFile = null;
			File spriteSheet = null;
			
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			
			File dir=new File("saves");
			System.out.println(dir.getAbsolutePath());
			if(!dir.exists()){
			
			dir.mkdir();
			}
			
			fileChooser.setCurrentDirectory(dir);
			int returnVal = fileChooser.showOpenDialog(parentFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				saveFile = fileChooser.getSelectedFile();
				//this line is where i should default the filechooser to save directory
			}
			
			
			
			try {
				
				FileReader fr = new FileReader(saveFile);
				BufferedReader in = new BufferedReader(fr);
				String line;
				int numLines = 0;
				
				line = in.readLine();
				line = in.readLine();
				line = in.readLine();
				while((line = in.readLine()) != null){
					  //use String file here
						numLines++;
					}
				
				fr = new FileReader(saveFile);
				in = new BufferedReader(fr);
				
				line = in.readLine();
				spriteSheet = new File(line);
				
				line = in.readLine();
				String x = line.substring(0,line.indexOf(" "));
				String y = line.substring(line.indexOf(" ") + 1);
				
				line = in.readLine();
				String xr = line.substring(0,line.indexOf(" "));
				String yr = line.substring(line.indexOf(" ") + 1);
				
				
				
				String[] stringMap = new String[numLines];
				
				int i = 0;
				while((line = in.readLine()) != null){
					  //use String file here
						stringMap[i] = line;
						i++;
				}
				
				
				loadedX = Integer.parseInt(x);
				loadedY = Integer.parseInt(y);
				loadedXr = Integer.parseInt(xr);
				loadedYr = Integer.parseInt(yr);
				loadedMap = new int[numLines];
				
				for(int j = 0; j < numLines; j++) {
					loadedMap[j] = Integer.parseInt(stringMap[j]);
				}
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//some how get the loaded data
			
			EditorWindow window = new EditorWindow(loadedX,loadedY,loadedMap,spriteSheet);
			new Thread(window).start();
		}
		
		//Add spritesheet
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 185 && arg0.getY() < 264)) {
			
			
			File dir=new File("spriteSheetDir");
			System.out.println(dir.getAbsolutePath());
			if(!dir.exists()){
			
			dir.mkdir();
			}
			
			//selects file and copies it into spriteSheetDir
			File sheetToAdd = null;
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(parentFrame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				sheetToAdd = fileChooser.getSelectedFile();
			}
			File dest = new File(dir, sheetToAdd.getName());
			try {
			    FileUtils.copyFile(sheetToAdd, dest);
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			
			
			//user inputs reference data for the added ss which is saved to reference
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
			
			myPanel.add(new JLabel("Enter x res:"));
			myPanel.add(cField);

			myPanel.add(Box.createVerticalStrut(15));

			myPanel.add(new JLabel("Enter y res:"));
			myPanel.add(dField);

			myPanel.add(Box.createVerticalStrut(15));

			int result = JOptionPane.showConfirmDialog(null, myPanel, " Enter dimensions for new tilemap", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION) {
				String temp1 = aField.getText();
				String temp2 = bField.getText();
				String temp3 = cField.getText();
				String temp4 = dField.getText();
				
				if (!temp1.equals("") && !temp2.equals("") && !temp3.equals("") && !temp4.equals("")) {
					int x = Integer.parseInt(temp1);
					int y = Integer.parseInt(temp2);
					int xr = Integer.parseInt(temp3);
					int yr = Integer.parseInt(temp4); 
					
					
					try
					{
					    String filename= "reference.txt";
					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					    BufferedWriter bw = new BufferedWriter(fw);
					    
					    bw.write(dest.getAbsolutePath());//appends the string to the file
					    bw.newLine();
					    bw.write("" + x +" " + y);
					    bw.newLine();
					    bw.write("" + xr +" " + yr);
					    bw.newLine();
					    bw.close();
					}
					catch(IOException ioe)
					{
					    System.err.println("IOException: " + ioe.getMessage());
					}
					
					
					
				}
				else {
					System.out.println("One or more fields was left empty. New SCS simulation was not started.");
				}
			}
			
			
			
			
			
			
			
			
		}
		
		//About/help
		if((arg0.getX() >= 3 && arg0.getX() <= windowX-4) && (arg0.getY() >= 265 && arg0.getY() < 344)) {
			System.out.println("Sorry, this feature doesn't work yet");
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
