
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
import java.awt.image.BufferedImage;
import java.io.File;



public class PaletteWindow extends Frame implements WindowListener, Runnable, KeyListener, MouseListener{

	//window stuff
	private boolean isRunning,isDone;
	private Image imgBuffer;
	
	private BufferedImage[] mapElements;
	
	private int windowX, windowY;
	private int spriteSheetX, spriteSheetY, xRes, yRes;

	
	public PaletteWindow(BufferedImage[] elements, int yd, int xd, int xr, int yr){
		super();
		spriteSheetX = xd;
		spriteSheetY = yd;
		xRes = xr; 
		yRes = yr;
		
		
		
		windowX = xRes*spriteSheetX + 7;
		windowY = 29 + 40 + yRes*spriteSheetY;
		
		
		imgBuffer = this.createImage(windowX, windowY);
		
		mapElements = elements;
		
		
		
		
		//more window stuff
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setSize(windowX,windowY);
		this.setTitle("Palette");
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
		
		
		g2.drawImage(mapElements[EditorWindow.getBrushValue()], 3, 25, null);
		
		
		for(int i = 0; i < spriteSheetX; i++){
			for(int j = 0; j < spriteSheetY; j++){
				g2.drawImage(mapElements[0+(j*spriteSheetX) + i], 3+i*xRes, 25 + 40 + yRes*j, null);//map[0+(i*32) + j]
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
		//while(true){
		//	if(isDone){
			//	System.exit(0);
			//}try{
		//		Thread.sleep(100);
		//	}catch(InterruptedException ie){
		//		ie.printStackTrace();
		//	}
			
		//}
		
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
		if(arg0.getY() >= 25+40){
			int i = arg0.getX()/xRes;
			int j = (arg0.getY()-65)/yRes;
			
			
			
			EditorWindow.setBrushValue(0+(j*spriteSheetX) + i);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}