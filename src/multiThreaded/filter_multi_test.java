package multiThreaded;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import filters.GaussianContourExtractorFilter;
import filters.GrayLevelFilter;
import singleThread.SingleThreadedFilter;

public class filter_multi_test {

	int K = 4;


	@Test
	void test_cercles_gaussian() throws Exception {
		// on crée le thread
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		
		// on charge l'image
		thread.loadImage("TEST_IMAGES/FourCircles.png");

		// on applique les filtres
		thread.applyFilter(new GrayLevelFilter());
		thread.applyFilter(new GaussianContourExtractorFilter());

		// on récupère l'image en sortie
		BufferedImage image_result = thread.getImg();
		thread.writeOutPngImage("TEST_IMAGES/test_output.png");

		BufferedImage image_expected = ImageIO.read(new File("TEST_IMAGES/FourCircles_gaussian_contour.png"));

		// On vérifie que les images sont de la même taille
		assertTrue(image_expected.getWidth() == image_result.getWidth());
		assertTrue(image_expected.getHeight() == image_result.getHeight());

		// On vérifie que les images sont de la même taille
		assertEquals(image_expected.getWidth(), image_result.getWidth());
		assertEquals(image_expected.getHeight(), image_result.getHeight());

		// Puis qu'elles sont identiques
		for (int x = 0; x < image_result.getWidth(); x++) {
			for (int y = 0; y < image_result.getHeight(); y++) {
				assertEquals(Integer.toHexString(image_result.getRGB(x, y)),
						Integer.toHexString(image_expected.getRGB(x, y)));
			}
		}
	}

	@Test
	void test_cercles_gray() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		thread.loadImage("TEST_IMAGES/FourCircles.png");
		thread.applyFilter(new GrayLevelFilter());
		BufferedImage image_result = thread.getImg();

		BufferedImage image_expected = ImageIO.read(new File("TEST_IMAGES/FourCircles_gray.png"));

		// On vérifie que les images sont de la même taille
		assertEquals(image_expected.getWidth(), image_result.getWidth());
		assertEquals(image_expected.getHeight(), image_result.getHeight());

		// Puis qu'elles sont identiques
		for (int x = 0; x < image_result.getWidth(); x++) {
			for (int y = 0; y < image_result.getHeight(); y++) {
				assertEquals(Integer.toHexString(image_result.getRGB(x, y)),
						Integer.toHexString(image_expected.getRGB(x, y)));
			}
		}
	}

	@Test
	void test_mount_gaussian() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		thread.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
		thread.applyFilter(new GrayLevelFilter());
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_output_mount.png");
		BufferedImage image_result = thread.getImg();

		BufferedImage image_expected = ImageIO
				.read(new File("TEST_IMAGES/15226222451_5fd668d81a_c_gaussian_contour.png"));

		// On vérifie que les images sont de la même taille
		assertEquals(image_expected.getWidth(), image_result.getWidth());
		assertEquals(image_expected.getHeight(), image_result.getHeight());

		// Puis qu'elles sont identiques
		for (int x = 0; x < image_result.getWidth(); x++) {
			for (int y = 0; y < image_result.getHeight(); y++) {
				assertEquals(Integer.toHexString(image_result.getRGB(x, y)),
						Integer.toHexString(image_expected.getRGB(x, y)));
			}
		}
	}

	@Test
	void test_mount_gray() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		thread.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
		thread.applyFilter(new GrayLevelFilter());
		BufferedImage image_result = thread.getImg();

		BufferedImage image_expected = ImageIO.read(new File("TEST_IMAGES/15226222451_5fd668d81a_c_gray.png"));

		// On vérifie que les images sont de la même taille
		assertEquals(image_expected.getWidth(), image_result.getWidth());
		assertEquals(image_expected.getHeight(), image_result.getHeight());

		// Puis qu'elles sont identiques
		for (int x = 0; x < image_result.getWidth(); x++) {
			for (int y = 0; y < image_result.getHeight(); y++) {
				assertEquals(Integer.toHexString(image_result.getRGB(x, y)),
						Integer.toHexString(image_expected.getRGB(x, y)));
			}
		}
	}

	@Test
	void test_white_square() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		// creating new image
		BufferedImage white_square = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Color white = new Color(255, 255, 255);
		for (int x = 0; x < white_square.getWidth(); x++) {
			for (int y = 0; y < white_square.getHeight(); y++) {
				// computing new color from extracted components
				white_square.setRGB(x, y, white.getRGB());
			}
		}

		thread.setImg(white_square);
		thread.writeOutPngImage("TEST_IMAGES/test_white_square.png");
		thread.applyFilter(new GrayLevelFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_white_grey.png");
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_white_gaussian.png");

	}

	@Test
	void test_black_square() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		// creating new image
		BufferedImage black_square = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Color black = new Color(0, 0, 0);
		for (int x = 0; x < black_square.getWidth(); x++) {
			for (int y = 0; y < black_square.getHeight(); y++) {
				// computing new color from extracted components
				black_square.setRGB(x, y, black.getRGB());
			}
		}

		thread.setImg(black_square);
		thread.writeOutPngImage("TEST_IMAGES/test_black_square.png");
		thread.applyFilter(new GrayLevelFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_black_grey.png");
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_black_gaussian.png");

	}

	@Test
	void test_red_square() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		// creating new image
		BufferedImage square = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Color red = new Color(255, 0, 0);
		for (int x = 0; x < square.getWidth(); x++) {
			for (int y = 0; y < square.getHeight(); y++) {
				// computing new color from extracted components
				square.setRGB(x, y, red.getRGB());
			}
		}

		thread.setImg(square);
		thread.writeOutPngImage("TEST_IMAGES/test_red_square.png");
		thread.applyFilter(new GrayLevelFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_red_grey.png");
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_red_gaussian.png");

	}

	@Test
	void test_green_square() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		// creating new image
		BufferedImage square = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Color green = new Color(0, 255, 0);
		for (int x = 0; x < square.getWidth(); x++) {
			for (int y = 0; y < square.getHeight(); y++) {
				// computing new color from extracted components
				square.setRGB(x, y, green.getRGB());
			}
		}

		thread.setImg(square);
		thread.writeOutPngImage("TEST_IMAGES/test_green_square.png");
		thread.applyFilter(new GrayLevelFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_green_grey.png");
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_green_gaussian.png");

	}

	@Test
	void test_blue_square() throws Exception {
		MultiThreadedImageFilteringEngine thread = new MultiThreadedImageFilteringEngine(K);
		// creating new image
		BufferedImage square = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Color blue = new Color(0, 0, 255);
		for (int x = 0; x < square.getWidth(); x++) {
			for (int y = 0; y < square.getHeight(); y++) {
				// computing new color from extracted components
				square.setRGB(x, y, blue.getRGB());
			}
		}

		thread.setImg(square);
		thread.writeOutPngImage("TEST_IMAGES/test_blue_square.png");
		thread.applyFilter(new GrayLevelFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_blue_grey.png");
		thread.applyFilter(new GaussianContourExtractorFilter());
		thread.writeOutPngImage("TEST_IMAGES/test_blue_gaussian.png");

	}
}