package game;

import java.awt.Point;

public abstract class GameParticipant {
    private int x, y, moveSpeedInMs;
    
    public GameParticipant(Point startPosition, int moveSpeedInMs) {
        this.moveSpeedInMs = moveSpeedInMs;
        this.x = (int) startPosition.getX();
        this.y = (int) startPosition.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Point getPositions() {
        return new Point(x, y);
    }
    
    public int getMoveSpeedInMs() {
        return moveSpeedInMs;
    }
    
    public void moveUp() {
        y -= 2;
    }
    
    public void moveDown() {
        y += 2;
    }
    
    public void moveLeft() {
        x -= 2;
    }
    
    public void moveRight() {
        x += 2;
    }
}
