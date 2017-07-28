import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	
	public SpriteSheet(){
	
	}
	
	public static BufferedImage[] getAsArray(String fileName, int rows, int cols, int xRes, int yRes){
		BufferedImage spriteSheet;
		BufferedImage[] images = new BufferedImage[rows*cols];
		
		try {
			spriteSheet = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			spriteSheet = null;
			e.printStackTrace();
		}
		
		int counter = 0;
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				images[counter] = new BufferedImage(xRes,yRes,spriteSheet.getType());
				Graphics2D gr = images[counter].createGraphics();
				gr.drawImage(spriteSheet, 0, 0, xRes,yRes,xRes*j,yRes*i,xRes*j + xRes, yRes*i + yRes, null);
				gr.dispose();
				counter ++;
			}
		}
		
		return images;
		
	}
}
