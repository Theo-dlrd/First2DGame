package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject{

    public Obj_Door(){
        super("Door");
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/door.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        setCollision(true);
    }

}
