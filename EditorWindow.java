
import java.awt.Color;
import java.awt.Frame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class EditorWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener, MouseMotionListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	private File spriteSheet;
	private static int brushValue = 0;
	
	
	private int numMapTilesX, numMapTilesY, windowX, windowY, spriteSheetX, spriteSheetY, xRes, yRes;
	
	private int[] map;
	
	private BufferedImage[] mapElements;
	
	public EditorWindow(int x, int y, File s){
		super();
		
		brushValue = 0;
		
		spriteSheet = s;
		
		try {
			//use file to scan the file
			FileReader fr = new FileReader("reference.txt");
			BufferedReader in = new BufferedReader(fr);
			String line;
			try {
				
				while((line = in.readLine()) != null){
				  if(line.equals(spriteSheet.getAbsolutePath())) {
					  line = in.readLine();
					  String tempXdim = line.substring(0,line.indexOf(" "));
					  String tempYdim = line.substring(line.indexOf(" ") + 1);
					  line = in.readLine();
					  String tempXres = line.substring(0,line.indexOf(" "));
					  String tempYres = line.substring(line.indexOf(" ") + 1);
					  
					  spriteSheetX = Integer.parseInt(tempXdim); 
					  spriteSheetY = Integer.parseInt(tempYdim); 
					  xRes = Integer.parseInt(tempXres);
					  yRes = Integer.parseInt(tempYres);
					  
					  break;
				  }
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
		
		
		
		
		
		
		numMapTilesX = x;
		numMapTilesY = y;
		
		map = new int[x*y];
		
		for(int i = 0; i < map.length; i++){
			map[i] = 0;//fills entire map with block 1247
		}
		
		windowX = 20 + xRes*numMapTilesX + 7;
		windowY = 29 + 40 + yRes*numMapTilesY;
		
		
		
		
		
		imgBuffer = this.createImage(windowX, windowY);
	
		//will use spritesheet filepath and sprite reference file
		mapElements = SpriteSheet.getAsArray(spriteSheet.getAbsolutePath(), spriteSheetY, spriteSheetX, xRes, yRes);
		
		
		//more window stuff
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setSize(windowX,windowY);
		this.setTitle("");
		isRunning = true;
		isDone = false;
		this.setVisible(true);
		this.setResizable(false);
		
		
	
		
	}
	
	public EditorWindow(int x, int y, int[] m, File s){
		super();
		
		brushValue = 0;
		
spriteSheet = s;
		
		try {
			//use file to scan the file
			FileReader fr = new FileReader("reference.txt");
			BufferedReader in = new BufferedReader(fr);
			String line;
			try {
				
				while((line = in.readLine()) != null){
				  if(line.equals(spriteSheet.getAbsolutePath())) {
					  line = in.readLine();
					  String tempXdim = line.substring(0,line.indexOf(" "));
					  String tempYdim = line.substring(line.indexOf(" ") + 1);
					  line = in.readLine();
					  String tempXres = line.substring(0,line.indexOf(" "));
					  String tempYres = line.substring(line.indexOf(" ") + 1);
					  
					  spriteSheetX = Integer.parseInt(tempXdim); 
					  spriteSheetY = Integer.parseInt(tempYdim); 
					  xRes = Integer.parseInt(tempXres);
					  yRes = Integer.parseInt(tempYres);
					  
					  break;
				  }
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
		
		
		numMapTilesX = x;
		numMapTilesY = y;
		
		map = m;
		
		
		
		windowX = 20 + xRes*numMapTilesX + 7;
		windowY = 29 + 40 + yRes*numMapTilesY;
		

		imgBuffer = this.createImage(windowX, windowY);
	
		mapElements = SpriteSheet.getAsArray(spriteSheet.getAbsolutePath(), spriteSheetY, spriteSheetX, xRes, yRes);
		
		
		//more window stuff
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setSize(windowX,windowY);
		this.setTitle("");
		isRunning = true;
		isDone = false;
		this.setVisible(true);
		this.setResizable(false);
		
		
	
		
	}
	
	public void run(){
		while(isRunning){
			draw();
			
			
			
			
			try{
				Thread.sleep(100);
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
		//g2.setColor(new Color(153,204,255));
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.drawImage(mapElements[brushValue], 23, 25, null);
		
		g2.setColor(Color.red);
		g2.fillRect(3, 25, 20, 20);
		
		g2.setColor(Color.green);
		g2.fillRect(43, 25, 20, 20);
		
		//g2.drawImage(mapElements[tileCounter], 25, 25, null);
		for(int i = 0; i < numMapTilesX; i++){
			for(int j = 0; j < numMapTilesY; j++){
				g2.drawImage(mapElements[map[0+(j*numMapTilesX) + i]], 3+20+i*xRes, 25 + 40 + yRes*j, null);//map[0+(i*32) + j]
			}
		}
		
			
		
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
		
		//save button
		if((arg0.getX() >= 3 && arg0.getX() <= 23) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			
			 
			File dir=new File("saves");
			System.out.println(dir.getAbsolutePath());
			if(!dir.exists()){
			
			dir.mkdir();
			}
				
			
			
			
			JTextField aField = new JTextField(5);
		

			JPanel myPanel = new JPanel();
			myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

			myPanel.add(new JLabel("Enter map name:"));
			myPanel.add(aField);

			myPanel.add(Box.createVerticalStrut(15));

			int result = JOptionPane.showConfirmDialog(null, myPanel, " Enter dimensions for new tilemap", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.OK_OPTION) {
				String temp1 = aField.getText();
				
				
				if (!temp1.equals("")) {
					
					
					
					try
					{
					    File filename = new File(dir, temp1+".txt");
					    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
					    BufferedWriter bw = new BufferedWriter(fw);
					    
					    bw.write(spriteSheet.getAbsolutePath());
						bw.newLine();
						bw.write(numMapTilesX + " " +numMapTilesY);
						bw.newLine();
						bw.write("20 20");
						bw.newLine();
						
						for(int i = 0; i < map.length; i++) {
							bw.write("" + map[i]);
							bw.newLine();
						}
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
		
		//if palette button is clicked
		if((arg0.getX() >= 23 && arg0.getX() <= 43) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			PaletteWindow window = new PaletteWindow(mapElements,spriteSheetY,spriteSheetX,xRes,yRes);
			new Thread(window).start();
		}
		
		if((arg0.getX() >= 23 && arg0.getX() <= 3+20+xRes*numMapTilesX) && (arg0.getY() >= 65 && arg0.getY() <= 65+yRes*numMapTilesY)){
			int i = (arg0.getX() - 23)/xRes;
			int j = (arg0.getY()-65)/yRes;
			int index = 0+(j*numMapTilesX) + i;
			
			map[index] = brushValue;
		}
		
		//export button
		if((arg0.getX() >= 43 && arg0.getX() <= 63) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			BufferedImage exportImage = new BufferedImage(20*numMapTilesX,20*numMapTilesY,mapElements[0].getType());//20*numMapTilesX, 20*numMapTilesY
			Graphics2D gr = exportImage.createGraphics();
			
			for(int i = 0; i < numMapTilesX; i++){
				for(int j = 0; j < numMapTilesY; j++){
					gr.drawImage(mapElements[map[0+(j*numMapTilesX) + i]], i*20, 20*j, null);//map[0+(i*32) + j]
				}
			}
			
			
			
			
			
			JFrame parentFrame = new JFrame();
			 
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");   
			//fileChooser.setCurrentDirectory("desktop");
			 
			int userSelection = fileChooser.showSaveDialog(parentFrame);
			 
			
			File file = null;
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				
					file = fileChooser.getSelectedFile();
					
	
					System.out.println(file + ".png");
				
			
			}
			
			
			
			
			try {
				ImageIO.write(exportImage, "png", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public BufferedImage[] concat(BufferedImage[] a, BufferedImage[] b) {
		   int aLen = a.length;
		   int bLen = b.length;
		   BufferedImage[] c= new BufferedImage[aLen+bLen];
		   System.arraycopy(a, 0, c, 0, aLen);
		   System.arraycopy(b, 0, c, aLen, bLen);
		   return c;
	}
	
	
	
	public static int getBrushValue(){
		return brushValue;
	}
	
	public static void setBrushValue(int a){
		brushValue = a;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		if((arg0.getX() >= 23 && arg0.getX() <= 3+20+20*numMapTilesX) && (arg0.getY() >= 65 && arg0.getY() <= 65+20*numMapTilesY)){
			int i = (arg0.getX() - 23)/20;
			int j = (arg0.getY()-65)/20;
			int index = 0+(j*numMapTilesX) + i;
			
			map[index] = brushValue;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
