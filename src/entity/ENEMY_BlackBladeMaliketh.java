package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_BlackBladeMaliketh extends Enemy{

    public ENEMY_BlackBladeMaliketh(GamePanel gp) {
        super(gp);
        name = "BlackBladeMaliketh";
        maxLife = 250 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 300 * gp.difficulty;
        setImage("BlackBladeMaliketh");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Piledriver", 60, 6, 1, 0);
        moves[1] = new Move("Black Blade", 70, 6, 1, 0);
        moves[2] = new Move("Destined Death", 90, 6, 2, 0);
    }




}
