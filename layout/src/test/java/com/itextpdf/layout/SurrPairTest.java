package com.itextpdf.layout;

import com.itextpdf.io.LogMessageConstant;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

@Category(IntegrationTest.class)
public class SurrPairTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/layout/SurrPairTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/layout/SurrPairTest/";
    public static final String fontsFolder = "./src/test/resources/com/itextpdf/layout/fonts/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void surrogatePairFrom2Chars() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "surrogatePairFrom2Chars.pdf";
        String cmpFileName = sourceFolder + "cmp_" + "surrogatePairFrom2Chars.pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));
        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(fontsFolder + "NotoEmoji-Regular.ttf", PdfEncodings.IDENTITY_H);

        //😉
        String winkinkSmile = "\uD83D\uDE09";

        Paragraph paragraph = new Paragraph(winkinkSmile);

        document.setFont(font);
        document.add(paragraph);
        document.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder));
    }

    @Test
    public void surrogatePair2Pairs() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "surrogatePair2Pairs.pdf";
        String cmpFileName = sourceFolder + "cmp_" + "surrogatePair2Pairs.pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));
        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(fontsFolder + "NotoEmoji-Regular.ttf", PdfEncodings.IDENTITY_H);

        //🇧🇾
        String belarusAbbr = "\uD83C\uDDE7\uD83C\uDDFE";

        Paragraph paragraph = new Paragraph(belarusAbbr);

        document.setFont(font);
        document.add(paragraph);
        document.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder));
    }

    @Test
    public void surrogatePairFullCharacter() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "surrogatePairFullCharacter.pdf";
        String cmpFileName = sourceFolder + "cmp_" + "surrogatePairFullCharacter.pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));
        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(fontsFolder + "NotoEmoji-Regular.ttf", PdfEncodings.IDENTITY_H);

        //🛀
        String em = new String(Character.toChars(0x0001F6C0));

        Paragraph paragraph = new Paragraph(em);

        document.setFont(font);
        document.add(paragraph);
        document.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder));
    }

    @Test
    //TODO DEVSIX-3307
    @LogMessages(messages = @LogMessage(messageTemplate = LogMessageConstant.FONT_SUBSET_ISSUE))
    public void surrogatePairCombingFullSurrs() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "surrogatePairCombingFullSurrs.pdf";
        String cmpFileName = sourceFolder + "cmp_" + "surrogatePairCombingFullSurrs.pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));
        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(fontsFolder + "NotoColorEmoji.ttf", PdfEncodings.IDENTITY_H);

        //🏴󠁧󠁢󠁥󠁮󠁧󠁿
        String firstPair = new String(Character.toChars(0x0001F3F4));
        String secondPair = new String(Character.toChars(0x000E0067));
        String thirdPair = new String(Character.toChars(0x000E0062));
        String forthPair = new String(Character.toChars(0x000E0065));
        String fifthPair = new String(Character.toChars(0x000E006E));
        String sixthPair = new String(Character.toChars(0x000E0067));
        String seventhPair = new String(Character.toChars(0x000E007F));
        String blackFlag = firstPair + secondPair + thirdPair + forthPair + fifthPair + sixthPair + seventhPair;

        Paragraph paragraph = new Paragraph(blackFlag);

        document.setFont(font);
        document.add(paragraph);
        document.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder));
    }

    @Test
    //TODO DEVSIX-3307
    @LogMessages(messages = @LogMessage(messageTemplate = LogMessageConstant.FONT_SUBSET_ISSUE))
    public void surrogatePairCombingFullSurrsWithNoSurrs() throws IOException, InterruptedException {
        String outFileName = destinationFolder + "surrogatePairCombingFullSurrsWithNoSurrs.pdf";
        String cmpFileName = sourceFolder + "cmp_" + "surrogatePairCombingFullSurrsWithNoSurrs.pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outFileName));
        Document document = new Document(pdfDocument);

        PdfFont font = PdfFontFactory.createFont(fontsFolder + "NotoColorEmoji.ttf", PdfEncodings.IDENTITY_H);

        //World Map
        String firstPair = new String(Character.toChars(0x0001F5FA));
        String space = "\u0020";

        //🗽
        String secondPair = new String(Character.toChars(0x0001F5FD));

        //Satellite
        String thirdPair = new String(Character.toChars(0x0001F6F0));
        String allPairs = firstPair + space + secondPair + space + thirdPair;

        Paragraph paragraph = new Paragraph(allPairs);

        document.setFont(font);
        document.add(paragraph);
        document.close();

        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder));
    }
}
