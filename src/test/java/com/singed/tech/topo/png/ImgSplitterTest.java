package com.singed.tech.topo.png;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ImgSplitterTest {
    private ImgSplitter objUnderTest;

    @Before
    public void setUp() throws Exception {
        this.objUnderTest = new ImgSplitter();
    }

    @Test
    public void extract() throws Exception {
        File srcImg = new File("src/test/resources/MT_Northeast_Missoula_20170503_TM_geo.png");
        File[] imgParts = this.objUnderTest.extract(srcImg);
        Assert.assertTrue(imgParts[0].exists());
        Assert.assertTrue(imgParts[1].exists());
        imgParts[0].delete();
        imgParts[1].delete();
    }
}