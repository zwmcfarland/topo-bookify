package com.singed.tech.topo.pdf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class PdfToUtilitiesTest {
    private PdfToUtilities objUnderTest;

    @Before
    public void setup() {
        this.objUnderTest = new PdfToUtilities();
    }

    @Test
    public void convert() throws Exception {
        File pdf = new File("src/test/resources/MT_Northeast_Missoula_20170503_TM_geo.pdf");
        File img = this.objUnderTest.convertToImage(pdf);
        Assert.assertTrue(img.exists());
        img.delete();
    }
}