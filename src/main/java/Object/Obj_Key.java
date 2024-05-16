package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends SuperObject{

    public Obj_Key(){
        super("Key");
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/key.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
