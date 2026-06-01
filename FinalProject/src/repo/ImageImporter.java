package repo;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageImporter {

    public void loadImage() {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File("src/repo/ChessKingArtIcon.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(image);//for testing
    }

}
