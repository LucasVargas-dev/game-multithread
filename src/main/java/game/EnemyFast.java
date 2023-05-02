package game;

import java.awt.Point;

public class EnemyFast extends GameParticipant implements Runnable, IEnemy {
    
    public EnemyFast() {
        super(new Point(20, 540), 150);
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
