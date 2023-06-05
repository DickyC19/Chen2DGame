package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;

    public int x, y;
    public int speed;
    public BufferedImage left1, left2, right1, right2;
    public String direction;

    public BufferedImage image;

    int dialogueIndex = 0;
    String[] dialogues = new String[20];

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int maxLife;
    public int life;

    public int attack;

    public Move[] moves;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex ++;
    }

}
