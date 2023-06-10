package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_Rennala extends Enemy{

    public ENEMY_Rennala(GamePanel gp) {
        super(gp);
        name = "Rennala";
        maxLife = 175 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 225 * gp.difficulty;
        setImage("Rennala");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Star Shower", 45, 6, 1, 0);
        moves[1] = new Move("Comet Azur", 55, 6, 1, 0);
        moves[2] = new Move("Full Moon", 75, 6, 2, 0);
    }




}
