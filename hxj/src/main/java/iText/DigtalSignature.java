package iText;


import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.signatures.*;
import com.itextpdf.styledxmlparser.jsoup.nodes.Element;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

public class DigtalSignature {


    public static final String BASE = "src/main/java/com/xiongjie/secondStudy/iText/";
    public static final String KEYSTORE = "/Users/admin/myKey.jks";
    public static final char[] PASSWORD = "123456".toCharArray();
    public static final String SRC = BASE+"hello.pdf";
    public static final String DEST = BASE+"hello_signed%s.pdf";

    public static void main(String[] args) throws Exception {
        //添加摘要hash加密算法
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);

        //从秘钥存储库获取包含公钥的证书和私钥
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
        Certificate[] chain = ks.getCertificateChain(alias);

        DigtalSignature app = new DigtalSignature();
        app.sign(SRC, String.format(DEST, 1), chain, pk, DigestAlgorithms.SHA256, provider.getName(), PdfSigner.CryptoStandard.CMS, "reason 1", "test location 1");
        app.sign(SRC, String.format(DEST, 2), chain, pk, DigestAlgorithms.SHA512, provider.getName(), PdfSigner.CryptoStandard.CMS, "reason 2", "test location 2");
        app.sign(SRC, String.format(DEST, 3), chain, pk, DigestAlgorithms.SHA256, provider.getName(), PdfSigner.CryptoStandard.CADES, "reason 3", "test location 3");
        app.sign(SRC, String.format(DEST, 4), chain, pk, DigestAlgorithms.RIPEMD160, provider.getName(), PdfSigner.CryptoStandard.CADES, "reason 4", "test location 4");
    }


    //说明文档和代码版本不同
    public void sign(String src, String dest, Certificate[] chain, PrivateKey pk, String digestAlgorithm, String provider, PdfSigner.CryptoStandard subfilter, String reason, String location)throws Exception{
        //是否签名成功标志
        boolean success = false;
        //预估大小
        int estimatedSize = 3000;

        //通过调整预估大小直到签名成功
        while (!success) {
            try {
                PdfReader pdfReader = new PdfReader(src);
                PdfSigner pdfSigner = new PdfSigner(pdfReader, new FileOutputStream(dest), false);
                pdfSigner.signExternalContainer(new IExternalSignatureContainer() {
                    @Override
                    public byte[] sign(InputStream inputStream) throws GeneralSecurityException {
                        return new byte[0];
                    }

                    @Override
                    public void modifySigningDictionary(PdfDictionary pdfDictionary) {

                    }
                }, estimatedSize);
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
                estimatedSize += 1000;
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

    }

    public void sign(String src, String tmp, String dest, Certificate[] chain, PrivateKey pk, String digestAlgorithm, String provider, PdfSigner.CryptoStandard subfilter, String reason, String location) throws Exception {
//        PdfReader reader = new PdfReader(src);
//        FileOutputStream os = new FileOutputStream(dest);
//        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', new File(tmp));
//
//        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
//        appearance.setReason(reason);
//        appearance.setLocation(location);
//        appearance.setVisibleSignature(new Rectangle(36, 748, 144, 780), 1, "sig");
//
//        ExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
//        ExternalDigest digest = new BouncyCastleDigest();
//        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
    }

}
