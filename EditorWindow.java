
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class EditorWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener, MouseMotionListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	private static int brushValue = 0;
	
	int numMapTilesX, numMapTilesY, windowX, windowY;
	
	 int[] map;
	
	BufferedImage[] mapElements;
	
	public EditorWindow(int x, int y){
		super();
		
		numMapTilesX = x;
		numMapTilesY = y;
		
		map = new int[x*y];
		
		for(int i = 0; i < map.length; i++){
			map[i] = 1247;//fills entire map with block 1247
		}
		
		windowX = 20 + 20*numMapTilesX + 7;
		windowY = 29 + 40 + 20*numMapTilesY;
		
		
		
		
		
		imgBuffer = this.createImage(windowX, windowY);
	
		mapElements = SpriteSheet.getAsArray("sum.png", 39, 32, 20, 20);
		
		
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
	
	public EditorWindow(int x, int y, int[] m){
		super();
		
		numMapTilesX = x;
		numMapTilesY = y;
		
		map = m;
		
		
		
		windowX = 20 + 20*numMapTilesX + 7;
		windowY = 29 + 40 + 20*numMapTilesY;
		

		imgBuffer = this.createImage(windowX, windowY);
	
		mapElements = SpriteSheet.getAsArray("sum.png", 39, 32, 20, 20);
		
		
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
		//g2.drawImage(mapElements[tileCounter], 25, 25, null);
		for(int i = 0; i < numMapTilesX; i++){
			for(int j = 0; j < numMapTilesY; j++){
				g2.drawImage(mapElements[map[0+(j*numMapTilesX) + i]], 3+20+i*20, 25 + 40 + 20*j, null);//map[0+(i*32) + j]
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
		
		//menu button
		if((arg0.getX() >= 3 && arg0.getX() <= 23) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			MenuWindow window = new MenuWindow();
			new Thread(window).start();
		}
		
		//if palette button is clicked
		if((arg0.getX() >= 23 && arg0.getX() <= 43) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			PaletteWindow window = new PaletteWindow();
			new Thread(window).start();
		}
		
		if((arg0.getX() >= 23 && arg0.getX() <= 3+20+20*numMapTilesX) && (arg0.getY() >= 65 && arg0.getY() <= 65+20*numMapTilesY)){
			int i = (arg0.getX() - 23)/20;
			int j = (arg0.getY()-65)/20;
			int index = 0+(j*numMapTilesY) + i;
			
			map[index] = brushValue;
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
		if((arg0.getX() >= 3 && arg0.getX() <= 23) && (arg0.getY() >= 25 && arg0.getY() <= 45)){
			PaletteWindow window = new PaletteWindow();
			new Thread(window).start();
		}
		
		if((arg0.getX() >= 23 && arg0.getX() <= 3+20+20*numMapTilesX) && (arg0.getY() >= 65 && arg0.getY() <= 65+20*numMapTilesY)){
			int i = (arg0.getX() - 23)/20;
			int j = (arg0.getY()-65)/20;
			int index = 0+(j*numMapTilesY) + i;
			
			map[index] = brushValue;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
