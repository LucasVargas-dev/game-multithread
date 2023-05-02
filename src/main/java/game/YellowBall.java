package game;

import java.awt.Point;

public class YellowBall extends GameParticipant implements Runnable, IEnemy {
    
    public YellowBall() {
        super(new Point(40, 40), 400);
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                updatePosition();
                Thread.sleep(this.getMoveSpeedInMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updatePosition() {
        Point playerPositions = Player.getInstance().getPositions();
        
        int enemyX = getX();
        int enemyY = getY();
        
        if (playerPositions.getX() < enemyX) {
            moveRight();
        } else {
            moveLeft();
        }

        if (playerPositions.getY() < enemyY) {
            moveDown();
        } else {
            moveUp();
        }
    }
}
