package com.java.pdf.itext7.guide.chapter1;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.java.pdf.utils.CommonUtils;

import java.io.File;
import java.io.IOException;

/**
 * Simple List example.
 *
 * https://github.com/iTextCN/itext7-in-mandarin/blob/master/guide/examples/chapter1/C01E03_QuickBrownFox.java
 */
public class C01E02_RickAstley {

    public static final String DEST = "guide/chapter01/rick_astley.pdf";

    public static void main(String args[]) throws IOException {
        String absolutePath = CommonUtils.getAbsolutePath(DEST);
        File file = new File(absolutePath);
        file.getParentFile().mkdirs();
        new C01E02_RickAstley().createPdf(absolutePath);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
        // Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        // Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
        // Add the list
        document.add(list);

        //Close document
        document.close();
    }
}