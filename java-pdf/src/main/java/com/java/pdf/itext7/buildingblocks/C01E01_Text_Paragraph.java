package com.java.pdf.itext7.buildingblocks;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.java.pdf.utils.CommonUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName C01E01_Text_Paragraph
 * @date 2025/7/25 15:14
 **/
public class C01E01_Text_Paragraph {

    public static final String DEST = "buildingblocks/chapter01/text_paragraph.pdf";

    public static void main(String args[]) throws IOException {
        String absolutePath = CommonUtils.getAbsolutePath(DEST);
        File file = new File(absolutePath);
        file.getParentFile().mkdirs();
        new C01E01_Text_Paragraph().createPdf(absolutePath);
    }

    public void createPdf(String dest) throws IOException {
        // Initialize PDF document
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        // Initialize document
        Document document = new Document(pdf);

        // Add content
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
        Text author = new Text("Robert Louis Stevenson").setFont(font);
        Paragraph p = new Paragraph().add(title).add(" by ").add(author);
        document.add(p);

        //Close document
        document.close();
    }
}