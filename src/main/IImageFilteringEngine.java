package main;


import java.awt.image.*;

import filters.IFilter;

/**
 * @author Francois Taiani   <francois.taiani@irisa.fr>
 */
public interface IImageFilteringEngine {

  public void loadImage(String inputImage) throws Exception ;
  public void writeOutPngImage(String outFile) throws Exception ;
  public void setImg(BufferedImage newImg) ;
  public BufferedImage getImg() ;
  public void applyFilter(IFilter someFilter) ;
  
} // EndInterface IImageFilteringEngine
