package de.acranum.encrypt;

import java.util.ArrayList;

public class XOR {
    public ArrayList XOR(ArrayList<String> bytes, ArrayList<String> key) {
        ArrayList<String> encryptedPackets = new ArrayList<>();

        for (String packet : bytes) {
            StringBuilder encryptedPacket = new StringBuilder();
            String[] binarys = packet.split("\\s+");
            int keyIndex = 0;

            for (String binary : binarys) {
                StringBuilder xorResult = new StringBuilder();
                for (int i = 0; i < binary.length(); i++) {
                    char bin = binary.charAt(i);
                    char keybin = key.get(keyIndex).charAt(i % 8);
                    int xorVal = Character.getNumericValue(bin) ^ Character.getNumericValue(keybin);
                    xorResult.append(xorVal);
                }
                encryptedPacket.append(xorResult).append(" ");
                keyIndex = (keyIndex + 1) % key.size();
            }
            encryptedPackets.add(encryptedPacket.toString().trim());
        }
        return encryptedPackets;
    }
}
