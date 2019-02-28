package org.terence.backend.common.utils.jwt;

import org.terence.backend.common.utils.NullValueUtil;

import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * @author terence
 * @since 2019/2/18 17:45
 */
public class RsaKeyHelper {

    /**
     * get public key by file path
     * @param publicKeyPath
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(String publicKeyPath) throws Exception {
        byte[] keyBytes = getFullFile(publicKeyPath);
        return getPublicKey(keyBytes);
    }

    /**
     * get private key by file path
     * @param privateKeyPath
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String privateKeyPath) throws Exception {
        byte[] keyBytes = getFullFile(privateKeyPath);
        return getPrivateKey(keyBytes);
    }

    /**
     * get public key by byte
     * @param publicKeyByte
     * @return
     * @throws Exception
     */
    public PublicKey getPublicKey(byte[] publicKeyByte) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyByte);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    /**
     * get private key by byte
     * @param privateKeyByte
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(byte[] privateKeyByte) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyByte);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    /**
     * generate both public & private key
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void generateKeys(String publicKeyFilename, String privateKeyFilename, String password) throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
        fos.write(publicKeyBytes);
        fos.close();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        fos = new FileOutputStream(privateKeyFilename);
        fos.write(privateKeyBytes);
        fos.close();
    }

    private byte[] getFullFile(String fileName) throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        NullValueUtil.handleNull(resourceAsStream);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        return keyBytes;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom("123".getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        keyPairGenerator.genKeyPair();
    	RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
    	try {
			rsaKeyHelper.generateKeys("D:/public.key", "D:/private.key", "terence");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
