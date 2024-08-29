package de.acranum;


import de.acranum.Util.ByteArrayConverter;
import de.acranum.decrypt.decryptC;
import de.acranum.decrypt.hashing;
import de.acranum.encrypt.encryptC;

import java.security.NoSuchAlgorithmException;

public class vin {
    de.acranum.decrypt.hashing hashing = new hashing();
    encryptC encrypt = new encryptC();
    decryptC decrypt = new decryptC();
    String finalKey;


    public String encrypt(String data, String key) {
        try {
            finalKey = hashing.md5(key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encrypt.encrypt(data, finalKey);
    }
    public String decrypt(String data, String key) {
        try {
            finalKey = hashing.md5(key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return decrypt.decrypt(data, finalKey);
    }

    ByteArrayConverter byteConv = new ByteArrayConverter();

    public byte[] encrypt(byte[] input, String key) {
        String inputStr = byteConv.byteArrayToString(input);
        String output = encrypt.encrypt(inputStr, key);
        byte[] outBytes = byteConv.stringToByteArray(output);
        return outBytes;
    }

    public byte[] decrypt(byte[] input, String key) {
        String inputStr = byteConv.byteArrayToString(input);
        String output = decrypt.decrypt(inputStr, key);
        byte[] outBytes = byteConv.stringToByteArray(output);
        return outBytes;
    }




}
