package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GaussianContourExtractorFilter implements IFilter {

	int SIZE_FILTER = 11;

	@Override
	public int getMargin() {
		return SIZE_FILTER / 2;
	}

	private int sign(double d) {
		if (d == 0)
			return 0;
		else {
			if (d < 0)
				return -1;
			else
				return 1;
		}
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {

		int m = this.getMargin();

		double grad_x = 0;
		double grad_y = 0;

		// Calcul des poids
		for (int i = -m; i <= m; i++) {
			for (int j = -m; j <= m; j++) {

				Color c = new Color(imgIn.getRGB(x + i, y + j));
				double blue = c.getBlue();

				grad_x += sign(i) * blue * Math.exp((i * i + j * j) / (-4.));
				grad_y += sign(j) * blue * Math.exp((i * i + j * j) / (-4.));

			}
		}

		double max = Math.round(Math.max(0, 255.0 - Math.sqrt(grad_x * grad_x + grad_y * grad_y) / 2.));
		int cast = (int)(max);

		// computing new color from extracted components
		Color newRgb = new Color(cast, cast, cast);
		imgOut.setRGB(x - m, y - m, newRgb.getRGB());

	}

}
