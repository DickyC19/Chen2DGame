package entity;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int x, y;
    public int speed;
    public BufferedImage left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }


    //
}
