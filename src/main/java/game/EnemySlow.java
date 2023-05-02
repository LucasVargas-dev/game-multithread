package game;

import java.awt.Point;

public class EnemySlow extends GameParticipant implements Runnable, IEnemy {

    public EnemySlow() {
        super(new Point(540, 55), 200);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.getMoveSpeedInMs());
                updatePosition();
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
            moveLeft();
        } else {
            moveRight();
        }

        // Verifica se o jogador está acima ou abaixo do inimigo
        if (playerPositions.getY() < enemyY) {
            moveUp();
        } else {
            moveDown();
        }
    }
}
