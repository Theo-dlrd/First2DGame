package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    private int x, y;
    private BufferedImage image;
    private final String name;
    private boolean collision;
    private final Rectangle solidArea;
    private final int solidAreaDefaultX, solidAreaDefaultY;
    protected UtilityTool UT;
    protected GamePanel gp;

    protected SuperObject(String name, GamePanel gp){
        this.solidArea = new Rectangle(0,0,48,48);
        this.name = name;
        this.collision = false;
        this.solidAreaDefaultX = 0;
        this.solidAreaDefaultY = 0;
        this.UT = new UtilityTool();
        this.gp = gp;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public Rectangle getSolidArea(){
        return solidArea;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setImage(BufferedImage img){
        this.image = img;
    }

    public BufferedImage getImage(){
        return image;
    }

    public String getName(){
        return name;
    }

    public void setCollision(boolean bool){
        this.collision = bool;
    }

    public boolean isCollideable(){
        return collision;
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = (int) (x - gp.getPlayer().getX()+gp.getPlayer().getScreenX());
        int screenY = (int) (y - gp.getPlayer().getY()+gp.getPlayer().getScreenY());

        if(x + gp.getTileSize() > gp.getPlayer().getX() - gp.getPlayer().getScreenX()
                && x - gp.getTileSize() < gp.getPlayer().getX() + gp.getPlayer().getScreenX()
                && y + gp.getTileSize() > gp.getPlayer().getY() - gp.getPlayer().getScreenY()
                && y - gp.getTileSize() < gp.getPlayer().getY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

}
