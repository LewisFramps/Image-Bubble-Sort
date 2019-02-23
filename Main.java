import java.awt.image.BufferedImage;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		// Variable setups
		System.out.println("It begins...");
		BufferedImage image = IOfuncs.readInImage("danny");		// object of the image
		int width = image.getWidth();									// width of image
		int height = image.getHeight();									// width of height
		int[][] posArray = new int[height][width];						// 2d array that holds the positions of the pixels from the original image
		posArray = setup_posArray(width, height, posArray);				// puts positions into posArray


		System.out.println("Randomising...");
		for(int i = 0; i < height*width; i++) {
			int[][] rPos = randomPos(width, height);
		    swapPixels(image, posArray, rPos[0][0], rPos[0][1], rPos[1][0], rPos[1][1]);
		    posArray = swapItem(posArray, rPos[0][0], rPos[0][1], rPos[1][0], rPos[1][1]);
		}
		IOfuncs.output(image, "Randomised Image");
		System.out.println("michael bubble sort inbound");
		bubble(posArray, image);
		IOfuncs.output(image, "Sorted Image");
		System.out.println("Noo, noo it oonds...");
	}

	public static int[][] setup_posArray(int w, int h, int[][] posArray){
		System.out.println("Setting up array...");
		int counter = 0;
		for(int i = 0; i < h; i++) {
			for(int n = 0; n < w; n++) {
				posArray[i][n] = counter;
				counter++;
			}
		}
		return posArray;
	}

	public static void swapPixels(BufferedImage image, int[][] posArray, int x1, int y1, int x2, int y2) {
		int[] temp = {image.getRGB(x1, y1), posArray[y1][x1]};
		image.setRGB(x1,y1,image.getRGB(x2,y2));
		image.setRGB(x2,y2,temp[0]);
	}

	public static int[][] swapItem(int[][] numsArray, int x1, int y1, int x2, int y2) {
		int temp = numsArray[y1][x1];
		numsArray[y1][x1] = numsArray[y2][x2];
		numsArray[y2][x2] = temp;
		return numsArray;
	}

	public static int[][] randomPos(int w, int h){
		int[][] posSwap = new int[2][2];
		w--;
		h--;

		int x1 = (int)(Math.random() * w+1);
	    int y1 = (int)(Math.random() * h+1);
	    posSwap[0][0] = x1;
	    posSwap[0][1] = y1;

	    int x2 = (int)(Math.random() * w+1);
	    int y2 = (int)(Math.random() * h+1);
	    posSwap[1][0] = x2;
	    posSwap[1][1] = y2;

	    return posSwap;
	}

	public static boolean isValid(int[][] array) {
		int prevVal = array[0][0];
		for(int[] row : array) {
			for(int item : row) {
				if(item < prevVal) return false;
				prevVal = item;
			}
		}
		return true;
	}

	public static int[][] bubble(int[][] array, BufferedImage image) {
		int c = 0;
		while(!isValid(array)) {
			IOfuncs.output(image, "PleaseDanny_"+c);
			c++;
			int[] prevValue = {array[0][0], 0, 0}; // prevValue[] = {value, x, y}
			for(int i = 0; i < array.length; i++) {
				for(int n = 0; n < array[0].length; n++) {
					if(prevValue[0] > array[i][n]) {
						array[prevValue[2]][prevValue[1]] = array[i][n];
						array[i][n] = prevValue[0];
						swapPixels(image, array, prevValue[1], prevValue[2], n, i);
					}
					prevValue[0] = array[i][n];
					prevValue[1] = n;
					prevValue[2] = i;
				}
			}
		}
		return array;
	}
}
