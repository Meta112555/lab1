package Lab3;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    public PImage img, dataObj;
    public void setup(){
        img = loadImage("bird01.jpg");
        size(img.width, img.height);
        dataObj = createGraphics(img.width, img.height);
        dataObj.loadPixels();
        img.loadPixels();
        for (int i=0; i<img.pixels.length; ++i){
            dataObj.pixels[i] = ( brightness( img.pixels[i] ) > 127) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        int[] rest = new int[dataObj.pixels.length];
        analize(rest);
    }

    private int[] analize(int[] rest) {
        for (int i : rest) {
            int[] neighbours = getNeighbour(dataObj.width, dataObj.height, i);
            int count = black
        }
        return ;
    }

    public void draw(){
        loadPixels();
        for (int i=0; i<dataObj.pixels.length; ++i){
            pixels[i] = ( dataObj.pixels[i] > Integer.MIN_VALUE) ? 0xFFFF00FF : 0xFF006000;
        }
        updatePixels();
    }

    public static void main(String[] args) {
        PApplet.main("Lab3.Main");
    }
}
