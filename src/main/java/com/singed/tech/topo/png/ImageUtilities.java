package com.singed.tech.topo.png;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtilities {
    public File[] splitImage(File srcImage) throws IOException {
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

    public File rotateImage90DegreesToLeft(File srcImage) throws Exception {
        File imageFile = generateImageFile(srcImage, "90l");
        BufferedImage image = ImageIO.read(srcImage);
        final double rads = Math.toRadians(270);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        ImageIO.write(rotatedImage, "png", imageFile);
        return imageFile;
    }
}
