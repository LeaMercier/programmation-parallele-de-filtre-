package filters;
import java.awt.image.BufferedImage;

public class GrayLevelFilter implements IFilter {

	@Override
	public int getMargin() {
		return 0;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		int rgb = imgIn.getRGB(x, y);
		// extracting red, green and blue components from rgb integer
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		int grey = (red + green + blue) / 3;
		// computing new color from extracted components
		int newRgb = (((grey << 8) | grey) << 8) | grey; // rotating RGB values
		imgOut.setRGB(x - getMargin(), y - getMargin(), newRgb);
	}

}
