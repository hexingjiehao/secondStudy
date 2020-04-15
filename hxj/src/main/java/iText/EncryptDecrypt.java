package iText;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class EncryptDecrypt {

    protected KeyStore ks;

    public EncryptDecrypt(String keystore, String ks_pass) throws GeneralSecurityException, IOException {
        initKeyStore(keystore, ks_pass);
    }
    public void initKeyStore(String keystore, String ks_pass)throws GeneralSecurityException, IOException {
        ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keystore), ks_pass.toCharArray());
    }
    public X509Certificate getCertificate(String alias)throws KeyStoreException {
        return (X509Certificate) ks.getCertificate(alias);
    }
    public Key getPublicKey(String alias)throws GeneralSecurityException, IOException {
        return getCertificate(alias).getPublicKey();
    }
    public Key getPrivateKey(String alias, String pk_pass) throws GeneralSecurityException, IOException {
        return ks.getKey(alias, pk_pass.toCharArray());
    }

    //公钥加密操作
    public byte[] encrypt(Key key, String message)throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherData = cipher.doFinal(message.getBytes());
        return cipherData;
    }

    //私钥解密操作
    public String decrypt(Key key, byte[] message) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherData = cipher.doFinal(message);
        return new String(cipherData);
    }

    public static void main(String[] args)throws GeneralSecurityException, IOException {

        //首先先要执行一下命令：
        // keytool -genkey -v -keystore myKey.jks -keyalg RSA -keysize 2048 -validity 10000 -alias myAlias
        // 输入密码123456
        EncryptDecrypt app =new EncryptDecrypt("/Users/admin/myKey.jks", "123456");

        Key publicKey = app.getPublicKey("myAlias");
        Key privateKey = app.getPrivateKey("myAlias", "123456");

        System.out.println("Let's encrypt 'secret message' with a public key");
        byte[] encrypted = app.encrypt(publicKey, "secret message");
        System.out.println("Encrypted message: " + new BigInteger(1, encrypted).toString(16));

        System.out.println("Let's decrypt it with the corresponding private key");
        String decrypted = app.decrypt(privateKey, encrypted);
        System.out.println(decrypted);

        System.out.println("You can also encrypt the message with a private key");
        encrypted = app.encrypt(privateKey, "secret message");
        System.out.println("Encrypted message: "+ new BigInteger(1, encrypted).toString(16));

        System.out.println("Now you need the public key to decrypt it");
        decrypted = app.decrypt(publicKey, encrypted);
        System.out.println(decrypted);

        //查看证书内容
        System.out.println("查看证书内容：");
        Certificate certificate=app.getCertificate("myAlias");
        System.out.println(certificate.toString());
    }

}
