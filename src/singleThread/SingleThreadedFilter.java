package singleThread;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import filters.IFilter;
import main.IImageFilteringEngine;

public class SingleThreadedFilter extends Thread implements IImageFilteringEngine {

	private BufferedImage imageOut;

	@Override
	public void loadImage(String inputImage) throws Exception {
		// on lit l'image dont le chemin est donnée en entrée
		imageOut = ImageIO.read(new File(inputImage));
	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
		// on crée un fichier vide avec pour chemin outFile
		File f = new File(outFile);
		// on y place l'image
		ImageIO.write(imageOut, "png", f);
	}

	@Override
	public void setImg(BufferedImage newImg) {
		this.imageOut = newImg;
	}

	@Override
	public BufferedImage getImg() {
		return this.imageOut;
	}

	@Override
	public void applyFilter(IFilter someFilter) {

		// on crée une nouvelle image
		BufferedImage outImg = new BufferedImage(imageOut.getWidth() - 2 * someFilter.getMargin(),
				imageOut.getHeight() - 2 * someFilter.getMargin(), BufferedImage.TYPE_INT_RGB);
		// on calcule la valeur de chaque pixel en applicant le filtre
		for (int x = 0 + someFilter.getMargin(); x < imageOut.getWidth() - someFilter.getMargin(); x++) {
			for (int y = 0 + someFilter.getMargin(); y < imageOut.getHeight() - someFilter.getMargin(); y++) {
				someFilter.applyFilterAtPoint(x, y, imageOut, outImg);
			}
		}
		
		imageOut = outImg;
	}

}
