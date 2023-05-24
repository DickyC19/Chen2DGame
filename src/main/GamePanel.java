package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;


public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int battleSize = 80; // when in turn-based section
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 17;
    final int maxScreenRow = 13;
    final int screenWidth = tileSize * maxScreenCol; // 816 pixels
    final int screenHeight = tileSize * maxScreenRow; // 624 pixels
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }


    // https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
}
