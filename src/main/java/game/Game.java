package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
    private boolean running;
    private Player player;
    private EnemySlow enemySlow;
    private EnemyFast enemyFast;
    

    public Game() {
        initComponents();
        
        setTitle("Jogo de Bolinhas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        player = Player.getInstance();
        enemySlow = new EnemySlow();
        enemyFast = new EnemyFast();

        setVisible(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        new Thread(this).start();
    }
    
    public void stop() {
        running = false;
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
        
        while (true) {
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
            System.out.println("Você perdeu!");
            stop();
        }

        // verifica se houve colisão com o inimigo fast
        if (Math.abs(player.getX() - enemyFast.getX()) < 10 && Math.abs(player.getY() - enemyFast.getY()) < 10) {
            System.out.println("Você perdeu!");
            stop();
        }
    }
}
