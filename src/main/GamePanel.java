package main;

import entity.ENEMY_GraftedScion;
import entity.Enemy;
import entity.NPC_OldMan;
import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;

    public final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol; //  pixels
    public final int screenHeight = tileSize * maxScreenRow; //  pixels

    int FPS = 60;
    public int difficulty;

    TileManager tileM = new TileManager(this);
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public UI ui = new UI(this);

    public Player player = new Player(this, keyH);
    public NPC_OldMan oldMan = new NPC_OldMan(this);
    public Enemy[] monsters = new Enemy[9];
    public boolean monsterDead = true;

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int deathState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        difficulty = 2;
        gameState = titleState;
        setUpMonsters();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpMonsters() {
        monsters[0] = new ENEMY_GraftedScion(this);
    }

    public Enemy getCurrentMonster() {
        return monsters[tileM.getCount() - 2];
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
        if (gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == playState || gameState == dialogueState) {
            if (player.x == screenWidth) {
                tileM.loadMap();
                player.x = 0;
                ui.enemy = monsters[tileM.getCount() - 2];
                ui.setImages();
                monsters[tileM.getCount() - 2].resetHp();
            } else if (player.x < 0) {
                tileM.setCount(tileM.getCount() - 2);
                tileM.loadMap();
                player.x = tileSize * (maxScreenCol - 1);
                ui.enemy = monsters[tileM.getCount() - 2];
                ui.setImages();
                monsters[tileM.getCount() - 2].resetHp();
            } else if (player.y == screenHeight) {
                player.x = 0;
                player.y = tileSize * 6;
                difficulty = 1;
                tileM.loadMap();
                ui.enemy = monsters[tileM.getCount() - 2];
                ui.setImages();
            } else if (player.y < 0) {
                player.x = 0;
                player.y = tileSize * 6;
                difficulty = 3;
                tileM.loadMap();
                ui.enemy = monsters[tileM.getCount() - 2];
                ui.setImages();
            }

            tileM.draw(g2);

            if (tileM.getCount() == 1) {
                oldMan.drawNPC(g2);
            } else {
                oldMan.setDrawn(false);
            }

            if (tileM.getCount() > 1 && !monsterDead) {
                monsters[tileM.getCount() - 2].drawEnemy(g2);
            }

            player.draw(g2);

            ui.draw(g2);
        } else {
            ui.draw(g2);
        }
        g2.dispose();
    }


}
