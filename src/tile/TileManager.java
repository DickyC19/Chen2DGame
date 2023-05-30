package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    String[] maps;
    Tile[] tile;
    int[][] mapTileNum;
    int count;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        maps = new String[]{"maps/map01.txt", "maps/map02.txt", "maps/map03.txt", "maps/map04.txt", "maps/map05.txt", "maps/map06.txt", "maps/map07.txt", "maps/map08.txt", "maps/map09.txt"};
        tile = new Tile[20];
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
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/000.png")); //insert file name after "/"
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/001.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/002.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/003.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/004.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/005.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/006.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/007.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/008.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/009.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/010.png"));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/017.png"));
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/017.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {

            System.out.println(maps[count]);
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

    // https://drive.google.com/drive/folders/1OBRM8M3qCNAfJDCaldg62yFMiyFaKgYx
}
