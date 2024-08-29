package de.acranum.Util;

import java.nio.charset.StandardCharsets;

public class ByteArrayConverter {

    public byte[] stringToByteArray(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }
        // Umwandlung des Strings in ein Byte-Array unter Verwendung von UTF-8
        return input.getBytes(StandardCharsets.UTF_8);
    }
    public String byteArrayToString(byte[] byteArray) {
        if (byteArray == null) {
            throw new IllegalArgumentException("Byte array cannot be null");
        }
        // Umwandlung des Byte-Arrays in einen String unter Verwendung von UTF-8
        return new String(byteArray, StandardCharsets.UTF_8);
    }

}
