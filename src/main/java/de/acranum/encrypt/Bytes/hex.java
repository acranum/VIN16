package de.acranum.encrypt.Bytes;

import java.util.ArrayList;
import java.util.Base64;

public class hex {
    public ArrayList<String> hexadetimal(ArrayList<String> in) {
        ArrayList<String> hexapacket = new ArrayList<>();
        ArrayList<String> hex = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String packet : in) {
            String[] binarys = packet.split("\\s+");
            int keyIndex = 0;

            for (String binary : binarys) {
                int decimal = Integer.parseInt(binary, 2);
                //System.out.println(decimal);
                String hexadecimal = Integer.toHexString(decimal);
                //System.out.println(hexadecimal);
                hex.add(hexadecimal);
                sb.append(hexadecimal + " ");
            }
            hexapacket.add(sb.toString().trim());
        }
        return hexapacket;
    }

    public String decimal(ArrayList<String> in) {
        ArrayList<String> hex = new ArrayList<>();
        for (String packet : in) {
            StringBuilder sb = new StringBuilder();
            String[] hexValues = packet.split("\\s+");
            for (String hexValue : hexValues) {
                int decimal = Integer.parseInt(hexValue, 16);
                sb.append((char) decimal); // Konvertiere den dezimalen Wert in ein Zeichen
            }
            String characters = sb.toString();
            String base64Encoded = Base64.getEncoder().encodeToString(characters.getBytes());
            hex.add(base64Encoded);
        }
        StringBuilder sb = new StringBuilder();
        for (String base64 : hex) {
            sb.append(base64);
        }
        String end = sb.toString();
        end.replace(" ", "");
        return end;
    }



    public String binaryListToBase64(ArrayList<String> binaryList) {
        StringBuilder binaryStringBuilder = new StringBuilder();

        // Konvertiere jede binäre Zeichenfolge in eine einzelne Binärzeichenfolge
        for (String binary : binaryList) {
            binaryStringBuilder.append(binary.replace(" ", ""));
        }

        // Konvertiere die Binärzeichenfolge in ein Byte-Array
        byte[] byteArray = new byte[binaryStringBuilder.length() / 8];
        for (int i = 0; i < binaryStringBuilder.length(); i += 8) {
            String byteString = binaryStringBuilder.substring(i, i + 8);
            byteArray[i / 8] = (byte) Integer.parseInt(byteString, 2);
        }

        // Base64-Kodierung des Byte-Arrays
        String base64String = Base64.getEncoder().encodeToString(byteArray);
        return base64String;
    }
}
