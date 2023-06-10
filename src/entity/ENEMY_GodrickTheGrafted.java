package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_GodrickTheGrafted extends Enemy{

    public ENEMY_GodrickTheGrafted(GamePanel gp) {
        super(gp);
        name = "GodrickTheGrafted";
        maxLife = 100 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 150 * gp.difficulty;
        setImage("GodrickTheGrafted");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Storm Blade", 30, 6, 1, 0);
        moves[1] = new Move("Storm Assault", 40, 6, 1, 0);
        moves[2] = new Move("Bear Witness", 60, 6, 2, 0);
    }




}
