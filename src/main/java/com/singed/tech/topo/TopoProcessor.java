package com.singed.tech.topo;

import com.singed.tech.topo.pdf.PdfToUtilities;
import com.singed.tech.topo.png.ImageUtilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TopoProcessor {
    public static void main(String args[]) throws Exception {
        PdfToUtilities pdfToUtilities = new PdfToUtilities();
        ImageUtilities imageUtilities = new ImageUtilities();
        // Get list of PDFs from folder
        File mapsRoot = new File("maps/");
        File[] mapFiles = mapsRoot.listFiles();
        List<File> mapBook = new ArrayList<>();

        // Split each png into parts
        for(int x = 0; x < mapFiles.length; x++) {
            System.out.printf("Processing map %d of %d\n", x, mapFiles.length);
            File mainImageFiles = pdfToUtilities.convertToImage(mapFiles[x]);
            File[] pngPages = imageUtilities.splitImage(mainImageFiles);
            // Process png to page
            //  - Rotate 90 degrees to the left
            //  - Resize to free up space for page number and header
            //  - Add page number in corner
            File page1 = imageUtilities.rotateImage90DegreesToLeft(pngPages[0]);
            File page2 = imageUtilities.rotateImage90DegreesToLeft(pngPages[1]);

            File pdfPage1 = imageUtilities.createPDFFromImage(page1);
            File pdfPage2 = imageUtilities.createPDFFromImage(page2);

            // Insert maps into book
            mapBook.add(pdfPage1);
            mapBook.add(pdfPage2);

            // Create notes pages
            // Insert notes page into book
        }


        // Generate index of all pages.
        // Prepend index pages to book.
        // Compile book to PDF document.
        pdfToUtilities.compileBook(mapBook);
    }
}
