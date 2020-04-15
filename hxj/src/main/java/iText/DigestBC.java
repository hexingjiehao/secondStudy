package iText;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.GeneralSecurityException;
import java.security.Security;

public class DigestBC extends DigestDefault {

    public static void main(String[] args) {
        showTest("SHA-224");
        showTest("RIPEMD128");
    }


    public static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();
    static {
        //PROVIDER提供丰富的加密算法
        Security.addProvider(PROVIDER);
    }
    protected DigestBC(String password, String algorithm) throws GeneralSecurityException {
        super(password, algorithm, PROVIDER.getName());
    }
    public static DigestBC getInstance(String password, String algorithm, String provider) throws GeneralSecurityException {
        return new DigestBC(password, algorithm);
    }

}
