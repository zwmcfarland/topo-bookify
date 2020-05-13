package com.singed.tech.topo.pdf;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfToUtilities {
    public File convertToImage(File pdf) throws IOException {
        File image = generateImageFile(pdf);
        try(PDDocument document = PDDocument.load(pdf)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                ImageIO.write(bim, "png", image);
            }
        }
        return image;
    }

    private File generateImageFile(File pdf) throws IOException {
        File imageFile = new File("./images/", pdf.getName().replace("pdf", "png"));
        imageFile.mkdirs();
        if(!imageFile.exists()) {
            imageFile.createNewFile();
        }
        return imageFile;
    }

    public void compileBook(List<File> mapBook) throws IOException {
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        for(File mapBookPage: mapBook) {
            mergerUtility.addSource(mapBookPage);
        }
        mergerUtility.setDestinationFileName("book.pdf");
        mergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }
}