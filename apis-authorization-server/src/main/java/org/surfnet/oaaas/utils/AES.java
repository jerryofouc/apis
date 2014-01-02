package org.surfnet.oaaas.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 12/30/13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public class AES {

    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        plainText = plaintext + "\0\0\0";
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
     //   System.out.println(encryptionKey.getBytes("UTF-8").length);
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }

    static String IV = "AAAAAAAAAAAAAAAA";
    static String plaintext = "test text 123"; /*Note null padding*/
    static String encryptionKey = "0123456789abcdef";
    public static void main(String [] args) {
        try {

            System.out.println("==Java==");
            System.out.println("plain:   " + plaintext);

            byte[] cipher = encrypt(plaintext, encryptionKey);

            System.out.print("cipher:  ");
            for (int i=0; i<cipher.length; i++)
                System.out.print(new Integer(cipher[i])+" ");
            System.out.println("");

            String decrypted = decrypt(cipher, encryptionKey);

            System.out.println("decrypt: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
