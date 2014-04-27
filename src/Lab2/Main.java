package Lab2;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Arrays;

public class Main extends PApplet{
    public PImage img;
    public PImage newimg;

    public void setup(){
        img = loadImage("bird01.jpg");
        size(img.width, img.height);
        newimg = createImage(img.width, img.height, RGB);
        img.loadPixels();
        newimg.loadPixels();
        for (int i = 0; i < img.pixels.length; i++){
            float b = brightness(img.pixels[i]);
            img.pixels[i] = (b > 125) ? 0xFFFFFFFF : 0xFF000000; // what happens if transparency is 00 ?
            newimg.pixels[i] =  0xFF000000;
            //newimg.pixels[i] =  0xFFFFFFFF;
        }
        img.updatePixels();
        newimg.updatePixels();
    }

    public void draw(){
        image(img,0,0);
    }

    private int countWhites(PImage img, int[] neighbourArray) {
        int count = 0;
        for (int i : neighbourArray) {
            if (i>-1) count += ( img.pixels[i] > 0xFF000000 ) ? 1 : 0;
        }
        /*if (count == 0 ) {
            println();
            println(neighbourArray[0]);
            println(count);
        }*/
        return count;
    }

    private int[] getNeighbour(PImage img, int i, int depth) {
        int[] neighbours = new int[2*2*2*depth];
        neighbours[0] = ( ( i % img.width ) == 0 ) ? -2 : ( i - 1 );
        neighbours[1] = ( i < img.width ) ? -2 : i - img.width;
        neighbours[2] = ( ( ( i + 1 ) % img.width ) == 0 ) ? -2 : ( i + 1 );
        neighbours[3] = ( i > ( img.pixels.length - img.width - 1) ) ? -2 : i + img.width;
        neighbours[4] = ( neighbours[0] < 0 ) ? -1 : neighbours[1] - 1;
        neighbours[5] = ( neighbours[1] < 0 ) ? -1 : neighbours[1] + 1;
        neighbours[6] = ( neighbours[2] < 0 ) ? -1 : neighbours[3] + 1;
        neighbours[7] = ( neighbours[3] < 0 ) ? -1 : neighbours[3] - 1;

        /*
        println();
        println(i);
        println(neighbours);
        */
        return neighbours;
    }

    public static void main(String[] args) {
        PApplet.main(new String[] {"Lab2.Main"});
    }

    public void mousePressed(){

        for (int i = 0; i < img.pixels.length; i++){
            if (img.pixels[i] == 0xFF000000){
                int[] neighbourArray = getNeighbour(img, i, 1);
                int whiteCount = countWhites(img, neighbourArray);
                newimg.pixels[i] = (whiteCount > 3) ? 0xFF000000 : 0xFFFFFFFF;
                //newimg.pixels[i] = (whiteCount == 0) ? 0xFF000000 : 0xFFFFFFFF;
                //newimg.pixels[i] = (whiteCount > 3) ? 0xFFFFFFFF : 0xFF000000;
            }
        }
        newimg.updatePixels();
        img.pixels = Arrays.copyOf(newimg.pixels, newimg.pixels.length);
        img.updatePixels();
    }
}
