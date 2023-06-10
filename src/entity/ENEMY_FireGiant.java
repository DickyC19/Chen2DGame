package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_FireGiant extends Enemy{

    public ENEMY_FireGiant(GamePanel gp) {
        super(gp);
        name = "FireGiant";
        maxLife = 200 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 275 * gp.difficulty;
        setImage("FireGiant");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Flame of the Fell God", 50, 6, 1, 0);
        moves[1] = new Move("Burn, O Flame", 60, 6, 1, 0);
        moves[2] = new Move("Breath of the Fell God", 80, 6, 2, 0);
    }




}
