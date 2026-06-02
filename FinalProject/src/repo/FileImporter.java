package repo;

import finalproject.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class FileImporter {

    public BufferedImage loadImage(String filePath) throws IOException {
        java.io.InputStream inputStream = getClass().getResourceAsStream("/" + filePath);
    
        if (inputStream == null) {
            throw new IOException("Resource not found: " + filePath);
        }

        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    public ArrayList<User> loadUsers(String filePath) throws IOException, NumberFormatException {
        try (InputStream in = FileImporter.class.getResourceAsStream(filePath)) {
            Scanner s = new Scanner(in);
            ArrayList<User> users = new ArrayList<>();

            while (s.hasNextLine()) {
                String tokens[] = s.nextLine().split(",");
                String userName = tokens[0];
                int numWins = Integer.parseInt(tokens[1]);
                User user = new User(userName, numWins); 
                users.add(user);
                //will import the other image #s later, after we have all the images
            }
            return users; 
        }
    }
}
