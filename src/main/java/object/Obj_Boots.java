package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Boots extends SuperObject{

    public Obj_Boots(GamePanel gp){
        super("Boots", gp);
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/boots.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
