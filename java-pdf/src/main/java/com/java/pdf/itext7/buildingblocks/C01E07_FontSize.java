package com.java.pdf.itext7.buildingblocks;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.File;
import java.io.IOException;

import static com.java.pdf.utils.CommonUtils.rootPath;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName C01E07_FontSize
 * @Description TODO
 * @date 2025/7/25 16:16
 **/
public class C01E07_FontSize {
    public static final String DEST = rootPath + "/src/main/resources/results/buildingblocks/chapter01/font_size.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E07_FontSize().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        // Add content
        Text title1 = new Text("The Strange Case of ").setFontSize(12);
        Text title2 = new Text("Dr. Jekyll and Mr. Hyde").setFontSize(16);
        Text author = new Text("Robert Louis Stevenson");
        Paragraph p = new Paragraph().setFontSize(8)
                .add(title1).add(title2).add(" by ").add(author);
        document.add(p);

        //Close document
        document.close();
    }
}