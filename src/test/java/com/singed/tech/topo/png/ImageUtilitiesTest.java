package com.singed.tech.topo.png;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ImageUtilitiesTest {
    private ImageUtilities objUnderTest;

    @Before
    public void setUp() throws Exception {
        this.objUnderTest = new ImageUtilities();
    }

    @Test
    public void extract() throws Exception {
        File srcImg = new File("src/test/resources/MT_Northeast_Missoula_20170503_TM_geo.png");
        File[] imgParts = this.objUnderTest.splitImage(srcImg);
        Assert.assertTrue(imgParts[0].exists());
        Assert.assertTrue(imgParts[1].exists());
        imgParts[0].delete();
        imgParts[1].delete();
    }

    @Test
    public void rotateImage() throws Exception {
        File srcImg = new File("src/test/resources/MT_Northeast_Missoula_20170503_TM_geo.png");
        File[] imgParts = this.objUnderTest.splitImage(srcImg);
        File image = this.objUnderTest.rotateImage90DegreesToLeft(imgParts[0]);
        Assert.assertTrue(imgParts[0].exists());
        Assert.assertTrue(imgParts[1].exists());
        Assert.assertTrue(image.exists());
        imgParts[0].delete();
        imgParts[1].delete();
        image.delete();
    }
}