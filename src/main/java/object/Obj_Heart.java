package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends SuperObject{
    public Obj_Heart(GamePanel gp){
        super("Heart",gp);
        try{
            this.setImage1(ImageIO.read(getClass().getResourceAsStream("/Objects/heart_full.png")));
            this.setImage1(UT.scaledImage(this.getImage1(),gp.getTileSize(), gp.getTileSize()));

            this.setImage2(ImageIO.read(getClass().getResourceAsStream("/Objects/heart_half.png")));
            this.setImage2(UT.scaledImage(this.getImage2(),gp.getTileSize(), gp.getTileSize()));

            this.setImage3(ImageIO.read(getClass().getResourceAsStream("/Objects/heart_blank.png")));
            this.setImage3(UT.scaledImage(this.getImage3(),gp.getTileSize(), gp.getTileSize()));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
