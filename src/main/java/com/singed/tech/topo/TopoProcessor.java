package com.singed.tech.topo;

import com.singed.tech.topo.pdf.PdfToImage;
import com.singed.tech.topo.png.ImageUtilities;

import java.io.File;

public class TopoProcessor {
    public static void main(String args[]) throws Exception {
        PdfToImage pdfToImage = new PdfToImage();
        ImageUtilities imageUtilities = new ImageUtilities();
        // Get list of PDFs from folder
        File mapsRoot = new File("maps/");
        File[] mapFiles = mapsRoot.listFiles();
        File[] imageFiles = new File[mapFiles.length];

        // Convert each pdf into a PNG
        for(int i = 0; i < mapFiles.length; i++) {
            imageFiles[i] = pdfToImage.convert(mapFiles[i]);
        }

        // Split each png into parts
        for(int x = 0; x < imageFiles.length; x++) {
            File[] pngPages = imageUtilities.splitImage(imageFiles[x]);
            // Process png to page
            //  - Rotate 90 degrees to the left
            //  - Resize to free up space for page number and header
            //  - Add page number in corner
            File page1 = imageUtilities.rotateImage90DegreesToLeft(pngPages[0]);
            File page2 =imageUtilities.rotateImage90DegreesToLeft(pngPages[1]);

            // Insert maps into book

            // Create notes pages
            // Insert notes page into book
        }


        // Generate index of all pages.
        // Prepend index pages to book.
        // Compile book to PDF document.
    }
}
