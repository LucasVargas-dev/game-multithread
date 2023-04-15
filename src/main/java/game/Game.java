package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private int x = 0;
    private int y = 0;
    private int r, g, b;
    private int tendenciaX, tendenciaY;

    private int vetX[] = new int[10];
    private int vetY[] = new int[10];

    public Game() {
        initComponents();
        for (int i = 0; i < 10; i++) {
            vetX[i] = getWidth() / 2;
            vetY[i] = getHeight() / 2;
        }
        new Thread(this).start();
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
    public void paint(Graphics gr) {
        gr.clearRect(0, 0, getWidth(), getHeight());
        gr.setColor(new Color(r, g, b));
        gr.drawRect(50 + x, 50 + y, 50, 50);

        for (int i = 0; i < 10; i++) {
            gr.setColor(new Color(i * 2, i * 3, i * 4));
            gr.drawOval(vetX[i], vetY[i], 5, 5);
        }

        if (Math.random() > 0.9) {

            tendenciaX = 2;
            tendenciaY = 2;
        } else {
            tendenciaX = 0;
            tendenciaY = 0;
        }
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                
        if (evt.getKeyCode() == evt.VK_UP) {
            y = y - 1;
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            y = y + 1;
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            x = x - 1;
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            x = x + 1;
        }

        if (evt.getKeyCode() == evt.VK_R) {
            r = r + 4;
        }
        if (evt.getKeyCode() == evt.VK_F) {
            r = r - 4;
        }

        if (evt.getKeyCode() == evt.VK_G) {
            g = g + 4;
        }
        if (evt.getKeyCode() == evt.VK_V) {
            g = g - 4;
        }

        if (evt.getKeyCode() == evt.VK_B) {
            b = b + 4;
        }
        if (evt.getKeyCode() == evt.VK_N) {
            b = b - 4;
        }

    }                               

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 10; i++) {
                    vetX[i] = vetX[i] + (int) (Math.random() * 5) - 2 + tendenciaX;
                    vetY[i] = vetY[i] + (int) (Math.random() * 5) - 2 + tendenciaY;
                }
                Thread.sleep(100);
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private Color Color(int r, Graphics g, int b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }                
}


