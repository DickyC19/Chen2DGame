package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_TreeSentinel extends Enemy{

    public ENEMY_TreeSentinel(GamePanel gp) {
        super(gp);
        name = "TreeSentinel";
        maxLife = 75 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];

        setImage("TreeSentinel");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 3, image.getHeight() * 3);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setMoves() {
        moves[0] = new Move("Rearing Slam", 25, 6, 1, 0);
        moves[1] = new Move("Shield Crush", 35, 6, 1, 0);
        moves[2] = new Move("Golden Retaliation", 55, 6, 2, 0);
    }




}
