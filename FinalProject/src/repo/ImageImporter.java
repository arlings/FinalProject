package repo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageImporter {

    public BufferedImage loadImage() throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(new File("src/images/ChessKingArtIcon.png"));
        return image;
    }
    
    public void importUserData(){
        //not suported currently
    }
}
