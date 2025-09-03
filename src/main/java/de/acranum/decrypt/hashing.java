package de.acranum.decrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class hashing {

    public String md5(char[] password) throws NoSuchAlgorithmException {
        byte[] passBytes = new byte[password.length * 2]; // UTF-16 (char -> 2 Bytes)
        for (int i = 0; i < password.length; i++) {
            passBytes[i*2] = (byte) (password[i] >> 8);
            passBytes[i*2+1] = (byte) (password[i]);
        }

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(passBytes);

        Arrays.fill(passBytes, (byte) 0);

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : encodedhash) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        Arrays.fill(password, '0');

        System.out.println(hexString.toString());
        return hexString.toString();
    }
}
