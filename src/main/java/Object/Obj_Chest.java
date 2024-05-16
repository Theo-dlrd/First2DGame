package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject{

    public Obj_Chest(){
        super("Chest");
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        setCollision(true);
    }

}
