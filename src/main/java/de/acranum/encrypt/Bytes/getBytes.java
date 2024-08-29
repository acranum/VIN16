package de.acranum.encrypt.Bytes;

public class getBytes {
    public String getByte(String in) {
        StringBuilder binaryStringBuilder = new StringBuilder();

        // Iteriere über jeden Charakter im Eingabestring
        for (char c : in.toCharArray()) {
            // Extrahiere die Binärrepräsentation des aktuellen Zeichens
            String binary = Integer.toBinaryString(c);

            // Füge führende Nullen hinzu, um sicherzustellen, dass jede Binärzahl 8 Bits hat
            while (binary.length() < 8) {
                binary = "0" + binary;
            }

            // Füge die Binärrepräsentation des aktuellen Zeichens zum Ergebnis hinzu
            binaryStringBuilder.append(binary).append(" ");
        }

        // Entferne das letzte Leerzeichen
        if (binaryStringBuilder.length() > 0) {
            binaryStringBuilder.deleteCharAt(binaryStringBuilder.length() - 1);
        }

        return binaryStringBuilder.toString();
    }
    public String getString(String in) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] binaryArray = in.split("\\s+");

        for (String binary : binaryArray) {
            int decimalValue = Integer.parseInt(binary, 2); // Konvertiere binären Wert in Dezimalwert
            stringBuilder.append((char) decimalValue); // Konvertiere Dezimalwert in ASCII-Buchstaben und füge sie zur Zeichenkette hinzu
        }

        return stringBuilder.toString();
    }
}
