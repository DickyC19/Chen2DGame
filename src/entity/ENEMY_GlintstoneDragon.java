package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_GlintstoneDragon extends Enemy{

    public ENEMY_GlintstoneDragon(GamePanel gp) {
        super(gp);
        name = "GlintstoneDragon";
        maxLife = 125 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 175 * gp.difficulty;
        setImage("GlintstoneDragon");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 4;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Bite", 35, 6, 1, 0);
        moves[1] = new Move("Tail Whip", 45, 6, 1, 0);
        moves[2] = new Move("Glintstone Cometshard", 65, 6, 2, 0);
    }




}
