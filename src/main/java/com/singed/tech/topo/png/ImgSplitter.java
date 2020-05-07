package com.singed.tech.topo.png;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgSplitter {
    public File[] extract(File srcImage) throws IOException {
        File[] images = new File[2];
        images[0] = generateImageFile(srcImage, "top");
        images[1] = generateImageFile(srcImage, "bottom");
        BufferedImage img = ImageIO.read(srcImage);
        //880x660
        BufferedImage subImage = img.getSubimage(880, 660, 5100, 3500);
        ImageIO.write(subImage, "png", images[0]);
        subImage = img.getSubimage(880, 4160,5100,3500);
        ImageIO.write(subImage, "png", images[1]);
        return images;
    }

    private File generateImageFile(File srcImage, String part) throws IOException {
        File imageFile = new File("./parts/", srcImage.getName().replace(".png", part + "_.png"));
        imageFile.mkdirs();
        if(!imageFile.exists()) {
            imageFile.createNewFile();
        }
        return imageFile;
    }

}
