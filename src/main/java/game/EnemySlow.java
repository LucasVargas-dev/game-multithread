package game;

import java.awt.Point;

public class EnemySlow extends GameParticipant implements Runnable, IEnemy {

    public EnemySlow() {
        super(new Point(55, 55), 4000);
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
        System.out.println("updateSlow");
        Point playerPositions = Player.getInstance().getPositions();
        StringBuilder builder = new StringBuilder();
        builder.append("Olá, sou um inimigo na posição ")
                .append(getX()).append(",").append(getY())
                .append(" e preciso encontrar o jogador que está na posição ")
                .append(playerPositions.getX()).append(",").append(playerPositions.getY())
                .append(". Para qual lado devo ir: LEFT, RIGHT, UP, DOWN? Escolha apenas um e responda no formato informado");
        
        String response = new GptRequest().enviar(builder.toString());
    }
}
