package game;

import java.awt.Point;

public abstract class GameParticipant {
    private int x, y, moveSpeedInMs;
    private ApplicationUtilities util = new ApplicationUtilities();
    private int border = util.getBorder();
    private int width = util.getWidth();
    private int height = util.getHeight();
    private int titleHeight = util.getTitleHeight();
    
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

    public void setMoveSpeedInMs(int moveSpeedInMs) {
        this.moveSpeedInMs = moveSpeedInMs;
    }
    
    public void moveUp() {
        y -= 2;
        if (y - border - titleHeight < 0) { // verifica se a posição do jogador ultrapassa a borda superior da janela
            y = border + titleHeight;
        }
    }
    
    public void moveDown() {
        y += 2;
        if (y + border + 10 > height) { // verifica se a posição do jogador ultrapassa a borda inferior da janela
            y = height - border - 10;
        }
    }
    
    public void moveLeft() {
        x -= 2;
        if (x - border < 0) { // verifica se a posição do jogador ultrapassa a borda esquerda da janela
            x = border;
        }
    }
    
    public void moveRight() {
        x += 2;
        if (x + border + 10 > width) { // verifica se a posição do jogador ultrapassa a borda direita da janela
            x = width - border - 10;
        }
    }
}
