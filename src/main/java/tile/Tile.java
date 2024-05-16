package tile;

import java.awt.image.BufferedImage;

public class Tile{
    private BufferedImage image;
    private boolean collision;

    public Tile(){
        this.image = null;
        this.collision = false;
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public void setImage(BufferedImage img){
        this.image = img;
    }

    public boolean isCollideable(){
        return collision;
    }

    public void setCollision(boolean bool){
        this.collision = bool;
    }
}
