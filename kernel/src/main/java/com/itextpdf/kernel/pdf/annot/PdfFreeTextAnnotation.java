/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: Bruno Lowagie, Paulo Soares, et al.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.kernel.pdf.annot;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.annot.da.AnnotationDefaultAppearance;

public class PdfFreeTextAnnotation extends PdfMarkupAnnotation {

    private static final long serialVersionUID = -7835504102518915220L;
	/**
     * Text justification options.
     */
    public static final int LEFT_JUSTIFIED = 0;
    public static final int CENTERED = 1;
    public static final int RIGHT_JUSTIFIED = 2;

    /**
     * Creates new instance
     *
     * @param rect - rectangle that specifies annotation position and bounds on page
     * @param contents - the displayed text
     */
    public PdfFreeTextAnnotation(Rectangle rect, PdfString contents) {
        super(rect);
        setContents(contents);
    }

    /**
     * Creates new instance
     *
     * @param rect - bounding rectangle of annotation
     * @param appearanceString - appearance string of annotation
     * @deprecated unintuitive, will be removed in 7.1 use {@link #PdfFreeTextAnnotation(Rectangle, PdfString)} instead
     */
    @Deprecated
    public PdfFreeTextAnnotation(Rectangle rect, String appearanceString) {
        super(rect);
        setDefaultAppearance(new PdfString(appearanceString));
    }

    public PdfFreeTextAnnotation(PdfDictionary pdfObject) {
        super(pdfObject);
    }

    @Override
    public PdfName getSubtype() {
        return PdfName.FreeText;
    }

    public PdfString getDefaultStyleString() {
        return getPdfObject().getAsString(PdfName.DS);
    }

    public PdfFreeTextAnnotation setDefaultStyleString(PdfString defaultStyleString) {
        return (PdfFreeTextAnnotation) put(PdfName.DS, defaultStyleString);
    }

    public PdfFreeTextAnnotation setDefaultAppearance(AnnotationDefaultAppearance da) {
        return (PdfFreeTextAnnotation) setDefaultAppearance(da.toPdfString());
    }

    public PdfArray getCalloutLine() {
        return getPdfObject().getAsArray(PdfName.CL);
    }

    public PdfFreeTextAnnotation setCalloutLine(float[] calloutLine) {
        return setCalloutLine(new PdfArray(calloutLine));
    }

    public PdfFreeTextAnnotation setCalloutLine(PdfArray calloutLine) {
        return (PdfFreeTextAnnotation) put(PdfName.CL, calloutLine);
    }

    public PdfName getLineEndingStyle() {
        return getPdfObject().getAsName(PdfName.LE);
    }

    public PdfFreeTextAnnotation setLineEndingStyle(PdfName lineEndingStyle) {
        return (PdfFreeTextAnnotation) put(PdfName.LE, lineEndingStyle);
    }
}
