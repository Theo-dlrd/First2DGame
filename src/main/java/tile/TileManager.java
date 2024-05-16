package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private final GamePanel gp;
    private final Tile[] tiles;
    private final int[][] mapTileNum;


    public TileManager(GamePanel gp){
        this.gp = gp;
        this.tiles = new Tile[10];
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("world01.txt");
    }

    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/grass01.png")));

            tiles[1] = new Tile();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png")));
            tiles[1].setCollision(true);

            tiles[2] = new Tile();
            tiles[2].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/water01.png")));
            tiles[2].setCollision(true);

            tiles[3] = new Tile();
            tiles[3].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png")));
            tiles[4].setCollision(true);

            tiles[5] = new Tile();
            tiles[5].setImage(ImageIO.read(getClass().getResourceAsStream("/Tiles/road00.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName){
        try{
            InputStream is = getClass().getResourceAsStream("/Maps/"+mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.getMaxWorldCol() && row<gp.getMaxWorldRow()){
                String line = br.readLine();
                while(col<gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col==gp.getMaxWorldCol()){
                    col=0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;

        while(worldCol<gp.getMaxWorldCol() && worldRow<gp.getMaxWorldRow()){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = (int) (worldX - gp.getPlayer().getX()+gp.getPlayer().getScreenX());
            int screenY = (int) (worldY - gp.getPlayer().getY()+gp.getPlayer().getScreenY());

            if(worldX + gp.getTileSize() > gp.getPlayer().getX() - gp.getPlayer().getScreenX()
                    && worldX - gp.getTileSize() < gp.getPlayer().getX() + gp.getPlayer().getScreenX()
                    && worldY + gp.getTileSize() > gp.getPlayer().getY() - gp.getPlayer().getScreenY()
                    && worldY - gp.getTileSize() < gp.getPlayer().getY() + gp.getPlayer().getScreenY()) {
                g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }

            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public int getMapTileNumIn(int x, int y){
        return mapTileNum[x][y];
    }

    public Tile getTileImageByIndex(int i){
        return tiles[i];
    }
}
