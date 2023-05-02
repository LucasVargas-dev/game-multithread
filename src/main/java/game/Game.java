package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame implements Runnable {
    private boolean running;
    private Player player;
    private EnemySlow enemySlow;
    private EnemyFast enemyFast;
    private YellowBall yellowBall;
    private ApplicationUtilities util = new ApplicationUtilities();
    
    private int currentLevel = 1;

    public Game() {
        initComponents();
        
        setTitle("Jogo de Bolinhas - Level " + currentLevel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(util.getWidth(), util.getHeight());
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel gamePanel = new JPanel();
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setContentPane(gamePanel);

        player = Player.getInstance();
        enemySlow = new EnemySlow();
        enemyFast = new EnemyFast();
        yellowBall = new YellowBall();

        setVisible(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        new Thread(this).start();
    }
    
    public void stop(boolean isWinner) {
        running = false;
        
        String message = isWinner ? "Parabéns, você venceu!" : "Que pena, você perdeu!";
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillOval(player.getX(), player.getY(), 10, 10);
        
        g.setColor(Color.RED);
        g.fillOval(enemySlow.getX(), enemySlow.getY(), 10, 10);
        
        g.setColor(Color.RED);
        g.fillOval(enemyFast.getX(), enemyFast.getY(), 10, 10);
        
        g.setColor(Color.YELLOW);
        g.fillOval(yellowBall.getX(), yellowBall.getY(), 10, 10);
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                
        if (evt.getKeyCode() == evt.VK_UP) {
            player.moveUp();
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            player.moveDown();
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            player.moveLeft();
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            player.moveRight();
        }
        
        verificarColisao();
    }                               

    @Override
    public void run() {
        running = true;
        new Thread(enemySlow).start();
        new Thread(enemyFast).start();
        new Thread(yellowBall).start();
        
        while (running) {
            try {
                Thread.sleep(100);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void verificarColisao() {
        // verifica se houve colisão com o inimigo slow
        if (Math.abs(player.getX() - enemySlow.getX()) < 10 && Math.abs(player.getY() - enemySlow.getY()) < 10) {
            stop(false);
        }

        // verifica se houve colisão com o inimigo fast
        if (Math.abs(player.getX() - enemyFast.getX()) < 10 && Math.abs(player.getY() - enemyFast.getY()) < 10) {
            stop(false);
        }
        
        // verifica se houve colisão com a bola amarela
        if (Math.abs(player.getX() - yellowBall.getX()) < 10 && Math.abs(player.getY() - yellowBall.getY()) < 10) {
            goToNextLevel();
        }
    }
    
    private void updateTitle() {
        setTitle("Jogo de Bolinhas - Level " + currentLevel);
    }
    
    private void resetParticipantPosition(GameParticipant participant, Point positions) {
        participant.setX((int) positions.getX());
        participant.setY((int) positions.getY());
    }
    
    private void goToNextLevel() {
        currentLevel += 1;
        updateTitle();

        if (currentLevel == 15) {
            stop(true);
        } 

        resetParticipantPosition(player, util.getRandomPoint(WIDTH, HEIGHT));
        resetParticipantPosition(yellowBall, util.getRandomPoint(WIDTH, HEIGHT));
        resetParticipantPosition(enemyFast, util.getRandomPoint(WIDTH, HEIGHT));
        resetParticipantPosition(enemySlow, util.getRandomPoint(WIDTH, HEIGHT));

        enemyFast.setMoveSpeedInMs(enemyFast.getMoveSpeedInMs() - 5);
        enemySlow.setMoveSpeedInMs(enemySlow.getMoveSpeedInMs() - 5);
    }
}
