package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_GraftedScion extends Enemy{

    public ENEMY_GraftedScion(GamePanel gp) {
        super(gp);
        name = "GraftedScion";
        maxLife = 100;
        life = maxLife;
        attack = 1;
        moves = new Move[3];

        setImage("GraftedScion");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 3, image.getHeight() * 3);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setMoves() {
        moves[0] = new Move("Slash", 40, 6, 1, 0);
        moves[1] = new Move("Shield Slam", 60, 6, 1, 0);
        moves[2] = new Move("Golden Tempering", 100, 6, 2, 0);
    }




}
