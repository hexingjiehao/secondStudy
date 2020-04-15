package iText;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.pdf.*;

public class AbcPdfIText {

    public static void main(String[] args) {

        //序号1
        PdfArray array = new PdfArray();
        array.add(PdfName.First);
        array.add(new PdfString("Second"));
        array.add(new PdfNumber(3));
        array.add(PdfBoolean.FALSE);
        showPdfArrayInfo(array);

        //序号2
        showPdfBooleanInfo(PdfBoolean.TRUE);
        showPdfBooleanInfo(PdfBoolean.FALSE);

        //序号3
        PdfDictionary dict = new PdfDictionary();
        dict.put(new PdfName("Entry1"), PdfName.First);
        dict.put(new PdfName("Entry2"), new PdfString("Second"));
        dict.put(new PdfName("3rd"), new PdfNumber(3));
        dict.put(new PdfName("Fourth"), PdfBoolean.FALSE);
        showPdfDictionaryInfo(dict);

        //序号4
        showPdfLiteralInfo(new PdfLiteral("<</Type/Custom/Contents [1 2 3]>>"));

        //序号6
        showPdfNameInfo(PdfName.Contents);
        showPdfNameInfo(new PdfName("CustomName"));
        showPdfNameInfo(new PdfName("Test #1 100%"));

        //序号7
        showPdfNullInfo(PdfNull.PDF_NULL);

        //序号8
        showPdfNumberInfo(new PdfNumber(100));
        showPdfNumberInfo(new PdfNumber(100l));
        showPdfNumberInfo(new PdfNumber(1.5f));
        showPdfNumberInfo(new PdfNumber(1.5));

        //序号9
        PdfStream stream = new PdfStream("Long stream of data stored in a FlateDecode compressed stream object".getBytes());
        showPdfStreamInfo(stream);

        //序号10
        showPdfStringInfo(new PdfString("Test"));
        showPdfStringInfo(new PdfString("\u6d4b\u8bd5", PdfEncodings.UTF8) );
        PdfString s1=new PdfString("Test");
        s1.setHexWriting(true);
        showPdfStringInfo(s1);
    }

    private static void showPdfArrayInfo(PdfArray obj){
        System.out.println("打印PdfArray内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isArray());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.size());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfBooleanInfo(PdfBoolean obj){
        System.out.println("打印PdfBoolean内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isBoolean());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.getValue());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfDictionaryInfo(PdfDictionary obj){
        System.out.println("打印PdfDictionary内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isDictionary());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.size());

        for (PdfName key : obj.keySet()) {
            System.out.print(key+":");
            System.out.println(obj.get(key));
        }
        System.out.println(obj.isIndirect());
    }

    private static void showPdfLiteralInfo(PdfLiteral obj){
        System.out.println("打印PdfLiteral内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isLiteral());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfNameInfo(PdfName obj){
        System.out.println("打印PdfName内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isName());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.getValue());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfNullInfo(PdfNull obj){
        System.out.println("打印PdfNull内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isNull());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfNumberInfo(PdfNumber obj){
        System.out.println("打印PdfNumber内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isNumber());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.getValue());
        System.out.println(obj.toString());
        System.out.println(obj.intValue());
        System.out.println(obj.longValue());
        System.out.println(obj.doubleValue());
        System.out.println(obj.floatValue());
        System.out.println(obj.isIndirect());
    }

    private static void showPdfStreamInfo(PdfStream obj){
        System.out.println("打印PdfStream内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isStream());
        System.out.println(obj.getType());
        System.out.println(obj.toString());
        System.out.println(obj.getLength());
        System.out.println(obj.size());
        for (PdfName key : obj.keySet()) {
            System.out.print(key+":");
            System.out.println(obj.get(key));
        }
        System.out.println(obj.isIndirect());
    }

    private static void showPdfStringInfo(PdfString obj){
        System.out.println("打印PdfString内容");
        System.out.println(obj.getClass().getName());
        System.out.println(obj.isString());
        System.out.println(obj.getType());
        System.out.println(new String(obj.getValueBytes()));
        System.out.println(obj.toString());
        System.out.println(obj.getValue());
        System.out.println(obj.isHexWriting());
        System.out.println(obj.getEncoding());
        System.out.println(obj.toUnicodeString());
        System.out.println(obj.isIndirect());
    }
}
