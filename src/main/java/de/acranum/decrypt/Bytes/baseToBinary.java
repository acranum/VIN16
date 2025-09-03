package de.acranum.decrypt.Bytes;

import java.util.ArrayList;
import java.util.Base64;

public class baseToBinary {
    public ArrayList<String> btb(String in) {
        ArrayList<String> data = new ArrayList<>();
        String clean_in = in.replaceAll("\\s+", "");

        //byte[] base64DecodedBytes = Base64.getDecoder().decode(clean_in);
        //String decodedString = new String(base64DecodedBytes);

        byte[] binaryData = Base64.getDecoder().decode(clean_in);

        // Ausgabe des Binär-Arrays
        for (byte b : binaryData) {
            //data.add(b + " ");
            data.add(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        return data;
    }

}
