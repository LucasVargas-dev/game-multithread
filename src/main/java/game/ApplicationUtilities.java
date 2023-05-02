package game;

import java.awt.Point;
import java.util.Random;

public class ApplicationUtilities {
    private int border = 2;
    private int width = 600;
    private int height = 600;
    private int titleHeight = 30;
    
    public int getRandomInt(int limit) {
        Random rand = new Random();
        return rand.nextInt(600) + 1;
    }
    
    public Point getRandomPoint(int limitX, int limitY) {
        return new Point(getRandomInt(limitX), getRandomInt(limitY));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBorder() {
        return border;
    }

    public int getTitleHeight() {
        return titleHeight;
    }
}
