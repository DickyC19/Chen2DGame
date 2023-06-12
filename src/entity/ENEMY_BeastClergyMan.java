package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_BeastClergyMan extends Enemy{

    public ENEMY_BeastClergyMan(GamePanel gp) {
        super(gp);
        name = "BeastClergyMan";
        maxLife = 225 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];

        setImage("BeastClergyMan");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 3;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Bestial Sling", 55, 6, 1, 0);
        moves[1] = new Move("Stone of Gurranq", 65, 6, 1, 0);
        moves[2] = new Move("Gurranq's Beast Claw", 85, 6, 2, 0);
    }




}
