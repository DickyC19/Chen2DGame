package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    String previousDirection;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "right";
        previousDirection = "right";
    }

    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Right2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if (keyH.upPressed) {
                if (direction.equals("right")) {
                    previousDirection = "right";
                } else if (direction.equals("left")) {
                    previousDirection = "left";
                }
                direction = "up";
                worldY -= speed;
            }
            if (keyH.downPressed) {
                if (direction.equals("right")) {
                   previousDirection = "right";
                } else if (direction.equals("left")) {
                    previousDirection = "left";
                }
                direction = "down";
                worldY += speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed ;
            }
            if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up", "down" -> {
                if (previousDirection.equals("right")) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        g2.drawImage(image, worldX, worldY, image.getWidth() * 3, image.getHeight() * 3, null);
    }
}
