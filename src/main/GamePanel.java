package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int battleSize = 108; // when in turn-based section
    final int scale = 4;

    public int difficulty;
    public final int tileSize = originalTileSize * scale;
    public final int battleTileSize = battleSize * scale;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol; //  pixels
    public final int screenHeight = tileSize * maxScreenRow; //  pixels

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        difficulty = 2;
    }

    public int getScale() {
        return scale;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // .01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (player.x == screenWidth) {
            tileM.loadMap();
            player.x = 0;
        } else if (player.x < 0) {
            tileM.setCount(tileM.getCount() - 2);
            tileM.loadMap();
            player.x = tileSize * (maxScreenCol - 1);
        } else if (player.y == screenHeight) {
            difficulty = 1;
            tileM.loadMap();
            player.y = 0;
        } else if (player.y < 0) {
            difficulty = 3;
            tileM.loadMap();
            player.y = screenHeight;
        }
        tileM.draw(g2);

        player.draw(g2);
        g2.dispose();
    }

}
