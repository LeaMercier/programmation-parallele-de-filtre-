package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import filters.GaussianContourExtractorFilter;
import filters.GrayLevelFilter;
import multiThreaded.MultiThreadedImageFilteringEngine;
import singleThread.SingleThreadedFilter;

public class Main {

	public static void main(String[] args) throws Exception {

		// La version sÃ©quentielle
		SingleThreadedFilter t = new SingleThreadedFilter();
		t.loadImage("TEST_IMAGES/15226222451_75d515f540_o.jpg");
		long start = System.currentTimeMillis();
		t.applyFilter(new GrayLevelFilter());
		long end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// Thread multithreadÃ©

		for (int i = 1; i < 11; i++) {
			MultiThreadedImageFilteringEngine threadmult = new MultiThreadedImageFilteringEngine(i);
			threadmult.loadImage("TEST_IMAGES/15226222451_75d515f540_o.jpg");

			start = System.currentTimeMillis();
			threadmult.applyFilter(new GrayLevelFilter());
			end = System.currentTimeMillis();
			System.out.println("Temps de calcul avec " + i + " threads diffÃ©rents, gris : " + (end - start) + "ms");
			threadmult.writeOutPngImage("TEST_IMAGES/test_multThread_gray.png");
			start = System.currentTimeMillis();
			threadmult.applyFilter(new GaussianContourExtractorFilter());
			end = System.currentTimeMillis();
			System.out.println("Temps de calcul avec " + i + " threads diffÃ©rents, gaussian : " + (end - start) + "ms");
			threadmult.writeOutPngImage("TEST_IMAGES/test_multThread_gaussian.png");
			System.out.println("DONE");
			threadmult.stop();
		}

		// La version sÃ©quentielle
		System.out.println("image 1 : ");
		SingleThreadedFilter t1 = new SingleThreadedFilter();
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c_gray.png");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨lle
		MultiThreadedImageFilteringEngine h = new MultiThreadedImageFilteringEngine(4);
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c_gray.png");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 2 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_c.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 3 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_m.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_m.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 4 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_n.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_n.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 5 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_t.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_t.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 6 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a_z.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a_z.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 7 : ");
		t1.loadImage("TEST_IMAGES/15226222451_5fd668d81a.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version parallÃ¨le
		h.loadImage("TEST_IMAGES/15226222451_5fd668d81a.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		// La version sÃ©quentielle
		System.out.println("image 9 : ");
		t1.loadImage("TEST_IMAGES/15226222451_a49b1a624b_h.jpg");
		start = System.currentTimeMillis();
		t1.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		t1.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation sÃ©quentielle, gaussian : " + (end - start) + "ms");

		// La version Parallèle
		h.loadImage("TEST_IMAGES/15226222451_a49b1a624b_h.jpg");
		start = System.currentTimeMillis();
		h.applyFilter(new GrayLevelFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gris : " + (end - start) + "ms");
		start = System.currentTimeMillis();
		h.applyFilter(new GaussianContourExtractorFilter());
		end = System.currentTimeMillis();
		System.out.println("Temps de calcul pour la synchronisation parallèle, gaussian : " + (end - start) + "ms");

		h.stop();
		t.stop();
		t1.stop();
		
	}
}
