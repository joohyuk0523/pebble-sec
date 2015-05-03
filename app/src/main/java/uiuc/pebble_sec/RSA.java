package uiuc.pebble_sec;

import android.util.Log;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

/**
 * Created by John on 4/5/2015.
 */
public class RSA {
    public static PublicKey uk = null;
    public static PrivateKey rk = null;

    public static PublicKey getPubKey(){
        return uk;
    }
    public static PrivateKey getPriKey(){
        return rk;
    }

    // byte2hex function is used to convert byte array to hex
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs += ("0" + stmp);
            else
                hs += stmp;
            Log.e("byte:", "0" + stmp);
        }
        return hs.toUpperCase();
    }

    // hex2byte function is used to convert hex to byte array
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("hello");

        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    // generateKey function is used to generate Asymmetric key pair, public key and private key
    public void generateKey() {
        KeyPairGenerator gen;
        try {
            gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(1024, new SecureRandom());
            KeyPair keyPair = gen.generateKeyPair();
            uk = keyPair.getPublic();
            rk = keyPair.getPrivate();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // encrypt function is used to perform encryption using Public key
    private static byte[] encrypt(String text, PublicKey pubRSA)
        throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubRSA);
        return cipher.doFinal(text.getBytes());
    }

    // encrypt function is used to catch exception if there is an error with the encryption
    public final static String encrypt(String text, PublicKey k, int i) {
        try{
            System.out.println("encrypt()");
            return byte2hex(encrypt(text, k));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // decrypt function is used to perform the real description with Private key
    private static byte[] decrypt(byte[] src, PrivateKey k) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(src);
    }

    // decrypt function is used to catch exception when there is an error with decryption
    public final static String decrypt(String data, PrivateKey kk, int i) {
        try{
            return new String(decrypt(hex2byte(data.getBytes()), kk));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String testcase(String test){
        generateKey();
        try {
            System.out.println("testcase()");
            byte[] encrypted = encrypt(test, uk);
            byte[] decrypted = decrypt(encrypted , rk);
            return byte2hex(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
