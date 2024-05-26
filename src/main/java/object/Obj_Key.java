package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends SuperObject{

    public Obj_Key(GamePanel gp){
        super("Key",gp);
        try{
            this.setImage1(ImageIO.read(getClass().getResourceAsStream("/Objects/key.png")));
            UT.scaledImage(this.getImage1(),gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
