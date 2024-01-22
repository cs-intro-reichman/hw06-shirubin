// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		// Color[][] tinypic = read("tinypic.ppm");
		// print(tinypic);

		// Creates an image which will be the result of various 
		// // image processing operations:
		Color[][] imageOut;

		// // Tests the horizontal flipping of an image:
		// imageOut = flippedHorizontally(tinypic);
		// imageOut = flippedVertically(tinypic);
		// imageOut = scaled(tinypic, 3,5);
		System.out.println(blend(new Color(100,40,100) , new Color(200,20,40), 0.25));

		Color[][] thor = read("thor.ppm");
		Color[][] xmen = read("xmen.ppm");
		// morph(xmen, thor, 3);
		display(thor);

		// System.out.println();
		// print(imageOut);
		
		// System.out.println(luminance(new Color(255,0,255)));

		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				r = in.readInt();
				g = in.readInt();
				b = in.readInt();
				image[i][j]= new Color (r, g, b);
			}
		}
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				print(image[i][j]);
			}
			System.out.println();
		}
		//// Replace this comment with your code
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int rows =image.length;
		int cols = image[1].length;
		Color[][] imageFlipped = new Color[rows][cols];
		for (int i = 0; i < imageFlipped.length; i++) {
			for (int j = 0; j < imageFlipped[i].length; j++) {
				imageFlipped[i][j]=image[i][image[j].length-1-j];
			}
		}
		//// Replace the following statement with your code
		return imageFlipped;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int rows =image.length;
		int cols = image[1].length;
		Color[][] imageFlipped = new Color[rows][cols];
		for (int i = 0; i < imageFlipped.length; i++) {
			for (int j = 0; j < imageFlipped[i].length; j++) {
				imageFlipped[i][j]=image[image.length-1-i][j];
			}
		}
		//// Replace the following statement with your code
		return imageFlipped;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		double r = pixel.getRed() * 0.299; 
		double g = pixel.getGreen() * 0.587;  
		double b = pixel.getBlue() * 0.114; 
		int rgb = (int)(r+g+b);
		Color newColor = new Color(rgb, rgb, rgb);
		return newColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int rows =image.length;
		int cols = image[1].length;
		Color[][] grayImage = new Color[rows][cols];
		for (int i = 0; i < grayImage.length; i++) {
			for (int j = 0; j < grayImage[i].length; j++) {
				grayImage[i][j]=luminance(image[i][j]);
			}
		}
		//// Replace the following statement with your code
		return grayImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] scaledImage = new Color[height][width];
		for (int i = 0; i < scaledImage.length ; i++) {
			for (int j = 0; j < scaledImage[i].length; j++) {
				scaledImage[i][j]=image[i*image.length/height][j*image[1].length/width];
			}
		}
		//// Replace the following statement with your code
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		double r1 = c1.getRed(); 
		double g1 = c1.getGreen();  
		double b1 = c1.getBlue();
		double r2 = c2.getRed(); 
		double g2 = c2.getGreen();  
		double b2 = c2.getBlue();  
		int r =(int)(alpha * r1 + (1-alpha) *r2);
		int g =(int)(alpha * g1 + (1-alpha) *g2);
		int b =(int)(alpha * b1 + (1-alpha) *b2);
		Color newColor = new Color(r, g, b);
		return newColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		int rows =image1.length;
		int cols = image1[1].length;
		Color[][] newImage = new Color[rows][cols];
		for (int i = 0; i < newImage.length; i++) {
			for (int j = 0; j < newImage[i].length; j++) {
				newImage[i][j]=blend(image1[i][j], image2[i][j], alpha);
			}
		}
		//// Replace the following statement with your code
		return newImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		// if (source.length != target.length || source[1].length != target[1].length){
			target = scaled(target, source[1].length, source.length);
		// }
		display(source);

		for (int i = 3; i >=0; i--) {
			source = blend(source, target, i/n);
			display(source);
			StdDraw.pause(500); 
		}
		//// Replace this comment with your code
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

