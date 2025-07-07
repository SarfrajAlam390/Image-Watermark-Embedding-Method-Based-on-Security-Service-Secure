package visual.security.evaluation.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageImportance {
	
	public double percentageCal(BufferedImage imgA,BufferedImage imgB) 
	{
		double percentage = 0;
		int width1 = imgA.getWidth(); 
        int width2 = imgB.getWidth(); 
        int height1 = imgA.getHeight(); 
        int height2 = imgB.getHeight(); 
        if ((width1 != width2) || (height1 != height2)) 
            System.out.println("Error: Images dimensions"+ 
                                             " mismatch"); 
        else
        { 
            long difference = 0; 
            for (int y = 0; y < height1; y++) 
            { 
                for (int x = 0; x < width1; x++) 
                { 
                    int rgbA = imgA.getRGB(x, y); 
                    int rgbB = imgB.getRGB(x, y); 
                    int redA = (rgbA >> 16) & 0xff; 
                    int greenA = (rgbA >> 8) & 0xff; 
                    int blueA = (rgbA) & 0xff; 
                    int redB = (rgbB >> 16) & 0xff; 
                    int greenB = (rgbB >> 8) & 0xff; 
                    int blueB = (rgbB) & 0xff; 
                    difference += Math.abs(redA - redB); 
                    difference += Math.abs(greenA - greenB); 
                    difference += Math.abs(blueA - blueB); 
                } 
            } 
            double total_pixels = width1 * height1 * 3;
            double avg_different_pixels = difference / total_pixels;
            percentage = (avg_different_pixels / 255) * 100; 
	}
        return percentage;
	}
	
	public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
	    final int w = img1.getWidth(),
	            h = img1.getHeight(), 
	            highlight = Color.MAGENTA.getRGB();
	    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	        }
	    }
	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    out.setRGB(0, 0, w, h, p1, 0, w);
	    System.out.println("Out--->"+out);
	    return out;
	}
}
