package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        int contornoX = 50;
        int contornoY = 50;
        int contornoWidth = getWidth() - 100;
        int contornoHeight = getHeight() - 100;

        gr.clearRect(0, 0, getWidth(), getHeight());

        // Desenha o quadrado maior (contorno)
        gr.setColor(Color.BLACK); // define a cor do contorno do quadrado como preto
        gr.drawRect(contornoX, contornoY, contornoWidth, contornoHeight); // desenha o quadrado na tela

        // Desenha o quadrado menor (movido pelo teclado)
        gr.setColor(new Color(r, g, b));
        gr.fillRect(contornoX + x, contornoY + y, 50, 50);

        for (int i = 0; i < 3; i++) {
            gr.setColor(Color.RED);
            gr.drawOval(vetX[i], vetY[i], 10, 10);
        }

        if (Math.random() > 0.9) {
            tendenciaX = 3;
            tendenciaY = 3;
        } else {
            tendenciaX = -2;
            tendenciaY = -2;
        }

        for (int i = 0; i < 3; i++) {
            if (verificarColisao(contornoX + x, contornoY + y, 50, 50, vetX[i], vetY[i], 10, 10)) {
                JOptionPane.showMessageDialog(null, "Colisão com a bolinha " + i + "! Fim de jogo!");
                System.exit(0);
            }
        }

        for (int i = 0; i < 3; i++) {
            int novaPosX = vetX[i] + (int) (Math.random() * 5) - 2 + tendenciaX;
            int novaPosY = vetY[i] + (int) (Math.random() * 5) - 2 + tendenciaY;

            // Verifica se a bola ultrapassa a borda esquerda ou direita do contorno
            if (novaPosX < contornoX) {
                novaPosX = contornoX;
            } else if (novaPosX > contornoX + contornoWidth) {
                novaPosX = contornoX + contornoWidth;
            }

            // Verifica se a bola ultrapassa a borda superior ou inferior do contorno
            if (novaPosY < contornoY) {
                novaPosY = contornoY;
            } else if (novaPosY > contornoY + contornoHeight) {
                novaPosY = contornoY + contornoHeight;
            }

            vetX[i] = novaPosX;
            vetY[i] = novaPosY;
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

    private boolean verificarColisao(int x1, int y1, int largura1, int altura1, int x2, int y2, int largura2, int altura2) {
        // Verifica se as caixas delimitadoras se interceptam em pelo menos um ponto
        return x1 < x2 + largura2 && x1 + largura1 > x2 && y1 < y2 + altura2 && y1 + altura1 > y2;
    }

    private Color Color(int r, Graphics g, int b) {
        throw new UnsupportedOperationException("Not supported yet.");
    }                
}


