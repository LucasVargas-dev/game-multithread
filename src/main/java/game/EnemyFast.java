package game;

import java.awt.Point;

public class EnemyFast extends GameParticipant implements Runnable, IEnemy {
    
    public EnemyFast() {
        super(new Point(50, 50), 3000);
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
        System.out.println("upFast");
        
        Point playerPositions = Player.getInstance().getPositions();
        StringBuilder builder = new StringBuilder();
        builder.append("Olá, sou um inimigo na posição ")
//                .append(getX()).append(",").append(getY())
//                .append(" e preciso encontrar o jogador que está na posição ")
//                .append(playerPositions.getX()).append(",").append(playerPositions.getY())
                .append(". Para qual lado devo ir: LEFT, RIGHT, UP, DOWN? Escolha apenas um e responda no formato informado");
        
        String response = new GptRequest().enviar(builder.toString());
    }
}
