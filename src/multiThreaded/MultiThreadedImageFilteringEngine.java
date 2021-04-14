package multiThreaded;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import javax.imageio.ImageIO;

import filters.IFilter;
import main.IImageFilteringEngine;

public class MultiThreadedImageFilteringEngine extends Thread implements IImageFilteringEngine {

	// Class Worker
	class Worker extends Thread {

		private CyclicBarrier barrier;
		private int name;
		private Semaphore semaphore;

		Worker(CyclicBarrier barrier, Semaphore semaphore, int name) {
			this.barrier = barrier;
			this.semaphore = semaphore;
			this.name = name;
		}

		public void run() {
			while (true) {

				try {
					semaphore.acquire();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				// on calcule la valeur de chaque pixel en applicant le filtre
				for (int x = name + someFilter.getMargin(); x < img.getWidth()
						- someFilter.getMargin(); x += nbWorkers) {
					for (int y = 0 + someFilter.getMargin(); y < img.getHeight() - someFilter.getMargin(); y++) {
						someFilter.applyFilterAtPoint(x, y, img, tmpImg);
					}
				}


				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}

	}
	// Fin de la classe Worker

	private BufferedImage img;
	private BufferedImage tmpImg;

	private IFilter someFilter;
	private CyclicBarrier barrier;
	private int nbWorkers;
	private List<Worker> workers;

	private Semaphore semaphore;

	public MultiThreadedImageFilteringEngine(int nbWorkers) throws Exception {
		this.nbWorkers = nbWorkers;
		barrier = new CyclicBarrier(nbWorkers+1);
		workers = new ArrayList<Worker>();
		semaphore = new Semaphore(0);

		// Create the worker
		for (int i = 0; i < nbWorkers; i++) {
			workers.add(new Worker(barrier, semaphore, i));
		}

		// Initialize all the workers
		for (int i = 0; i < nbWorkers; i++) {
			// send in barrier
			workers.get(i).start();
		}
	}

	@Override
	public void loadImage(String inputImage) throws Exception {
		img = ImageIO.read(new File(inputImage));
	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
		File f = new File(outFile);
		ImageIO.write(img, "png", f);
	}

	@Override
	public void setImg(BufferedImage newImg) {
		this.img = newImg;
	}

	@Override
	public BufferedImage getImg() {
		return this.img;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		this.someFilter = someFilter;

		// creating new image
		tmpImg = new BufferedImage(img.getWidth() - 2 * someFilter.getMargin(),
				img.getHeight() - 2 * someFilter.getMargin(), BufferedImage.TYPE_INT_RGB);

		semaphore.release(nbWorkers);

		/////////////////////////////////////////////////////////////// F1
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		img = tmpImg;
	}
}
