package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_GraftedScion extends Enemy{

    public ENEMY_GraftedScion(GamePanel gp) {
        super(gp);
        name = "GraftedScion";
        maxLife = 50 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 100 * gp.difficulty;
        setImage("GraftedScion");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 3;
        y =  - gp.tileSize / 3;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Slash", 20, 6, 1, 0);
        moves[1] = new Move("Shield Slam", 30, 6, 1, 0);
        moves[2] = new Move("Golden Tempering", 50, 6, 2, 0);
    }




}
