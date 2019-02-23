import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IOfuncs {
	public static BufferedImage readInImage(String name) throws IOException{
		File f = new File(System.getProperty("user.dir")+"\\src\\"+name+".bmp");
		System.out.println(System.getProperty("user.dir")+"\\src\\"+name+".bmp");
		BufferedImage readImg = ImageIO.read(f);
		return readImg;
	}

	public static void output(BufferedImage newPic, String name) {
		  try{
	    	File outputDir = new File(System.getProperty("user.dir")+"\\src\\PleaseDanny\\"+name+".bmp");
	        ImageIO.write(newPic, "jpg", outputDir);
	      }catch(IOException e){
	        System.out.println("Error: "+e);
		  }
	  }
}
