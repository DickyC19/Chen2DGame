package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    String[] maps;
    public Tile[] tile;
    public int[][] mapTileNum;
    int count;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        maps = new String[]{"maps/map00.txt", "maps/map01.txt", "maps/map02.txt", "maps/map03.txt", "maps/map04.txt", "maps/map05.txt", "maps/map06.txt", "maps/map07.txt", "maps/map08.txt", "maps/map09.txt"};
        tile = new Tile[100];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenCol];
        count = 0;
        getTileImage();
        loadMap();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int newCount) {
        count = newCount;
    }

    public void getTileImage() {
            setUp(0, "000", false);
            setUp(1, "001", false);
            setUp(2, "002", false);
            setUp(3, "003", true);
            setUp(4, "004", false);
            setUp(5, "005", false);
            setUp(6, "006", false);
            setUp(7, "007", false);
            setUp(8, "008", false);
            setUp(9, "009", false);
            setUp(10, "010", false);
            setUp(11, "011", false);
            setUp(12, "012", false);
            setUp(17, "017", false);
            setUp(38, "038", true);
            setUp(39, "039", true);
            setUp(40, "040", true);
            setUp(41, "041", true);
            setUp(42, "042", true);
            setUp(43, "043", true);
            setUp(44, "044", true);
            setUp(45, "045", true);
            setUp(46, "046", true);
            setUp(47, "047", true);
            setUp(48, "048", true);
            setUp(49, "049", true);
            setUp(50, "050", true);
            setUp(51, "051", true);
            setUp(52, "052", true);
            setUp(53, "053", true);
            setUp(54, "054", true);
            setUp(55, "055", false);
    }

    public void setUp(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            gp.monsterDead = false;
            InputStream is = getClass().getClassLoader().getResourceAsStream(maps[count]);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line .split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }
            count ++;
            br.close();
        } catch (Exception e) {}
    }


    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col ++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
